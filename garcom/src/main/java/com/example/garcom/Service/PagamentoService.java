package com.example.garcom.Service;

import com.example.garcom.DTOs.*;
import com.example.garcom.Model.*;
import com.example.garcom.Repository.ContaRepository;
import com.example.garcom.Repository.PagamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {
    
    private final PagamentoRepository pagamentoRepository;
    private final ContaRepository contaRepository;
    
    public PagamentoService(PagamentoRepository pagamentoRepository, ContaRepository contaRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.contaRepository = contaRepository;
    }
    
    public PagamentoDTO realizarPagamento(Long contaId, PagamentoDTO dto) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
        
        if (conta.getStatus() != StatusConta.FECHADA) {
            throw new RuntimeException("O pagamento só pode ser realizado em contas FECHADAS.");
        }
        
        if (pagamentoRepository.findByContaId(contaId).isPresent()) {
            throw new RuntimeException("O pagamento para esta conta já foi registrado.");
        }
        
        if (!dto.getValor().equals(conta.getValorTotal())) {
            throw new RuntimeException("O valor do pagamento deve ser igual ao valor total da conta (" + conta.getValorTotal() + ").");
        }
        
        Pagamento entity = toEntity(dto, conta);
        Pagamento savedEntity = pagamentoRepository.save(entity);
        
        conta.setPagamento(savedEntity);
        contaRepository.save(conta);
        
        return toDTO(savedEntity);
    }
    
    private Pagamento toEntity(PagamentoDTO dto, Conta conta) {
        Pagamento entity;
        String tipo = dto.getTipo().toUpperCase();
        
        if (tipo.equals("CARTAO") && dto instanceof PagamentoCartaoDTO) {
            Pagamento.PagamentoCartao cartaoEntity = new Pagamento.PagamentoCartao();
            cartaoEntity.setNumeroTransacao(((PagamentoCartaoDTO) dto).getNumeroTransacao());
            entity = cartaoEntity;
        } else if (tipo.equals("CHEQUE") && dto instanceof PagamentoChequeDTO) {
            Pagamento.PagamentoCheque chequeEntity = new Pagamento.PagamentoCheque();
            chequeEntity.setNumeroCheque(((PagamentoChequeDTO) dto).getNumeroCheque());
            entity = chequeEntity;
        } else if (tipo.equals("DINHEIRO") && dto instanceof PagamentoDinheiroDTO) {
            entity = new Pagamento.PagamentoDinheiro();
        } else {
            throw new IllegalArgumentException("Tipo de pagamento ou DTO inválido: " + tipo);
        }
        
        entity.setValor(dto.getValor());
        entity.setConta(conta);
        return entity;
    }
    
    private PagamentoDTO toDTO(Pagamento entity) {
        PagamentoDTO dto;
        String tipo;
        
        if (entity instanceof Pagamento.PagamentoCartao) {
            PagamentoCartaoDTO cartaoDto = new PagamentoCartaoDTO();
            cartaoDto.setNumeroTransacao(((Pagamento.PagamentoCartao) entity).getNumeroTransacao());
            dto = cartaoDto;
            tipo = "CARTAO";
        } else if (entity instanceof Pagamento.PagamentoCheque) {
            PagamentoChequeDTO chequeDto = new PagamentoChequeDTO();
            chequeDto.setNumeroCheque(((Pagamento.PagamentoCheque) entity).getNumeroCheque());
            dto = chequeDto;
            tipo = "CHEQUE";
        } else if (entity instanceof Pagamento.PagamentoDinheiro) {
            dto = new PagamentoDinheiroDTO();
            tipo = "DINHEIRO";
        } else {
            dto = new PagamentoDTO();
            tipo = "DESCONHECIDO";
        }
        
        dto.setId(entity.getId());
        dto.setValor(entity.getValor());
        dto.setTipo(tipo);
        dto.setContaId(entity.getConta().getId());
        return dto;
    }
}