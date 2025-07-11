package com.loja_de_eletronicos.loja.Controller;

import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutosController {
    private ProdutosService service;

    @Autowired
    public ProdutosController(ProdutosService service) {
        this.service = service;
    }

    @GetMapping("/produtos")
    public List<ProdutosEletronicos>lista(){
        return service.produtosEletronicosList();
    }

    @GetMapping("/produtos/{nome}")
    public ResponseEntity<?>pesquisarPorNome(@PathVariable String nome){
        return service.pesquisarPorNome(nome);
    }

    @GetMapping("/produtos/categoria/{categoria}")
    public ResponseEntity<?>pesquisarPorCategoria(@PathVariable String categoria){
        return service.pesquisarPorCategoria(categoria);
    }

}
