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
                .url("mysql://root:dKLXSjpiPyhBlMvebZhNgyaoFWKylhKG@mysql.railway.internal:3306/railway")
                .username("root")
                .password("dKLXSjpiPyhBlMvebZhNgyaoFWKylhKG")
                .build();
    }



}
