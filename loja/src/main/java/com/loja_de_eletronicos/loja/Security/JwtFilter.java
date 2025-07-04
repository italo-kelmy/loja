package com.loja_de_eletronicos.loja.Security;


import com.loja_de_eletronicos.loja.Repository.UsuariosRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UsuariosRepository repository;

    @Autowired
    public JwtFilter(JwtUtil jwtUtil, UsuariosRepository repository) {
        this.jwtUtil = jwtUtil;
        this.repository = repository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Optional.ofNullable(request.getHeader("Authorization"))
                .filter(header -> header.startsWith("Bearer "))
                .map(header -> header.substring(7))
                .ifPresent(token -> {
                    String usuarios = jwtUtil.extrairClaim(token, Claims::getSubject);

                    if (usuarios != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        repository.findByUsuario(usuarios).ifPresent(usuarios1 -> {
                            UserDetails user = User.builder()
                                    .username(usuarios1.getUsuario())
                                    .password(usuarios1.getSenha())
                                    .roles("USER")
                                    .build();

                            if (jwtUtil.isTokenValid(token)) {
                                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                            }


                        });


                    }

                });

        filterChain.doFilter(request, response);
    }

}

