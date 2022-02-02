package com.example.time.demo.demo.testcontainer;

import com.example.time.demo.demo.CustomTimeAbstractIntegrationTest;
import com.example.time.demo.demo.model.CustomTime;
import com.example.time.demo.demo.util.JsonCustomTimeParser;
import com.example.time.demo.demo.util.ListCustomTime;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
public class CustomTimeContainerTest extends CustomTimeAbstractIntegrationTest {
    List<CustomTime> timeList = ListCustomTime.getListCustomTime();
    public static final String pathUrl = "/api/v1/time";

    @Container
    public static MySQLContainer<?> mysqlSQLContainer = new MySQLContainer<>("mysql:latest")
            .withDatabaseName("demo_time")
            .withUsername("root")
            .withPassword("root");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> String.format("jdbc:mysql://localhost:%d/demo_time", mysqlSQLContainer.getFirstMappedPort()));
        registry.add("spring.datasource.password", mysqlSQLContainer::getPassword);
        registry.add("spring.datasource.username", mysqlSQLContainer::getUsername);
    }

    @Test
    public void searchFromDBTimeTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(pathUrl + "/{id}", 0);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"instantTime\":\"2021-11-15T10:13:00Z\"}", false));
    }

    @Sql(value = "/clear-data-after-testing.sql")
    @Test
    public void shouldWriteInstanceCustomTimeToDB() throws Exception {

        String requestJson = objectMapper.writeValueAsString(timeList.get(3));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(pathUrl + "/")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(201));
    }

    @Test
    public void shouldReturnAllRecordsFromDB() throws Exception {
        mockMvc.perform(get(pathUrl + "/"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[" +
                        "{\"instantTime\":\"2021-11-15T10:13:00Z\"}," +
                        "{\"instantTime\":\"2021-11-15T01:13:00Z\"}" +
                        "]", false))
                .andReturn();
    }
}
