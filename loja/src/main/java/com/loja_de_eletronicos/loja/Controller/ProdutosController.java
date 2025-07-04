package com.loja_de_eletronicos.loja.Controller;
import com.loja_de_eletronicos.loja.Entity.Compra;
import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Service.CarrinhoService;
import com.loja_de_eletronicos.loja.Service.CompraService;
import com.loja_de_eletronicos.loja.Service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutosController {

    private final ProdutosService service;
    private final CarrinhoService carrinhoService;
    private final CompraService compraService;


    @Autowired
    public ProdutosController(ProdutosService service, CarrinhoService carrinhoService, CompraService compraService) {
        this.service = service;
        this.carrinhoService = carrinhoService;
        this.compraService = compraService;
    }

    @GetMapping("/produtos")
    public List<ProdutosEletronicos> produtosEletronicos() {
        return service.produtos();
    }


    @GetMapping("/produtos/{nome}")
    public ResponseEntity<?> pesquisarPorNome(ProdutosEletronicos nome) {
        return service.buscarPorNome(nome);
    }

    @GetMapping("/produtos/categoria/{categoria}")
    public ResponseEntity<?> pesquisarPorCategoria(ProdutosEletronicos categoria) {
        return service.buscarPorCategoria(categoria);
    }


    @PostMapping("/produtos/AdicionarNoCarrinho")
    public ResponseEntity<?> adicionarNoCarrinho(@RequestBody ProdutosEletronicos produtosEletronicos) {
        int quantidade = produtosEletronicos.getQuantidade();
        return carrinhoService.adicionarNoCarrinho(produtosEletronicos, quantidade);
    }

    @GetMapping("/produtos/verCarrinho")
    public ResponseEntity<?> verCarrinho() {
        return carrinhoService.mostrarCarrinho();
    }

    @GetMapping("/produtos/clean")
    public ResponseEntity<?> limparCarrinho() {
        return carrinhoService.limparCarrinho();
    }


    @PostMapping("/produtos/finalizarCompra")
    public ResponseEntity<?> compraFinalizada(@RequestBody  Compra compra) {
        int quantidade = compra.getQuantidade();
        return compraService.compraEfetuada(compra, quantidade);
    }

}
