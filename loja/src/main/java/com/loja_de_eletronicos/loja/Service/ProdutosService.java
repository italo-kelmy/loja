package com.loja_de_eletronicos.loja.Service;

import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosService {

    private final ProdutosRepository repository;

    @Autowired
    public ProdutosService(ProdutosRepository repository) {
        this.repository = repository;
    }

    public List<ProdutosEletronicos> produtos() {
        return repository.findAll();
    }


    public ResponseEntity<?> buscarPorNome(ProdutosEletronicos produtos) {

        List<ProdutosEletronicos> produtosEletronicos = repository.findByNome(produtos.getNome());

        if (produtosEletronicos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Item não encontrado");
        }

        return ResponseEntity.ok(produtosEletronicos);
    }


    public ResponseEntity<?> buscarPorCategoria(ProdutosEletronicos produtos) {

        List<ProdutosEletronicos> produtosEletronicos = repository.findByCategoria(produtos.getCategoria());

        if (produtosEletronicos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Categoria não disponível");
        }

        return ResponseEntity.ok(produtosEletronicos);
    }











}
