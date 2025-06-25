package com.loja_de_eletronicos.loja.Service;

import com.loja_de_eletronicos.loja.Entity.Usuarios;
import com.loja_de_eletronicos.loja.Repository.UsuariosRepository;
import com.loja_de_eletronicos.loja.Security.JwtUtil;
import com.loja_de_eletronicos.loja.Security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService implements UserDetailsService {
    private final UsuariosRepository repository;
    private final SecurityConfig config;
    private JwtUtil jwtUtil;


    @Autowired
    public UsuariosService(UsuariosRepository repository, SecurityConfig config, JwtUtil jwtUtil) {
        this.repository = repository;
        this.config = config;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<?> cadastro(Usuarios usuarios) {

        if (repository.findByUsuario(usuarios.getUsuario()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já cadastrado");
        } else if (repository.findByEmail(usuarios.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
        }


        usuarios.setSenha(config.bCryptPasswordEncoder().encode(usuarios.getSenha()));
        repository.save(usuarios);
        return ResponseEntity.ok("Usuário cadastrado com sucesso");
    }

    public String login(Usuarios usuarios) throws Exception {
        config.manager(new AuthenticationConfiguration()).authenticate(
                new UsernamePasswordAuthenticationToken(usuarios.getUsuario(), usuarios.getSenha())
        );

        Usuarios usuarios1 = repository.findByUsuario(usuarios.getUsuario()).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        UserDetails user = User.builder()
                .username(usuarios1.getUsuario())
                .password(usuarios1.getSenha())
                .roles("USER")
                .build();

        return jwtUtil.genarateKey(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuarios = repository.findByUsuario(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return User.builder()
                .username(usuarios.getUsuario())
                .password(usuarios.getSenha())
                .build();
    }
}
