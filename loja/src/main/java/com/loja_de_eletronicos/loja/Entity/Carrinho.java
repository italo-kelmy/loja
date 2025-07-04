package com.loja_de_eletronicos.loja.Entity;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "carrinho")
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoria;
    private String nome;
    private int quantidade;
    private double valor;
    private double valorTotal;


    public Carrinho(){

    }

    public Carrinho( String categoria, String nome, int quantidade, double valor) {
        this.categoria = categoria;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.valorTotal = valor * quantidade;
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

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Carrinho carrinho = (Carrinho) o;
        return getQuantidade() == carrinho.getQuantidade() && Double.compare(getValor(), carrinho.getValor()) == 0 && Double.compare(getValorTotal(), carrinho.getValorTotal()) == 0 && Objects.equals(getId(), carrinho.getId()) && Objects.equals(getCategoria(), carrinho.getCategoria()) && Objects.equals(getNome(), carrinho.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCategoria(), getNome(), getQuantidade(), getValor(), getValorTotal());
    }


}
