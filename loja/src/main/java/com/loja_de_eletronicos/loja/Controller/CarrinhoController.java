package com.loja_de_eletronicos.loja.Controller;
import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Service.CarrrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/produtos")
public class CarrinhoController {
    private final CarrrinhoService service;

    @Autowired
    public CarrinhoController(CarrrinhoService service) {
        this.service = service;
    }

    @PostMapping("/adicionarNoCarrinho")
    public ResponseEntity<?> adicionarNoCarrinho(@RequestBody ProdutosEletronicos produtos) {
        int quantidade = produtos.getQuantidade();
        return service.adicionarNoCarrinho(produtos.getId(), quantidade);
    }

    @GetMapping("/mostrarCarrinho")
    public ResponseEntity<?> verCarrinho(){
        return service.mostrarCarrinho();
    }

    @GetMapping("/clean")
    public ResponseEntity<?> limparCarrinho(){
        return service.limparCarrinho();
    }

}
