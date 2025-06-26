package com.loja_de_eletronicos.loja.Controller;

import com.loja_de_eletronicos.loja.Component.CarrinhoRequest;

import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;

import com.loja_de_eletronicos.loja.Service.CarrinhoService;
import com.loja_de_eletronicos.loja.Service.EletronicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EletronicosController {

    private final EletronicosService service;
    private final CarrinhoService carrinhoProdutos;

    @Autowired
    public EletronicosController(EletronicosService service, CarrinhoService carrinhoProdutos) {
        this.service = service;
        this.carrinhoProdutos = carrinhoProdutos;
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
    public ResponseEntity<?> adicionarCarrinho(@RequestBody CarrinhoRequest request) {

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
    public ResponseEntity<?> finalizarCompra(@RequestBody ProdutosEletronicos comprarRequest) {

        int quantidade = comprarRequest.getQuantidade();
        return carrinhoProdutos.comprarProduto(comprarRequest, quantidade);
    }

}
