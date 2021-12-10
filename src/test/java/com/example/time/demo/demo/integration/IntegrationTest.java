package com.example.time.demo.demo.integration;

import com.example.time.demo.demo.model.CustomTime;
import com.example.time.demo.demo.util.JsonCustomTimeParser;
import com.example.time.demo.demo.util.ListCustomTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource("/applicationForTest.properties")
@AutoConfigureMockMvc
@Sql(value = {"/set-value-time-auto-set.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/clear-data-after-testing.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class IntegrationTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    ObjectMapper objectMapper;

    String customTimeInstance = JsonCustomTimeParser.getResourceFileAsString("test-json/CustomTimeInstance.json");
    List<CustomTime> timeList = ListCustomTime.getListCustomTime();
    private MockMvc mockMvc;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void searchFromDBTimeTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/{id}", 0);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(customTimeInstance));
    }

    @Sql(value = "/clear-data-after-testing.sql")
    @Test
    public void shouldWriteInstanceCustomTimeToDB() throws Exception {

        String requestJson = objectMapper.writeValueAsString(timeList.get(3));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(201));
    }

    @Test
    public void shouldReturnAllRecordsFromDB() throws Exception {
        mockMvc.perform(get("/"))
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
