package com.example.garcom.DTOs;

public class CategoriaDTO {
    private Long id;
    private String nome;
    private CategoriaDTO categoriaMae;

    public CategoriaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public CategoriaDTO getCategoriaMae() {
        return categoriaMae;
    }
    
    public void setCategoriaMae(CategoriaDTO categoriaMae) {
        this.categoriaMae = categoriaMae;
    }
    
}
