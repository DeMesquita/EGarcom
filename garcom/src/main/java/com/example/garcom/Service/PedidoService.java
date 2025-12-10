package com.example.garcom.Service;


import com.example.garcom.DTOs.*;
import com.example.garcom.Model.*;
import com.example.garcom.Repository.*;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ItemCardapioRepository itemCardapioRepository;
    private final ContaRepository contaRepository;
    private final MesaRepository mesaRepository;
    
    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ItemCardapioRepository itemCardapioRepository, ContaRepository contaRepository, MesaRepository mesaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.itemCardapioRepository = itemCardapioRepository;
        this.contaRepository = contaRepository;
        this.mesaRepository = mesaRepository;
    }
    
    public PedidoDTO create(PedidoDTO dto) {
        if (dto.getCliente() == null || dto.getCliente().getId() == null) {
            throw new IllegalArgumentException("Pedido deve estar associado a um Cliente.");
        }
        
        Cliente cliente = clienteRepository.findById(dto.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));
        
        Conta conta;
        if (dto.getContaId() != null) {
            conta = contaRepository.findById(dto.getContaId())
                    .orElseThrow(() -> new RuntimeException("Conta fornecida não encontrada!"));
            if (conta.getStatus() != StatusConta.ABERTA) {
                throw new RuntimeException("A Conta não está aberta para receber novos pedidos.");
            }
        } else {
            Mesa mesaLivre = mesaRepository.findAll().stream()
                    .filter(Mesa::getDisponivel)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Nenhuma mesa disponível para abrir uma nova conta."));
            
            conta = new Conta();
            conta.setMesa(mesaLivre);
            conta.setStatus(StatusConta.ABERTA);
            conta = contaRepository.save(conta);
            
            mesaLivre.setDisponivel(false);
            mesaRepository.save(mesaLivre);
            dto.setContaId(conta.getId());
        }
        
        Integer proximoNumero = pedidoRepository.findMaxNumero().map(max -> max + 1).orElse(101);
        
        Pedido pedido = new Pedido();
        pedido.setNumero(proximoNumero);
        pedido.setCliente(cliente);
        pedido.setConta(conta);
        pedido.setHorarioPedido(new Date());
        
        AtomicReference<Double> valorTotal = new AtomicReference<>(0.0);
        List<ItemPedido> itensPedido = dto.getItens().stream()
                .map(itemDto -> {
                    ItemPedido item = toItemPedidoEntity(itemDto, pedido);
                    double precoUnitario = item.getItemCardapio().getPreco();
                    valorTotal.set(valorTotal.get() + precoUnitario * item.getQuantidade());
                    return item;
                })
                .collect(Collectors.toList());
        
        pedido.setItens(itensPedido);
        pedido.setValorTotal(valorTotal.get());
        pedido.setStatus(StatusPedido.RECEBIDO); // Status inicial do pedido
        
        Pedido savedPedido = pedidoRepository.save(pedido);
        
        return toDTO(savedPedido);
    }
    
    public List<PedidoDTO> findAll() {
        return pedidoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public PedidoDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
        return toDTO(pedido);
    }
    
    public PedidoDTO update(Long id, PedidoDTO dto) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
        
     
        
        
        Pedido updatedPedido = pedidoRepository.save(pedido);
        return toDTO(updatedPedido);
    }
    
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }
    
    private ItemPedido toItemPedidoEntity(ItemPedidoDTO itemDto, Pedido pedido) {
        ItemCardapio itemCardapio = itemCardapioRepository.findById(itemDto.getItemCardapio().getId())
                .orElseThrow(() -> new RuntimeException("ItemCardapio não encontrado!"));
        
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setQuantidade(itemDto.getQuantidade());
        itemPedido.setItemCardapio(itemCardapio);
        itemPedido.setPedido(pedido);
        return itemPedido;
    }
    
    private PedidoDTO toDTO(Pedido entity) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(entity.getId());
        dto.setNumero(entity.getNumero());
        dto.setHorarioPedido(entity.getHorarioPedido());
        dto.setHorarioEntrega(entity.getHorarioEntrega());
        dto.setStatus(entity.getStatus().toString());
        dto.setValorTotal(entity.getValorTotal());
        dto.setContaId(entity.getConta().getId());
        
        ClienteDTO clienteDto = new ClienteDTO();
        clienteDto.setId(entity.getCliente().getId());
        clienteDto.setNome(entity.getCliente().getNome());
        dto.setCliente(clienteDto);
        
        List<ItemPedidoDTO> itensDto = entity.getItens().stream()
                .map(this::toItemPedidoDTO)
                .collect(Collectors.toList());
        dto.setItens(itensDto);
        
        return dto;
    }
    
    private ItemPedidoDTO toItemPedidoDTO(ItemPedido entity) {
        ItemPedidoDTO dto = new ItemPedidoDTO();
        dto.setId(entity.getId());
        dto.setQuantidade(entity.getQuantidade());
        
        ItemCardapio itemCardapioEntity = entity.getItemCardapio();
        
        ItemCardapioDTO itemCardapioDto = new ItemCardapioDTO();
        itemCardapioDto.setId(itemCardapioEntity.getId());
        itemCardapioDto.setNome(itemCardapioEntity.getNome());
        itemCardapioDto.setPreco(itemCardapioEntity.getPreco()); // CORREÇÃO
        itemCardapioDto.setIngredientes(itemCardapioEntity.getIngredientes()); // CORREÇÃO
        itemCardapioDto.setDisponivelNaCozinha(itemCardapioEntity.getDisponivelNaCozinha()); // CORREÇÃO
        
        if (itemCardapioEntity.getCategoria() != null) {
            CategoriaDTO categoriaDto = new CategoriaDTO();
            categoriaDto.setId(itemCardapioEntity.getCategoria().getId());
            categoriaDto.setNome(itemCardapioEntity.getCategoria().getNome());
            itemCardapioDto.setCategoria(categoriaDto);
        }
        
        dto.setItemCardapio(itemCardapioDto);
        
        return dto;
    }
}