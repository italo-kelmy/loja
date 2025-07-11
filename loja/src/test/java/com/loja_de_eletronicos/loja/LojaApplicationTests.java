package com.loja_de_eletronicos.loja;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class LojaApplicationTests {

	@Test
	void contextLoads() {
		// Este teste verifica se o contexto da aplicação é carregado corretamente
		assertDoesNotThrow(() -> {}, "O contexto da aplicação deve carregar sem erros");
	}

}
