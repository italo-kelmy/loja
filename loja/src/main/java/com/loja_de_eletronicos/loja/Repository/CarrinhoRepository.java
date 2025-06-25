package com.loja_de_eletronicos.loja.Repository;

import com.loja_de_eletronicos.loja.Entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
}
