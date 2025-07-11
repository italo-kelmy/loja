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

    public String generateKey(UserDetails userDetails){
        return Jwts.builder()
                .signWith(jwtConfig.secretKey())
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getExpirarion()))
                .compact();
    }

    public Claims extrairAllClaims(String key){
        return Jwts.parser()
                .verifyWith(jwtConfig.secretKey())
                .build()
                .parseSignedClaims(key)
                .getPayload();
    }


    public <T> T extrairKey(String key, Function<Claims, T> resolver){
        return resolver.apply(extrairAllClaims(key));
    }

    public boolean validadToken(String key, UserDetails userDetails){
        String usuario = extrairKey(key, Claims::getSubject);
        return usuario.equals(userDetails.getUsername()) && isToken(key);
    }

    public boolean isToken(String key) {
        return extrairKey(key, Claims::getExpiration).before(new Date());
    }


}
