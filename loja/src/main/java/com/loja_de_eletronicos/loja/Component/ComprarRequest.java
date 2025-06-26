package com.loja_de_eletronicos.loja.Component;

import org.springframework.stereotype.Component;

@Component
public class ComprarRequest {
    private Long id;
    private int quantidade;

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
