package com.loja_de_eletronicos.loja.Service;

import com.loja_de_eletronicos.loja.Entity.Carrinho;
import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Repository.CarrinhoRepository;
import com.loja_de_eletronicos.loja.Repository.ProdutosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrinhoService {

    private final CarrinhoRepository repository;
    private final ProdutosRepository produtosRepository;


    public CarrinhoService(CarrinhoRepository repository, ProdutosRepository produtosRepository) {
        this.repository = repository;
        this.produtosRepository = produtosRepository;
    }

    public ResponseEntity<?> adicionarNoCarrinho(ProdutosEletronicos produtosEletronicos, int quantidade) {

        ProdutosEletronicos carrinhos = produtosRepository.findById(produtosEletronicos.getId()).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        Carrinho carrinho = new Carrinho
                (
                        carrinhos.getCategoria(),
                       carrinhos.getNome(),
                        quantidade,
                        carrinhos.getValor()
                );

        repository.save(carrinho);
        return ResponseEntity.ok("Adicionado com sucesso no carrinho");
    }

    public ResponseEntity<?> mostrarCarrinho() {

        List<Carrinho> carrinho = repository.findAll();
        if (carrinho.isEmpty()) {
            return ResponseEntity.ok("Carrinho está vázio");
        }
        return ResponseEntity.ok(carrinho);
    }

    public ResponseEntity<?> limparCarrinho() {


        repository.deleteAll();
        return ResponseEntity.ok("Itens do carrinho removido com sucesso");
    }


}
