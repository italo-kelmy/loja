package com.loja_de_eletronicos.loja.Component;

import org.springframework.stereotype.Component;

@Component
public class CarrinhoRequest {
    private Long produtoId;
    private int quantidade;





    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
