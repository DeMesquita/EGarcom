package com.example.garcom.DTOs;

import java.util.List;

public class CardapioDTO {
  private Long id;
  private List<CategoriaDTO> categorias;
  private GerenteDTO gerente;

  public CardapioDTO() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<CategoriaDTO> getCategorias() {
    return categorias;
  }

  public void setCategorias(List<CategoriaDTO> categorias) {
    this.categorias = categorias;
  }

  public GerenteDTO getGerente() {
    return gerente;
  }

  public void setGerente(GerenteDTO gerente) {
    this.gerente = gerente;
  }
}
