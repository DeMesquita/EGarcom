package com.example.garcom.DTOs;

import java.time.LocalDateTime;
import java.util.List;

public class ContaDTO {
    private Long id;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private Double valorTotal;
    private String status;
    private MesaDTO mesa; // Usado pelo ContaService
    private List<PedidoDTO> pedidos;
    private List<PagamentoDTO> pagamentos;
    
    public ContaDTO() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }
    
    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
    
    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }
    
    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }
    
    public Double getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public MesaDTO getMesa() {
        return mesa;
    }
    
    public void setMesa(MesaDTO mesa) {
        this.mesa = mesa;
    }
    
    public List<PedidoDTO> getPedidos() {
        return pedidos;
    }
    
    public void setPedidos(List<PedidoDTO> pedidos) {
        this.pedidos = pedidos;
    }
    
    public List<PagamentoDTO> getPagamentos() {
        return pagamentos;
    }
    
    public void setPagamentos(List<PagamentoDTO> pagamentos) {
        this.pagamentos = pagamentos;
    }
}