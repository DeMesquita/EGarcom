package com.example.garcom.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "pagamento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pagamento", discriminatorType = DiscriminatorType.STRING)
public abstract class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Double valor;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;
    
    public Pagamento() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Double getValor() {
        return valor;
    }
    
    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    public Conta getConta() {
        return conta;
    }
    
    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    @Entity
    @DiscriminatorValue("DINHEIRO")
    public static class PagamentoDinheiro extends Pagamento {
    }
    
    @Entity
    @DiscriminatorValue("CARTAO")
    public static class PagamentoCartao extends Pagamento {
        @Column(name = "nro_transacao")
        private String numeroTransacao;
        
        public PagamentoCartao() {
        }
        
        public String getNumeroTransacao() {
            return numeroTransacao;
        }
        
        public void setNumeroTransacao(String numeroTransacao) {
            this.numeroTransacao = numeroTransacao;
        }
    }
    
    @Entity
    @DiscriminatorValue("CHEQUE")
    public static class PagamentoCheque extends Pagamento {
        @Column(name = "numero_cheque")
        private String numeroCheque;
        
        public PagamentoCheque() {
        }
        
        public String getNumeroCheque() {
            return numeroCheque;
        }
        
        public void setNumeroCheque(String numeroCheque) {
            this.numeroCheque = numeroCheque;
        }
    }
    
    
    
}