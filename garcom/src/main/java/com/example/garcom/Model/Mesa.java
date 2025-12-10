package com.example.garcom.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "mesa")
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private Integer numero;
    
    private Boolean disponivel;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garcom_id")
    private Usuario garcom;
    
    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conta> contas;
    
    public Mesa() {
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
    
    public Boolean getDisponivel() {
        return disponivel;
    }
    
    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    public Usuario getGarcom() {
        return garcom;
    }
    
    public void setGarcom(Usuario garcom) {
        this.garcom = garcom;
    }
    
    public List<Conta> getContas() {
        return contas;
    }
    
    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }
}
