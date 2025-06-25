package com.loja_de_eletronicos.loja.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "carrinho")
public class Carrinho {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double valor;
    private int quantidade;
    private String categoria;
    private double valorTotal;

    @ManyToOne(fetch = FetchType.LAZY)  // define o relacionamento
    @JoinColumn(name = "usuario_id")    // nome da coluna FK no BD
    private Usuarios usuario;

    public Carrinho() {

    }

    public Carrinho(Long id, String nome, double valor, int quantidade, String categoria) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.valorTotal = valor * quantidade;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }



    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Carrinho carrinho)) return false;
        return Double.compare(getValor(), carrinho.getValor()) == 0 && getQuantidade() == carrinho.getQuantidade() && Double.compare(getValorTotal(), carrinho.getValorTotal()) == 0 && Objects.equals(getId(), carrinho.getId()) && Objects.equals(getNome(), carrinho.getNome()) && Objects.equals(getCategoria(), carrinho.getCategoria());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getValor(), getQuantidade(), getCategoria(), getValorTotal());
    }
}
