package com.loja_de_eletronicos.loja.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Usuário é obrigátorio")
    private String usuario;
    @NotNull(message = "Senha é obrigátorio")
    @Size(min = 8, message = "Senha com no mínimo 8 caracteres")
    private String senha;
    @NotNull(message = "Email é obrigátorio")
    @Email(message = "Formato válido: exemploemail@hotmail.com")
    private String email;
    @NotNull(message = "Cep é obrigátorio")
    @Pattern(regexp = "^\\d{5}-\\d{3}$")
    private String cep;
    @NotNull(message = "Número de telefone é obrigátorio")
    @Pattern(regexp = "^\\d{2} \\d{4,5}-\\d{4}$", message = "Formato válido: xx xxxxx-xxxx")
    private String telefone;






    public Usuarios() {

    }

    public Usuarios(Long id, String usuario, String senha, String email, String cep, String telefone) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
        this.cep = cep;
        this.telefone = telefone;

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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuarios usuarios = (Usuarios) o;
        return Objects.equals(getId(), usuarios.getId()) && Objects.equals(getUsuario(), usuarios.getUsuario()) && Objects.equals(getSenha(), usuarios.getSenha()) && Objects.equals(getEmail(), usuarios.getEmail()) && Objects.equals(getCep(), usuarios.getCep()) && Objects.equals(getTelefone(), usuarios.getTelefone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsuario(), getSenha(), getEmail(), getCep(), getTelefone());
    }
}
