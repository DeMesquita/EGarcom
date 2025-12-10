package com.example.garcom.Model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;

@Entity
@Table(name = "categoria")
public class Categoria {
  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String nome;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "categoria_mae_id")
  private Categoria categoriaMae;

  @OneToMany(mappedBy = "categoriaMae")
  private List<Categoria> subcategorias;

  public Categoria() {}

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Categoria getcategoriaMae() {
    return categoriaMae;
  }

  public void setcategoriaMae(Categoria categoriaMae) {
    this.categoriaMae = categoriaMae;
  }

  public List<Categoria> getSubcategorias() {
    return subcategorias;
  }

  public void setSubcategorias(List<Categoria> subcategorias) {
    this.subcategorias = subcategorias;
  }
}
