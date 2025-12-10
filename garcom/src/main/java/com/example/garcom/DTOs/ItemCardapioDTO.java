package com.example.garcom.DTOs;

public class ItemCardapioDTO {
    private Long id;
    private String nome;
    private String ingredientes;
    private Float preco;
    private Boolean disponivelNaCozinha;
    private CategoriaDTO categoria;

    public ItemCardapioDTO() {
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

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Boolean getDisponivelNaCozinha() {
        return disponivelNaCozinha;
    }

    public void setDisponivelNaCozinha(Boolean disponivelNaCozinha) {
        this.disponivelNaCozinha = disponivelNaCozinha;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }
}
