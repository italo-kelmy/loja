package com.loja_de_eletronicos.loja.Controller;

import com.loja_de_eletronicos.loja.Component.CarrinhoRequest;
import com.loja_de_eletronicos.loja.Entity.Carrinho;
import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Entity.Usuarios;
import com.loja_de_eletronicos.loja.Service.CarrinhoService;
import com.loja_de_eletronicos.loja.Service.EletronicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EletronicosController {

    private final EletronicosService service;
    private final CarrinhoService carrinhoProdutos;
    private final CarrinhoRequest request;

    @Autowired
    public EletronicosController(EletronicosService service, CarrinhoService carrinhoProdutos, CarrinhoRequest request) {
        this.service = service;
        this.carrinhoProdutos = carrinhoProdutos;
        this.request = request;
    }

    @GetMapping("/produtos")
    public List<ProdutosEletronicos> listaDeProdutos() {
        return service.produtosEletronicos();
    }


    @GetMapping("/produtos/{nome}")
    public ResponseEntity<?> pesquisarPorNome(@PathVariable String nome) {
        return service.pesquisarPorNome(nome);
    }

    @GetMapping("/produtos/categoria/{categoria}")
    public ResponseEntity<?> pesquisaPorCategoria(@PathVariable String categoria) {
        return service.pesquisarPorCategoria(categoria);
    }

    @PostMapping("/produtos/adicionarNoCarrinho")
    public ResponseEntity<?> adicionarCarrinho(@RequestBody ProdutosEletronicos produtosEletronicos) {

        return carrinhoProdutos.adicionarNoCarrinho(request.getProdutoId(), request.getQuantidade());
    }

    @GetMapping("/produtos/verCarrinho")
    public ResponseEntity<?> verCarrinho() {
        return carrinhoProdutos.mostrarCarrinho();
    }

    @GetMapping("/produtos/clean")
    public ResponseEntity<?> limparCarrinho() {
        return service.limparCarrinho();
    }

    @PostMapping("/produtos/comprar")
    public ResponseEntity<?> finalizarCompra(@RequestBody ProdutosEletronicos produtosEletronicos) {
        int quantidade = produtosEletronicos.getQuantidade();
        return carrinhoProdutos.comprarProduto(produtosEletronicos, quantidade);
    }

}
