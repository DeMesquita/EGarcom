package com.example.garcom.Model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cliente")
public class Cliente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  private Date horaChegada;
  private Date horaSaida;

  public Cliente() {}

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

  public Date getHoraChegada() {
    return horaChegada;
  }

  public void setHoraChegada(Date horaChegada) {
    this.horaChegada = horaChegada;
  }

  public Date getHoraSaida() {
    return horaSaida;
  }

  public void setHoraSaida(Date horaSaida) {
    this.horaSaida = horaSaida;
  }
}
