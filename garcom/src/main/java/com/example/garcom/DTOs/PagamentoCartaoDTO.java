package com.example.garcom.DTOs;

public class PagamentoCartaoDTO extends PagamentoDTO {
  private String numeroTransacao;

  public PagamentoCartaoDTO() {}

  public String getNumeroTransacao() {
    return numeroTransacao;
  }

  public void setNumeroTransacao(String numeroTransacao) {
    this.numeroTransacao = numeroTransacao;
  }
}
