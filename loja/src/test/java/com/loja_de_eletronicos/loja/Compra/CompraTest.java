package com.loja_de_eletronicos.loja.Compra;
import com.loja_de_eletronicos.loja.Entity.Compra;
import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import com.loja_de_eletronicos.loja.Repository.CompraRepository;
import com.loja_de_eletronicos.loja.Repository.ProdutosRepository;
import com.loja_de_eletronicos.loja.Service.CompraService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class CompraTest {

    @Mock
    private CompraRepository compraRepository;
    @Mock
    private ProdutosRepository produtosRepository;

    @InjectMocks
    private CompraService service;


    @BeforeEach
    void testUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCompra_ComprarProdutoDaLoja() {
        Long id = 1L;
        int quantidade = 3;

        ProdutosEletronicos produto = new ProdutosEletronicos(id, "Smartphone", "Iphone 15", 18, 10000.0, "Iphone potente e uma ótima qualidade de câmera");
        Mockito.when(produtosRepository.findById(id)).thenReturn(Optional.of(produto));

        Compra compra = new Compra(
                id,
                quantidade
        );

        ResponseEntity<?> resposta = service.comprar(compra.getId(), quantidade);
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals("Compra finalizada com sucesso", resposta.getBody());
        Mockito.verify(compraRepository, Mockito.times(1)).save(Mockito.any(Compra.class));
    }

    @Test
    void testCompra_Produto_Nao_Encontrado() {
        Long id = 8L;
        int quantidade = 2;
        Mockito.when(compraRepository.findById(id)).thenReturn(Optional.empty());

        Compra compra = new Compra(id, quantidade);
        ResponseEntity<?> resposta = service.comprar(compra.getId(), quantidade);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        Assertions.assertEquals("Produto não encontrado", resposta.getBody());
    }


    @Test
    void testCompra_Estoque_Insuficiente_Do_Produto() {
        Long id = 1L;
        int quantidade = 20;
        ProdutosEletronicos produto = new ProdutosEletronicos(id, "Smartphone", "Iphone 15", 18, 10000.0, "Iphone potente e uma ótima qualidade de câmera");
        Mockito.when(produtosRepository.findById(id)).thenReturn(Optional.of(produto));


        Compra compra = new Compra(
                id,
                quantidade
        );

        ResponseEntity<?> resposta = service.comprar(compra.getId(), quantidade);
        Assertions.assertEquals(HttpStatus.CONFLICT, resposta.getStatusCode());
        Assertions.assertEquals("Quantidade insuficiente", resposta.getBody());
    }


}
