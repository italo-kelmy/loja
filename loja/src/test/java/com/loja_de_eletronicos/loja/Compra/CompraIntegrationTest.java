package com.loja_de_eletronicos.loja.Compra;

import com.loja_de_eletronicos.loja.Entity.Compra;
import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Repository.CompraRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraIntegrationTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CompraRepository repository;


    @AfterEach
    void testDows(){
        entityManager.clear();
    }


    @Test
    void testSucessoAoComprarOProduto(){



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


           produtos.setQuantidade(produtos.getQuantidade() - 2);
            entityManager.merge(produtos);

            Compra item1 = new Compra(
                    produtos.getId(), 2
            );

            entityManager.persist(item1);
            entityManager.flush();

        Optional<Compra>produtoID = repository.findById(item1.getId());
        Assertions.assertTrue(produtoID.isPresent());


        Compra compra = produtoID.get();
        Assertions.assertEquals(produtos.getId(), compra.getId());
        Assertions.assertEquals(2, compra.getQuantidade());

        ProdutosEletronicos produtoAtualizado = entityManager.find(ProdutosEletronicos.class, produtos.getId());
        Assertions.assertEquals(7, produtoAtualizado.getQuantidade());


    }



}
