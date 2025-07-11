package com.loja_de_eletronicos.loja.Carrinho;

import com.loja_de_eletronicos.loja.Entity.Carrinho;
import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Repository.CarrinhoRepository;
import com.loja_de_eletronicos.loja.Repository.ProdutosRepository;
import com.loja_de_eletronicos.loja.Service.CarrrinhoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.Optional;


public class CarrinhoTest {

    @Mock
    private CarrinhoRepository carrinhoRepository;
    @Mock
    private ProdutosRepository produtosRepository;


    @InjectMocks
    private CarrrinhoService service;


    @BeforeEach
    void testUP() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCarrinho_RetornarCarrinhoAdicionado() {
        Long id = 1L;
        int quantidade = 2;
        ProdutosEletronicos produtos = new ProdutosEletronicos(id, "Processador", "AMD Radeon RX 7900 XTX", 13, 7999.0, "Alta performance com arquitetura RDNA 3");
        Mockito.when(produtosRepository.findById(id)).thenReturn(Optional.of(produtos));

        Carrinho carrinho = new Carrinho();
        carrinho.setId(id);
        carrinho.setQuantidade(quantidade);

        Mockito.when(produtosRepository.save(Mockito.any(ProdutosEletronicos.class))).thenReturn(produtos);
        Mockito.when(carrinhoRepository.save(Mockito.any(Carrinho.class))).thenReturn(carrinho);
        ResponseEntity<?> resposta = service.adicionarNoCarrinho(id, quantidade);
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals("Adicionado no carrinho com sucesso", resposta.getBody());
    }

    @Test
    void testCarrinho_Erro_ProdutoNaoEncontrado_Ao_AdicionarNocarrinho() {
        Long id = 2L;
        int quantidade = 3;
        Mockito.when(produtosRepository.findById(id)).thenReturn(Optional.empty());
        Mockito.when(carrinhoRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<?> resposta = service.adicionarNoCarrinho(id, quantidade);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        Assertions.assertEquals("Produto não encontrado", resposta.getBody());
        Mockito.verify(produtosRepository, Mockito.times(1)).findById(id);
    }


    @Test
    void testCarrinho_Retorar_CarrinhoLimpo() {

        Mockito.when(carrinhoRepository.save(Mockito.any())).thenReturn(Optional.empty());

        ResponseEntity<?> resposta = service.limparCarrinho();
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals("Carrinho limpado com sucesso", resposta.getBody());
        Mockito.verify(carrinhoRepository, Mockito.times(1)).deleteAll();
    }


    @Test
    void testCarrinho_retornar_ProdutosAdicionadosNoCarrinho() {
        int quantidade = 2;
        ProdutosEletronicos produtos = new ProdutosEletronicos(1L, "Smartphone", "Iphone 15", 18, 10000.0, "Iphone potente e uma ótima qualidade de câmera");



        Carrinho carrinho = new Carrinho(
                produtos.getId(),
                produtos.getCategoria(),
                produtos.getNome(),
                quantidade,
                produtos.getValor()
        );


        Mockito.when(carrinhoRepository.findAll()).thenReturn(Collections.singletonList(carrinho));
        ResponseEntity<?> resposta = service.mostrarCarrinho();
        Assertions.assertEquals(HttpStatus.OK,resposta.getStatusCode());
        Mockito.verify(carrinhoRepository, Mockito.times(1)).findAll();
    }


    @Test
    void testCarrinho_Mensagem_QuandoCarrinhoEstiverVazio(){

        Mockito.when(carrinhoRepository.save(Mockito.any())).thenReturn(Optional.empty());

        ResponseEntity<?>resposta = service.mostrarCarrinho();
        Assertions.assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        Assertions.assertEquals("Carrinho está vázio", resposta.getBody());
        Mockito.verify(carrinhoRepository, Mockito.times(1)).findAll();
    }







}
