package com.example.garcom.Model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer numero;
    
    private Date horarioPedido;
    private Date horarioEntrega;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    
    @Column(nullable = false)
    private Double valorTotal;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private Conta conta;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens;
    
    public Pedido() {
        this.status = StatusPedido.RECEBIDO;
        this.valorTotal = 0.0;
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
    
    public StatusPedido getStatus() {
        return status;
    }
    
    public void setStatus(StatusPedido status) {
        this.status = status;
    }
    
    public Double getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Conta getConta() {
        return conta;
    }
    
    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    public List<ItemPedido> getItens() {
        return itens;
    }
    
    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
}