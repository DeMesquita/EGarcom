package com.example.garcom.DTOs;

public class MesaDTO {
  private Long id;
  private Integer numero;
  private Boolean disponivel;
  private GarcomDTO garcom;

  public MesaDTO() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getNumero() {
    return numero;
  }
  

  public Boolean getDisponivel() {
    return disponivel;
  }

  

  public GarcomDTO getGarcom() {
    return garcom;
  }
    
    public void setGarcom(GarcomDTO garcom) {
        this.garcom = garcom;
    }
    
    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
