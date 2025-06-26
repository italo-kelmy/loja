package com.loja_de_eletronicos.loja.Service;

import com.loja_de_eletronicos.loja.Entity.Carrinho;
import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Repository.CarrinhoRepository;
import com.loja_de_eletronicos.loja.Repository.EletronicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarrinhoService {
    private final CarrinhoRepository carrinhorepository;
    private final EletronicosRepository repository;


    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhorepository, EletronicosRepository repository) {
        this.carrinhorepository = carrinhorepository;
        this.repository = repository;
    }

    public ResponseEntity<?> adicionarNoCarrinho(Long produtoId, int quantidade) {
        ProdutosEletronicos produto = repository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        Carrinho carrinho = new Carrinho();
        carrinho.setNome(produto.getNome());
        carrinho.setValor(produto.getValor());
        carrinho.setQuantidade(quantidade);
        carrinho.setCategoria(produto.getCategoria());
        carrinho.setValorTotal(produto.getValor() * quantidade);

        // Salva no banco
        carrinhorepository.save(carrinho);

        return ResponseEntity.ok("Produto adicionado ao carrinho");
    }


    public void limparCarrinho() {
        carrinhorepository.deleteAll();
    }

    public ResponseEntity<?> mostrarCarrinho() {
        List<Carrinho> carrinhoList = carrinhorepository.findAll();

        if (carrinhoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Carrinho está vázio");
        }

        for (Carrinho carrinho1 : carrinhoList) {
            return ResponseEntity.ok(carrinho1);

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao carregar o carrinho");
    }


    public ResponseEntity<?> comprarProduto(ProdutosEletronicos eletronicos, int quantidade) {
        ProdutosEletronicos produtosEletronicos = repository.findById(eletronicos.getId()).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (quantidade <= 0) {
            return ResponseEntity.badRequest().body("Quantidade inválida");
        }

        if (produtosEletronicos.getQuantidade() < quantidade) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Quantidade indisponivel");
        }


        int comprado = produtosEletronicos.getQuantidade() - quantidade;

        produtosEletronicos.setQuantidade(comprado);
        repository.save(produtosEletronicos);

        return ResponseEntity.ok("Compra efetuada com sucesso");
    }


}
