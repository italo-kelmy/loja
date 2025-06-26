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
                .url("jdbc:postgresql://dpg-d1e6vvemcj7s73a17a10-a.oregon-postgres.render.com:5432/loja_postgres_7q1x")
                .username("italo")
                .password("2610!!")
                .build();
    }



}
