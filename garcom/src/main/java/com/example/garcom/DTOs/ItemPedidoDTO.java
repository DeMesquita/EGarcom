package com.example.garcom.DTOs;

public class ItemPedidoDTO {
  private Long id;
  private Float quantidade;
  private ItemCardapioDTO itemCardapio;

  public ItemPedidoDTO() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Float getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Float quantidade) {
    this.quantidade = quantidade;
  }

  public ItemCardapioDTO getItemCardapio() {
    return itemCardapio;
  }

  public void setItemCardapio(ItemCardapioDTO itemCardapio) {
    this.itemCardapio = itemCardapio;
  }
}
