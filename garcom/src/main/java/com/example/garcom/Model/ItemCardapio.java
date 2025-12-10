package com.example.garcom.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "item_cardapio")
public class ItemCardapio {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  private String ingredientes;

  @Column(nullable = false)
  private Float preco;

  @Column(name = "disponivel_na_cozinha")
  private Boolean disponivelNaCozinha;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "categoria_id", nullable = false)
  private Categoria categoria;

  public ItemCardapio() {}

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

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }
}
