package com.loja_de_eletronicos.loja.Service;

import com.loja_de_eletronicos.loja.Entity.Carrinho;
import com.loja_de_eletronicos.loja.Entity.Compra;
import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Entity.Usuarios;
import com.loja_de_eletronicos.loja.Repository.CompraRepository;
import com.loja_de_eletronicos.loja.Repository.ProdutosRepository;
import com.loja_de_eletronicos.loja.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    private final ProdutosRepository produtosRepository;
    private final UsuariosRepository repository;

    @Autowired
    public CompraService(ProdutosRepository produtosRepository, UsuariosRepository repository) {
        this.produtosRepository = produtosRepository;
        this.repository = repository;
    }


    public ResponseEntity<?> compraEfetuada(Compra compra, int quantidade) {


        ProdutosEletronicos produtosEletronicos = produtosRepository.findById(compra.getId()).orElseThrow(()
                -> new IllegalArgumentException("Id não encontrado"));


        if (produtosEletronicos.getQuantidade() < quantidade) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estoque insufiente");
        }
        int comprado = produtosEletronicos.getQuantidade() - quantidade;
        produtosEletronicos.setQuantidade(comprado);
        produtosRepository.save(produtosEletronicos);

        return ResponseEntity.ok("Compra efetuada com sucesso");
    }

}
