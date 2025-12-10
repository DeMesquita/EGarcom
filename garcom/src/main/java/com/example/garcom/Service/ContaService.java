package com.example.garcom.Service;

import com.example.garcom.DTOs.ContaDTO;
import com.example.garcom.DTOs.MesaDTO;
import com.example.garcom.DTOs.PedidoDTO;
import com.example.garcom.Model.Conta;
import com.example.garcom.Model.Mesa;
import com.example.garcom.Model.Pedido;
import com.example.garcom.Model.StatusConta;
import com.example.garcom.Model.StatusPedido; // Importação adicionada/ajustada
import com.example.garcom.Repository.ContaRepository;
import com.example.garcom.Repository.MesaRepository;
import com.example.garcom.Repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaService {
    
    private final ContaRepository contaRepository;
    private final MesaRepository mesaRepository;
    private final PedidoRepository pedidoRepository;
    
    public ContaService(ContaRepository contaRepository, MesaRepository mesaRepository, PedidoRepository pedidoRepository) {
        this.contaRepository = contaRepository;
        this.mesaRepository = mesaRepository;
        this.pedidoRepository = pedidoRepository;
    }
    
    public ContaDTO abrirConta(Long mesaId) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada!"));
        
        if (contaRepository.findByMesaIdAndStatus(mesaId, StatusConta.ABERTA).isPresent()) {
            throw new RuntimeException("Já existe uma conta aberta para esta mesa!");
        }
        
        Conta conta = new Conta();
        conta.setMesa(mesa);
        conta.setStatus(StatusConta.ABERTA);
        mesa.setDisponivel(false);
        mesaRepository.save(mesa);
        
        return toDTO(contaRepository.save(conta));
    }
    
    public ContaDTO fecharConta(Long contaId) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
        
        if (conta.getStatus() != StatusConta.ABERTA) {
            throw new RuntimeException("A conta não está ABERTA para ser fechada.");
        }
        
        List<Pedido> pedidosDaConta = pedidoRepository.findByContaId(contaId);
        
        if (pedidosDaConta.isEmpty()) {
            throw new RuntimeException("A conta não pode ser fechada, pois não possui pedidos associados.");
        }
        
        
        double total = pedidosDaConta.stream()
                .filter(p -> p.getStatus() == StatusPedido.ENTREGUE)
                .mapToDouble(Pedido::getValorTotal)
                .sum();
        
        conta.setValorTotal(total);
        conta.setDataFechamento(LocalDateTime.now());
        conta.setStatus(StatusConta.FECHADA);
        
        Mesa mesa = conta.getMesa();
        mesa.setDisponivel(true);
        mesaRepository.save(mesa);
        
        return toDTO(contaRepository.save(conta));
    }
    
    public ContaDTO findById(Long id) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
        return toDTO(conta);
    }
    
    public List<ContaDTO> findAll() {
        return contaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
    
    private ContaDTO toDTO(Conta entity) {
        ContaDTO dto = new ContaDTO();
        dto.setId(entity.getId());
        dto.setDataAbertura(entity.getDataAbertura());
        dto.setDataFechamento(entity.getDataFechamento());
        dto.setValorTotal(entity.getValorTotal());
        dto.setStatus(entity.getStatus().toString());
        
        MesaDTO mesaDto = new MesaDTO();
        mesaDto.setId(entity.getMesa().getId());
        mesaDto.setNumero(entity.getMesa().getNumero());
        mesaDto.setDisponivel(entity.getMesa().getDisponivel());
        dto.setMesa(mesaDto);
        
        if (entity.getPedidos() != null) {
            dto.setPedidos(entity.getPedidos().stream().map(p -> {
                PedidoDTO pedidoDto = new PedidoDTO();
                pedidoDto.setId(p.getId());
                pedidoDto.setStatus(p.getStatus().toString());
                pedidoDto.setValorTotal(p.getValorTotal());
                return pedidoDto;
            }).collect(Collectors.toList()));
        }
        
        return dto;
    }
}