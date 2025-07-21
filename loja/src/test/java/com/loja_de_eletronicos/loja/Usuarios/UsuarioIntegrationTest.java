package com.loja_de_eletronicos.loja.Usuarios;


import com.loja_de_eletronicos.loja.Entity.Usuarios;
import com.loja_de_eletronicos.loja.Repository.UsuariosRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuariosRepository repository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @AfterEach
    void testDow() {
        entityManager.clear();
    }

    @Test
    void testSalvarCadastroUsuario() {

        Usuarios usuario = new Usuarios();
        usuario.setUsuario("italokelmy");
        usuario.setSenha(passwordEncoder.encode("senha123"));
        usuario.setEmail("italokelmy548@hotmail.com");
        usuario.setCep("75226-532");
        usuario.setTelefone("11 92365-1033");


        Usuarios savedUsuario = repository.save(usuario);
        Assertions.assertNotNull(savedUsuario.getId());
        Assertions.assertEquals("italokelmy", savedUsuario.getUsuario());
        Assertions.assertTrue(passwordEncoder.matches("senha123", savedUsuario.getSenha()));
    }

    @Test
    void testBuscarUsuarioJaCadastrado() {

        Usuarios usuario = new Usuarios();
        usuario.setUsuario("italokelmy");
        usuario.setSenha(passwordEncoder.encode("senha123"));
        usuario.setEmail("italokelmy548@hotmail.com");
        usuario.setCep("75226-532");
        usuario.setTelefone("11 92365-1033");
        entityManager.persist(usuario);
        entityManager.flush();

        Optional<Usuarios> usuarios = repository.findByUsuario("italokelmy");
        Assertions.assertNotNull(usuarios);
        Assertions.assertTrue(usuarios.isPresent());
        Assertions.assertEquals("italokelmy", usuarios.get().getUsuario());
    }

    @Test
    void testBuscarPorUsuario_QuandoNaoExistir() {
        Optional<Usuarios> usuario = repository.findByUsuario("Nao existe");

        Assertions.assertFalse(usuario.isPresent());
    }

    @Test
    void testSalvarUsuarioComEmailInvalido_DeveFalhar() {
        Usuarios usuario = new Usuarios();
        usuario.setUsuario("italokelmy");
        usuario.setSenha(passwordEncoder.encode("senha123"));
        usuario.setEmail("email-invalido");
        usuario.setCep("75226-532");
        usuario.setTelefone("11 92365-1033");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            entityManager.persistAndFlush(usuario);
        });
    }


}
