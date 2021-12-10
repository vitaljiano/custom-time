package com.example.time.demo.demo.testcontainer;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
@SpringBootTest
public class SpringBootTestContainer {

    @ClassRule
    public static MySQLContainer<?> mysqlSQLContainer = new MySQLContainer("mysql:latest")
            .withDatabaseName("demo_time")
            .withUsername("root")
            .withPassword("123456789");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", mysqlSQLContainer::getPassword);
        registry.add("spring.datasource.username", mysqlSQLContainer::getUsername);
    }
//    static class Initializer
//            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
//            TestPropertyValues.of(
//                    "spring.datasource.url=" + mysqlSQLContainer.getJdbcUrl(),
//                    "spring.datasource.username=" + mysqlSQLContainer.getUsername(),
//                    "spring.datasource.password=" + mysqlSQLContainer.getPassword()
//            ).applyTo(configurableApplicationContext.getEnvironment());
//        }
//    }
    @Test
    public void test(){

    }
}
