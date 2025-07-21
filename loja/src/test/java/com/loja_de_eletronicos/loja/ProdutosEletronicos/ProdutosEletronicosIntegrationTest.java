package com.loja_de_eletronicos.loja.ProdutosEletronicos;

import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Repository.ProdutosRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ProdutosEletronicosIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ProdutosRepository repository;

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void testSalvarProdutos_DevePersistirNoBanco() {
        ProdutosEletronicos produtos = new ProdutosEletronicos();
        produtos.setCategoria("Processador");
        produtos.setNome("AMD Ryzen 9 7950X");
        produtos.setQuantidade(9);
        produtos.setValor(3699.00);
        produtos.setDescricao("Alto desempenho com 16 núcleos e 32 threads");
        entityManager.persist(produtos);


        ProdutosEletronicos produtos2 = new ProdutosEletronicos();
        produtos2.setCategoria("Memória RAM");
        produtos2.setNome("Corsair Vengeance 32GB DDR5 6000MHz");
        produtos2.setQuantidade(26);
        produtos2.setValor(999.00);
        produtos2.setDescricao("Alto desempenho para jogos e criação");
        entityManager.persist(produtos2);
        entityManager.flush();


        List<ProdutosEletronicos> jogos = repository.findAll();
        Assertions.assertEquals(2, jogos.size());
    }

    @Test
    void testBuscarPorNome_QuandoExistir_DeveRetornarProdutosEletronicos() {
        ProdutosEletronicos produtos = new ProdutosEletronicos();
        produtos.setCategoria("Processador");
        produtos.setNome("AMD Ryzen 9 7950X");
        produtos.setQuantidade(9);
        produtos.setValor(3699.00);
        produtos.setDescricao("Alto desempenho com 16 núcleos e 32 threads");
        entityManager.persist(produtos);


        ProdutosEletronicos produtos2 = new ProdutosEletronicos();
        produtos2.setCategoria("Memória RAM");
        produtos2.setNome("Corsair Vengeance 32GB DDR5 6000MHz");
        produtos2.setQuantidade(26);
        produtos2.setValor(999.00);
        produtos2.setDescricao("Alto desempenho para jogos e criação");
        entityManager.persist(produtos2);
        entityManager.flush();

        Optional<ProdutosEletronicos> buscarPorNome = repository.findByNome("AMD Ryzen 9 7950X");
        Assertions.assertNotNull(buscarPorNome);
        Assertions.assertTrue(buscarPorNome.isPresent());
    }

    @Test
    void testBuscarPorCategoria_QuandoExistir() {
        ProdutosEletronicos produtos = new ProdutosEletronicos();
        produtos.setCategoria("Processador");
        produtos.setNome("AMD Ryzen 9 7950X");
        produtos.setQuantidade(9);
        produtos.setValor(3699.00);
        produtos.setDescricao("Alto desempenho com 16 núcleos e 32 threads");
        entityManager.persist(produtos);


        ProdutosEletronicos produtos2 = new ProdutosEletronicos();
        produtos2.setCategoria("Memória RAM");
        produtos2.setNome("Corsair Vengeance 32GB DDR5 6000MHz");
        produtos2.setQuantidade(26);
        produtos2.setValor(999.00);
        produtos2.setDescricao("Alto desempenho para jogos e criação");
        entityManager.persist(produtos2);
        entityManager.flush();

        List<ProdutosEletronicos> buscarPorCategoria = repository.findByCategoria("Memória RAM");
        Assertions.assertNotNull(buscarPorCategoria);
        Assertions.assertEquals(1, buscarPorCategoria.size());
    }


}
