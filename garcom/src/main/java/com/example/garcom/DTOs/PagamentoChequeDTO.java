package com.example.garcom.DTOs;

public class PagamentoChequeDTO extends PagamentoDTO {
  private String numeroCheque;

  public PagamentoChequeDTO() {}

  public String getNumeroCheque() {
    return numeroCheque;
  }

  public void setNumeroCheque(String numeroCheque) {
    this.numeroCheque = numeroCheque;
  }
}
