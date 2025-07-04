package com.loja_de_eletronicos.loja.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final JwtConfig jwtConfig;


    @Autowired
    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }


    public String generateKeys(UserDetails userDetails){
        return Jwts.builder()
                .signWith(jwtConfig.secretKey())
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .compact();
    }


    public Claims extrairAllClaims(String key){
        return Jwts.parser()
                .verifyWith(jwtConfig.secretKey())
                .build()
                .parseSignedClaims(key)
                .getPayload();
    }


    public <T> T extrairClaim(String key, Function<Claims, T> resolver){
        return resolver.apply(extrairAllClaims(key));
    }


    public boolean validadeClaims(String key, UserDetails userDetails){
        String usuario = extrairClaim(key, Claims::getSubject);
        return usuario.equals(userDetails.getUsername()) && isTokenValid(key);
    }

    public boolean isTokenValid(String key) {
        return extrairClaim(key, Claims::getExpiration).after(new Date());
    }


}
