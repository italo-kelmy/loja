package com.loja_de_eletronicos.loja.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private JwtFilter jwtFilter;


    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(http -> http
                        .requestMatchers("/cadastro", "/login").permitAll()
                        .requestMatchers("/produtos").permitAll()
                        .anyRequest().authenticated()
                )

                .requiresChannel(channelRequestMatcherRegistry -> channelRequestMatcherRegistry.anyRequest().requiresSecure())

                .headers(header -> header.contentSecurityPolicy(csp -> csp.policyDirectives("default-src 'self'; script-src 'self'; style-src 'self'; img-src 'self'"))

                        .httpStrictTransportSecurity(https -> https
                                .includeSubDomains(true)
                                .preload(true)
                                .maxAgeInSeconds(31536000)
                        )
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessin -> sessin.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
