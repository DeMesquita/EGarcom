package com.example.garcom.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cardapio")
public class Cardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gerente_id", unique = true, nullable = false)
    private Usuario gerente;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardapio_id")
    private List<Categoria> categorias;
    
    public Cardapio() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Usuario getGerente() {
        return gerente;
    }
    
    public void setGerente(Usuario gerente) {
        this.gerente = gerente;
    }
    
    public List<Categoria> getCategorias() {
        return categorias;
    }
    
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}