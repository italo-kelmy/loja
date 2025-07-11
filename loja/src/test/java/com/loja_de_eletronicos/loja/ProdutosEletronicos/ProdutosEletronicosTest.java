package com.loja_de_eletronicos.loja.ProdutosEletronicos;


import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Repository.ProdutosRepository;
import com.loja_de_eletronicos.loja.Service.ProdutosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class ProdutosEletronicosTest {

    @Mock
    private ProdutosRepository produtosRepository;

    @InjectMocks
    private ProdutosService service;

    @BeforeEach
    void testUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProdutos_deveRetornarOsProdutos() {

        ProdutosEletronicos produtos = new ProdutosEletronicos(1L, "Smartphone", "Iphone 15", 18, 10000.0, "Iphone potente e uma ótima qualidade de câmera");
        ProdutosEletronicos produto2 = new ProdutosEletronicos(2L, "Notebook", "MacBook Pro", 5, 12000.0, "Notebook potente");
        Mockito.when(produtosRepository.findAll()).thenReturn(Arrays.asList(produtos, produto2));

        List<ProdutosEletronicos> produtosList = service.produtosEletronicosList();
        Assertions.assertEquals(2, produtosList.size());
        Mockito.verify(produtosRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testBuscarPorNome_QuandoProdutoExiste_DeveRetornarProduto() {
        String nome = "Pc Gamer Full White Ryzen 5 5600GT";
        ProdutosEletronicos produtos = new ProdutosEletronicos(1L, "Setup gamer",
                nome, 25, 3700.0, "Apresentamos o PC " +
                "Gamer Full White Neologic - NLI89950 , uma máquina projetada para oferecer desempenho e estética impecáveis. Equipado com o" +
                " poderoso Ryzen 5 5600GT , 16GB de memória RAM DDR4 de 3200MHz e SSD M.2 de 480GB ");

        Mockito.when(produtosRepository.findByNome(nome)).thenReturn(Optional.of(produtos));


        ResponseEntity<?> resposta = service.pesquisarPorNome(nome);
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertNotNull(resposta.getBody());
        Mockito.verify(produtosRepository, Mockito.times(1)).findByNome(nome);
    }

    @Test
    void testBuscarPorNome_QuandoProdutoNaoExiste_DeveRetornarErro() {
        String nome = "Não existe";
        Mockito.when(produtosRepository.findByNome(nome)).thenReturn(Optional.empty());

        ResponseEntity<?> resposta = service.pesquisarPorNome(nome);
        Assertions.assertEquals(HttpStatus.CONFLICT, resposta.getStatusCode());
        Assertions.assertEquals("Produto não encontrado", resposta.getBody());
        Mockito.verify(produtosRepository, Mockito.times(1)).findByNome(nome);
    }


    @Test
    void testBuscarPorCategoria_QuandoProdutoExiste_DeveRetornarCategoria() {
        String categoria = "Placa de Vídeo";
        ProdutosEletronicos produtos = new ProdutosEletronicos(1L, categoria, "AMD Radeon RX 7900 XTX", 13, 7999.0, "Alta performance com arquitetura RDNA 3");


        Mockito.when(produtosRepository.findByCategoria(categoria)).thenReturn(Collections.singletonList(produtos));
        ResponseEntity<?> resposta = service.pesquisarPorCategoria(categoria);
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertNotNull(resposta.getBody());
        Mockito.verify(produtosRepository, Mockito.times(1)).findByCategoria(categoria);
    }



}
