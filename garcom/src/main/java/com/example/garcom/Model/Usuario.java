package com.example.garcom.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false, unique = true, length = 50)
    private String login;
    
    @Column(nullable = false)
    private String senha;
    
    public Usuario() {
    }
    
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
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @Entity
    @DiscriminatorValue("COZINHA")
    public static class Cozinha extends Usuario {
    }
    
    @Entity
    @DiscriminatorValue("GARCOM")
    public static class Garcom extends Usuario {
    }
    
    @Entity
    @DiscriminatorValue("GERENTE")
    public static class Gerente extends Usuario {
    }
    
    @Entity
    @DiscriminatorValue("CAIXA")
    public static class Caixa extends Usuario {
    }
}
