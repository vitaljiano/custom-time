package com.example.time.demo.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class Configuration {
//@Bean
//public DataSource cardDataSource(Environment environment) {
//   DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//   dataSourceBuilder.driverClassName(environment.getRequiredProperty("DB_DRIVER"));
//   dataSourceBuilder.url(environment.getRequiredProperty("CARD_DB_URL"));
//   dataSourceBuilder.username(environment.getRequiredProperty("DB_ROOT_USER"));
//   dataSourceBuilder.password(environment.getRequiredProperty("DB_PASS"));
//   return dataSourceBuilder.build();
//}
}
