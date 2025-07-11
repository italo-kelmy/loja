package com.loja_de_eletronicos.loja.Service;

import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutosService {
    private ProdutosRepository repository;

    @Autowired
    public ProdutosService(ProdutosRepository repository) {
        this.repository = repository;
    }

    public List<ProdutosEletronicos> produtosEletronicosList() {
        return repository.findAll();
    }


    public ResponseEntity<?> pesquisarPorNome(String nome) {
        Optional<ProdutosEletronicos> produto = repository.findByNome(nome);
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Produto não encontrado");
        }

    }


    public ResponseEntity<?> pesquisarPorCategoria(String categoria) {
        List<ProdutosEletronicos> produtosEletronicos = repository.findByCategoria(categoria);
        if (produtosEletronicos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não disponível");
        }

        return ResponseEntity.ok(produtosEletronicos);
    }


}
