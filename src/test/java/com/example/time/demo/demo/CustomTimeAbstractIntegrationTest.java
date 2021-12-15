package com.example.time.demo.demo;

import com.example.time.demo.demo.model.CustomTime;
import com.example.time.demo.demo.util.JsonCustomTimeParser;
import com.example.time.demo.demo.util.ListCustomTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"/set-value-time-auto-set.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/clear-data-after-testing.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CustomTimeAbstractIntegrationTest {
    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    protected ObjectMapper objectMapper;

    protected MockMvc mockMvc;


    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

}
