package com.loja_de_eletronicos.loja.Security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {
    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private Long expirationl;


    public SecretKey secretKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public Long getExpirationl() {
        return expirationl;
    }
}
