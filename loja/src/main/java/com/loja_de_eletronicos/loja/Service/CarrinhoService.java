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

    public void adicionarNoCarrinho(ProdutosEletronicos produto, int quantidade) {

        Carrinho carrinho = new Carrinho(
                produto.getNome(),
                produto.getValor(),
                quantidade,
                produto.getCategoria()
        );


        // Salva no banco
        carrinhorepository.save(carrinho);

    }


    public void limparCarrinho() {
        carrinhorepository.deleteAll();
    }

    public ResponseEntity<?> mostrarCarrinho() {
        List<Carrinho> carrinhoList = carrinhorepository.findAll();

        if (carrinhoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Carrinho está vázio");
        }


        return ResponseEntity.ok(carrinhoList);
    }


    public ResponseEntity<?> comprarProduto(ProdutosEletronicos produtoId, int quantidade) {
        ProdutosEletronicos produtosEletronicos = repository.findById(produtoId.getId()).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (quantidade <= 0) {
            return ResponseEntity.badRequest().body("Quantidade inválida");
        }

        if (produtosEletronicos.getQuantidade() < quantidade) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Quantidade indisponivel");
        }


        produtosEletronicos.setQuantidade(produtosEletronicos.getQuantidade() - quantidade);

        repository.save(produtosEletronicos);

        return ResponseEntity.ok("Compra efetuada com sucesso");
    }


}
