package com.loja_de_eletronicos.loja.Repository;

import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutosRepository extends JpaRepository<ProdutosEletronicos, Long> {
    
    List<ProdutosEletronicos> findByNome(String nome);
    List<ProdutosEletronicos> findByCategoria(String categoria);

}
