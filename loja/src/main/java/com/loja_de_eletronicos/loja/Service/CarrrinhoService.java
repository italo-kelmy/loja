package com.loja_de_eletronicos.loja.Service;

import com.loja_de_eletronicos.loja.Entity.Carrinho;
import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Repository.CarrinhoRepository;
import com.loja_de_eletronicos.loja.Repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CarrrinhoService {
    private final CarrinhoRepository carrinhoRepository;
    private final ProdutosRepository produtosRepository;

    @Autowired
    public CarrrinhoService(CarrinhoRepository carrinhoRepository, ProdutosRepository produtosRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.produtosRepository = produtosRepository;
    }


    public ResponseEntity<?> adicionarNoCarrinho(Long id, int quantidade) {

        Optional<ProdutosEletronicos> optionalProduto = produtosRepository.findById(id);

        if (optionalProduto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        ProdutosEletronicos produtos = optionalProduto.get();
        Carrinho carrinho = new Carrinho(
                produtos.getId(),
                produtos.getCategoria(),
                produtos.getNome(),
                quantidade,
                produtos.getValor() * quantidade

        );


        if (produtos.getQuantidade() < quantidade) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Quantidade insuficiente");
        }

        carrinhoRepository.save(carrinho);
        produtosRepository.save(produtos);
        return ResponseEntity.ok("Adicionado no carrinho com sucesso");
    }

    public ResponseEntity<?> limparCarrinho() {
        carrinhoRepository.deleteAll();
        return ResponseEntity.ok("Carrinho limpado com sucesso");
    }

    public ResponseEntity<?> mostrarCarrinho() {
        List<Carrinho> carrinhoList = carrinhoRepository.findAll();

        if (carrinhoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrinho está vázio");
        }

        return ResponseEntity.ok(carrinhoList);
    }


}
