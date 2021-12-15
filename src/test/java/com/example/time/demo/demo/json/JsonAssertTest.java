package com.example.time.demo.demo.json;

import com.example.time.demo.demo.model.CustomTime;
import com.example.time.demo.demo.util.JsonCustomTimeParser;
import com.example.time.demo.demo.util.ListCustomTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.List;


public class JsonAssertTest {
    String resultJsonString;

    List<CustomTime> timesList = ListCustomTime.getListCustomTime();
    ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void getBytes() {
        resultJsonString = JsonCustomTimeParser.getResourceFileAsString(
                "test-json/CustomTimeInstance.json");
    }

    @Test
    public void validateJson() throws JSONException {
        JSONAssert.assertEquals("{\"localTime\":\"10:13\"}", resultJsonString,  JSONCompareMode.LENIENT);
    }


}
