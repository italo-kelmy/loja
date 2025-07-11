package com.loja_de_eletronicos.loja.Usuarios;

import com.loja_de_eletronicos.loja.Entity.Usuarios;
import com.loja_de_eletronicos.loja.Repository.UsuariosRepository;
import com.loja_de_eletronicos.loja.Security.JwtUtil;
import com.loja_de_eletronicos.loja.Security.SecurityConfig;
import com.loja_de_eletronicos.loja.Service.UsuariosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Optional;


public class UsuariosTest {

    @Mock
    private UsuariosRepository usuariosRepository;
    @Mock
    private SecurityConfig config;
    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UsuariosService service;


    @BeforeEach
    void voidUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(config.bCryptPasswordEncoder()).thenReturn(new BCryptPasswordEncoder());
    }


    @Test
    void testCadastro_QuandoUsuarioNovo_DeveCadastrarComSucesso() {

        String usuario = "leclec";
        String email = "klec08@hotmail.com";

        Usuarios usuario1 = new Usuarios(null, usuario, "senha123!!", email, "16556-789", "11 99520-4284");


        Mockito.when(usuariosRepository.findByUsuario(usuario)).thenReturn(Optional.empty());
        Mockito.when(usuariosRepository.findByEmail(email)).thenReturn(Optional.empty());


        ResponseEntity<?> resposta = service.cadastro(usuario1);
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals("Usuário cadastrado com sucesso", resposta.getBody());
        Mockito.verify(usuariosRepository, Mockito.times(1)).save(Mockito.any(Usuarios.class));
    }

    @Test
    void testCadastro_QuandoUsuarioExistente_DeveRetornarConflito() {
        String usuario = "leclec";
        String email = "klec08@hotmail.com";

        Usuarios usuario1 = new Usuarios(null, usuario, "senha123!!", email, "16556-789", "11 99520-4284");


        Mockito.when(usuariosRepository.findByUsuario(usuario)).thenReturn(Optional.of(usuario1));
        Mockito.when(usuariosRepository.findByEmail(email)).thenReturn(Optional.of(usuario1));
        Mockito.when(usuariosRepository.save(Mockito.any(Usuarios.class))).thenReturn(usuario1);

        ResponseEntity<?> resposta = service.cadastro(usuario1);
        Assertions.assertEquals(HttpStatus.CONFLICT, resposta.getStatusCode());
        Assertions.assertEquals("Usuário já cadastrado", resposta.getBody());
        Mockito.verify(usuariosRepository, Mockito.never()).save(Mockito.any(Usuarios.class));
    }

    @Test
    void testLogin_QuandoCredenciaisCorretas_DeveRetornarToke() throws Exception {
        String usuario = "italokelmy";
        String senha = "senha123";

        Usuarios login = new Usuarios();
        login.setUsuario(usuario);
        login.setSenha(senha);

        AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);
        Authentication authentication = Mockito.mock(Authentication.class);

        Mockito.when(config.manager(Mockito.any())).thenReturn(authenticationManager);
        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authentication);

        Mockito.when(usuariosRepository.findByUsuario(usuario)).thenReturn(Optional.of(login));
        Mockito.when(jwtUtil.generateKey(Mockito.any())).thenReturn("token-teste");

        ResponseEntity<?> resposta = service.login(login);
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals("token-teste", resposta.getBody());
    }




}
