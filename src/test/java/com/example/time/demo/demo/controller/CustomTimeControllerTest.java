package com.example.time.demo.demo.controller;

import com.example.time.demo.demo.model.CustomTime;
import com.example.time.demo.demo.service.TimeServiceImpl;
import com.example.time.demo.demo.util.ListCustomTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class CustomTimeControllerTest {

    List<CustomTime> timeList = ListCustomTime.getListCustomTime();

    String expectedResponse = "{ " +
            "\"localDateTime\": \"2021-11-15T10:13:00\"," +
            "\"localTime\": \"10:13:00\", " +
            "\"offsetDateTime\": \"2021-11-15T10:13:00.000000002Z\"," +
            "\"zonedDateTime\": \"2021-11-15T10:13:00.000000001+02:00\"," +
            "\"instantTime\": \"2017-07-14T02:40:00Z\"}";

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    TimeServiceImpl timeService;


    @Test
    public void getCustomTimeByIdTest() throws Exception {
        when(timeService.findById(anyLong())).thenReturn(timeList.get(4));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/{id}", 4)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse))
                .andReturn();
    }

    @Test
    public void getAllCustomTimesTest() throws Exception {
        when(timeService.getAll()).thenReturn(timeList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/")
                .accept(MediaType.APPLICATION_JSON);

        var response = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[" +
                                "{\"localTime\":\"01:01:01\"}, " +
                                "{\"localTime\":\"23:59:59\"}, " +
                                "{\"localTime\":\"23:59:59\"}, " +
                                "{\"localTime\":\"23:59:59\"}, " +
                                "{\"localTime\":\"10:13:00\"}" +
                                "]", false))
                .andReturn();
    }

    @Test
    public void createTimeTest() throws Exception {
        when(timeService.save(timeList.get(0))).thenReturn(timeList.get(0));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(timeList.get(0)));

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));
    }

    @Test
    public void shouldReturnCorrectFormatStringWhenShowTimeNow() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/show-time"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString(), matchesPattern("\\d{4}-[01]\\d-[0-3]\\dT[0-2]\\d:[0-5]\\d:[0-5]\\d(?:\\.\\d+)?Z?"));
    }
}
