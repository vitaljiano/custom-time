package com.example.time.demo.demo.integration;

import com.example.time.demo.demo.CustomTimeAbstractIntegrationTest;
import com.example.time.demo.demo.model.CustomTime;
import com.example.time.demo.demo.util.JsonCustomTimeParser;
import com.example.time.demo.demo.util.ListCustomTime;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("${spring.profiles.active}")
public class CustomTimeIntegrationTest extends CustomTimeAbstractIntegrationTest {
    String customTimeInstance = JsonCustomTimeParser.getResourceFileAsString("test-json/CustomTimeInstance.json");
    List<CustomTime> timeList = ListCustomTime.getListCustomTime();
    public static final String pathUrl = "/api/v1/time";

    @Test
    public void searchFromDBTimeTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(pathUrl + "/{id}", 0);

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
