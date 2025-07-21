package com.loja_de_eletronicos.loja.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Carrinho {
    @Id

    private Long id;
    private String categoria;
    private String nome;
    private int quantidade;
    private double valor;
    private double valorTotal;

    public Carrinho(){

    }

    public Carrinho(Long id, String categoria, String nome, int quantidade, double valor) {
        this.id = id;
        this.categoria = categoria;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.valorTotal = this.valor * quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
