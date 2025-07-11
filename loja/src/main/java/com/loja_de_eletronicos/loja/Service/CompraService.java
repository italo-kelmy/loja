package com.loja_de_eletronicos.loja.Service;

import com.loja_de_eletronicos.loja.Entity.Compra;
import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Repository.CompraRepository;
import com.loja_de_eletronicos.loja.Repository.ProdutosRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompraService {
    private final CompraRepository compraRepository;
    private final ProdutosRepository produtosRepository;

    public CompraService(CompraRepository compraRepository, ProdutosRepository produtosRepository) {
        this.compraRepository = compraRepository;
        this.produtosRepository = produtosRepository;
    }

    @Transactional
    public ResponseEntity<?> comprar(Long id, int quantidade) {

        Optional<ProdutosEletronicos> produtoNaoEncontrado = produtosRepository.findById(id);

        if (produtoNaoEncontrado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        ProdutosEletronicos produtosEletronicos = produtoNaoEncontrado.get();
        Compra compra = new Compra(produtosEletronicos.getId(), produtosEletronicos.getQuantidade());


        if (produtosEletronicos.getQuantidade() < quantidade) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Quantidade insuficiente");
        }

        int comprado = produtosEletronicos.getQuantidade() - quantidade;

        produtosEletronicos.setQuantidade(comprado);
        compraRepository.save(compra);
        produtosRepository.save(produtosEletronicos);
        return ResponseEntity.ok("Compra finalizada com sucesso");
    }

}
