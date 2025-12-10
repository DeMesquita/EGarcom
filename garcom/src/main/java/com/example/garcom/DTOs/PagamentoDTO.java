package com.example.garcom.DTOs;

public class PagamentoDTO {
  private Long id;
  private String tipo;
  private Double valor;
  private Long contaId;

  public PagamentoDTO() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public Long getContaId() {
    return contaId;
  }

  public void setContaId(Long contaId) {
    this.contaId = contaId;
  }
}
