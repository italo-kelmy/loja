package com.loja_de_eletronicos.loja.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Usuário é obrigátorio")
    private String usuario;
    @NotNull(message = "Senha é obrigátorio")
    @Size(min = 8, message = "No mínimo 8 caracteres")
    private String senha;
    @NotNull(message = "Nome é obrigátorio")
    @Email(message = "Email inválido! Formato: exemploemail@hotmail.com")
    private String email;
    @NotNull(message = "Nome é obrigátorio")
    @Pattern(regexp = "^\\d{2} \\d{4,5}-\\d{4}$")
    private String telefone;
    @NotNull(message = "Nome é obrigátorio")
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Cep inválido! Formato: xxxxx-xxx")
    private String cep;


    public Usuarios() {

    }

    public Usuarios(Long id, String usuario, String senha, String email, String telefone, String cep) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.cep = cep;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuarios usuarios)) return false;
        return Objects.equals(getId(), usuarios.getId()) && Objects.equals(getUsuario(), usuarios.getUsuario()) && Objects.equals(getSenha(), usuarios.getSenha()) && Objects.equals(getEmail(), usuarios.getEmail()) && Objects.equals(getTelefone(), usuarios.getTelefone()) && Objects.equals(getCep(), usuarios.getCep());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsuario(), getSenha(), getEmail(), getTelefone(), getCep());
    }
}
