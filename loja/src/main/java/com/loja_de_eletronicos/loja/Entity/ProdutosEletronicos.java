package com.loja_de_eletronicos.loja.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "produtos_eletronicos")
public class ProdutosEletronicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoria;
    private String nome;
    private int quantidade;
    private double valor;
    private String descricao;

    public ProdutosEletronicos(){

    }

    public ProdutosEletronicos(Long id, String categoria, String nome, int quantidade, double valor, String descricao) {
        this.id = id;
        this.categoria = categoria;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.descricao = descricao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
