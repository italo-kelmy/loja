package com.loja_de_eletronicos.loja.Controller;

import com.loja_de_eletronicos.loja.Entity.Compra;
import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class CompraController {
    private final CompraService service;

    @Autowired
    public CompraController(CompraService service) {
        this.service = service;
    }

    @PostMapping("/finalizarCompra")
    public ResponseEntity<?> comprarProduto(@RequestBody Compra produtosEletronicos) {
        int quantidade = produtosEletronicos.getQuantidade();
        return service.comprar(produtosEletronicos.getId(), quantidade);
    }

}
