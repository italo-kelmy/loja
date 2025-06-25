package com.loja_de_eletronicos.loja.Repository;

import com.loja_de_eletronicos.loja.Entity.ProdutosEletronicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EletronicosRepository  extends JpaRepository<ProdutosEletronicos, Long> {
    Optional<ProdutosEletronicos> findByNome(String nome);
    List<ProdutosEletronicos> findByCategoria(String caegoria);




}
