package com.loja_de_eletronicos.loja.Repository;

import com.loja_de_eletronicos.loja.Entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

}
