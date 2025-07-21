package com.loja_de_eletronicos.loja.Carrinho;

import com.loja_de_eletronicos.loja.Entity.Carrinho;
import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Repository.CarrinhoRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CarrinhoIntegrationTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CarrinhoRepository carrinhoRepository;



    @AfterEach
    void tesDown() {
        entityManager.clear();
    }


    @Test
    void dadoProdutoPersistido_quandoAdicionadoAoCarrinho_entaoDeveEstarNoCarrinho() {

        ProdutosEletronicos produtos = new ProdutosEletronicos();

        produtos.setCategoria("Processador");
        produtos.setNome("AMD Ryzen 9 7950X");
        produtos.setQuantidade(9);
        produtos.setValor(3699.00);
        entityManager.persist(produtos);


        ProdutosEletronicos produtos2 = new ProdutosEletronicos();
        produtos2.setCategoria("Memória RAM");
        produtos2.setNome("Corsair Vengeance 32GB DDR5 6000MHz");
        produtos2.setQuantidade(26);
        produtos2.setValor(999.00);

        entityManager.persist(produtos2);
        entityManager.flush();


        Carrinho carrinho = new Carrinho(
                produtos.getId(), produtos.getCategoria(), produtos.getNome(), 1, produtos.getValor()
        );

        entityManager.persist(carrinho);
        entityManager.flush();


        Optional<Carrinho> carrinhoId = carrinhoRepository.findById(produtos.getId());
        Assertions.assertTrue(carrinhoId.isPresent());
    }

    @Test
    void testParaQuandoLimparOCarrinho() {

        ProdutosEletronicos produtos = new ProdutosEletronicos();

        produtos.setCategoria("Processador");
        produtos.setNome("AMD Ryzen 9 7950X");
        produtos.setQuantidade(9);
        produtos.setValor(3699.00);
        entityManager.persist(produtos);


        ProdutosEletronicos produtos2 = new ProdutosEletronicos();
        produtos2.setCategoria("Memória RAM");
        produtos2.setNome("Corsair Vengeance 32GB DDR5 6000MHz");
        produtos2.setQuantidade(26);
        produtos2.setValor(999.00);

        entityManager.persist(produtos2);
        entityManager.flush();


        Carrinho item1 = new Carrinho(
                produtos.getId(), produtos.getCategoria(), produtos.getNome(), 1, produtos.getValor()
        );
        Carrinho item2 = new Carrinho(produtos2.getId(), produtos2.getCategoria(), produtos2.getNome(), 1, produtos2.getValor());
        entityManager.persist(item1);
        entityManager.persist(item2);
        entityManager.flush();

        carrinhoRepository.deleteAll();
        List<Carrinho> todosItens = carrinhoRepository.findAll();
        Assertions.assertTrue(todosItens.isEmpty(), "O carrinho deveria estar vazio após a limpeza");
    }


    @Test
    void testParaMostrar_Os_Produtos_No_Carrinho() {

        ProdutosEletronicos produtos = new ProdutosEletronicos();

        produtos.setCategoria("Processador");
        produtos.setNome("AMD Ryzen 9 7950X");
        produtos.setQuantidade(9);
        produtos.setValor(3699.00);
        entityManager.persist(produtos);


        ProdutosEletronicos produtos2 = new ProdutosEletronicos();
        produtos2.setCategoria("Memória RAM");
        produtos2.setNome("Corsair Vengeance 32GB DDR5 6000MHz");
        produtos2.setQuantidade(26);
        produtos2.setValor(999.00);

        entityManager.persist(produtos2);
        entityManager.flush();


        Carrinho item1 = new Carrinho(
                produtos.getId(), produtos.getCategoria(), produtos.getNome(), 1, produtos.getValor()
        );
        entityManager.persist(item1);
        Carrinho item2 = new Carrinho(
                produtos2.getId(), produtos2.getCategoria(), produtos2.getNome(), 1, produtos2.getValor()
        );
        entityManager.persist(item2);
        entityManager.flush();

        List<Carrinho> todosItem = carrinhoRepository.findAll();
        Assertions.assertEquals(2, todosItem.size());
    }


}
