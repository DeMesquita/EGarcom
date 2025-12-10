package com.example.garcom.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Float quantidade;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_cardapio_id", nullable = false)
  private ItemCardapio itemCardapio;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pedido_id", nullable = false)
  private Pedido pedido;

  public ItemPedido() {}

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

  public ItemCardapio getItemCardapio() {
    return itemCardapio;
  }

  public void setItemCardapio(ItemCardapio itemCardapio) {
    this.itemCardapio = itemCardapio;
  }

  public Pedido getPedido() {
    return pedido;
  }

  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }
}
