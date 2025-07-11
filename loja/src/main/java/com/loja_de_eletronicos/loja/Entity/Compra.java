package com.loja_de_eletronicos.loja.Entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;

@Entity
public class Compra {
    @Id
    private Long id;
    private int quantidade;


    public Compra() {
    }

    public Compra(Long id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
