package com.example.garcom.DTOs;

import java.util.Date;
import java.util.List;


public class PedidoDTO {
    private Long id;
    private Integer numero;
    private Date horarioPedido;
    private Date horarioEntrega;
    private ClienteDTO cliente;
    private List<ItemPedidoDTO> itens;
    private String status;
    private Double valorTotal;
    private Long contaId;
    
    public PedidoDTO() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getNumero() {
        return numero;
    }
    
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    
    public Date getHorarioPedido() {
        return horarioPedido;
    }
    
    public void setHorarioPedido(Date horarioPedido) {
        this.horarioPedido = horarioPedido;
    }
    
    public Date getHorarioEntrega() {
        return horarioEntrega;
    }
    
    public void setHorarioEntrega(Date horarioEntrega) {
        this.horarioEntrega = horarioEntrega;
    }
    
    public ClienteDTO getCliente() {
        return cliente;
    }
    
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
    
    public List<ItemPedidoDTO> getItens() {
        return itens;
    }
    
    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Double getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public Long getContaId() {
        return contaId;
    }
    
    public void setContaId(Long contaId) {
        this.contaId = contaId;
    }
}