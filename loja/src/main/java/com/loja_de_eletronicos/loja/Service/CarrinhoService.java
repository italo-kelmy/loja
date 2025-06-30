package com.loja_de_eletronicos.loja.Service;

import com.loja_de_eletronicos.loja.Entity.Carrinho;
import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Entity.Usuarios;
import com.loja_de_eletronicos.loja.Repository.CarrinhoRepository;
import com.loja_de_eletronicos.loja.Repository.EletronicosRepository;
import com.loja_de_eletronicos.loja.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarrinhoService {
    private final CarrinhoRepository carrinhorepository;
    private final EletronicosRepository repository;
    private final UsuariosRepository usuariosRepository;


    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhorepository, EletronicosRepository repository, UsuariosRepository usuariosRepository) {
        this.carrinhorepository = carrinhorepository;
        this.repository = repository;
        this.usuariosRepository = usuariosRepository;
    }

    public void adicionarNoCarrinho(ProdutosEletronicos produto, int quantidade) {
        // Recupera o nome do usuário autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Busca o usuário no banco de dados
        Usuarios usuario = usuariosRepository.findByUsuario(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Carrinho carrinho = new Carrinho(
                produto.getNome(),
                produto.getValor(),
                quantidade,
                produto.getCategoria(),
                usuario
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

        // Recupera o nome do usuário autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Busca o usuário no banco de dados
        Usuarios usuario = usuariosRepository.findByUsuario(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        Carrinho carrinho = new Carrinho(
                produtosEletronicos.getNome(),
                produtosEletronicos.getValor(),
                quantidade,
                produtosEletronicos.getCategoria(),
                usuario
        );

        if (quantidade <= 0) {
            return ResponseEntity.badRequest().body("Quantidade inválida");
        }

        if (produtosEletronicos.getQuantidade() < quantidade) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Quantidade indisponivel");
        }

        produtosEletronicos.setQuantidade(produtosEletronicos.getQuantidade() - quantidade);

        carrinhorepository.save(carrinho);
        repository.save(produtosEletronicos);
        return ResponseEntity.ok("Compra efetuada com sucesso");
    }


}
