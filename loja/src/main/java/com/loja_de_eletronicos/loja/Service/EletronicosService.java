package com.loja_de_eletronicos.loja.Service;

import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Entity.Usuarios;
import com.loja_de_eletronicos.loja.Repository.EletronicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EletronicosService {

    private final EletronicosRepository repository;
    private final CarrinhoService carrinhoService;

    @Autowired
    public EletronicosService(EletronicosRepository repository, CarrinhoService carrinhoService) {
        this.repository = repository;
        this.carrinhoService = carrinhoService;
    }

    public List<ProdutosEletronicos> produtosEletronicos() {
        return repository.findAll();
    }


    public ResponseEntity<?> pesquisarPorNome(String nome) {
        ProdutosEletronicos produtosEletronicos = repository.findByNome(nome).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        return ResponseEntity.ok(produtosEletronicos);
    }


    public ResponseEntity<?> pesquisarPorCategoria(String categoria) {
        List<ProdutosEletronicos> produtosEletronicos = repository.findByCategoria(categoria);

        if (produtosEletronicos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Categoria não dispoível");
        }

        return ResponseEntity.ok(produtosEletronicos);
    }




    public ResponseEntity<?> limparCarrinho() {
        carrinhoService.limparCarrinho();
        return ResponseEntity.ok("Carrinho limpado com sucesso");
    }


}
