package com.loja_de_eletronicos.loja.Configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class SourcerConfiguration {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://dpg-d1eccauuk2gs73ai4s1g-a.oregon-postgres.render.com:5432/loja_postgress")
                .username("loja_postgress_user")
                .password("sl8hYkpsYl9BWmMMhsAHgZCQYQPpdCa1")
                .build();
    }



}
