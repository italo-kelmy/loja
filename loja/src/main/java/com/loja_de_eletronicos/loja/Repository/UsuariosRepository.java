package com.loja_de_eletronicos.loja.Repository;

import com.loja_de_eletronicos.loja.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findByUsuario(String usuario);
    Optional<Usuarios> findByEmail(String email);

}
