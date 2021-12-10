package com.example.time.demo.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class JsonCustomTimeParser {
    public static <T> T getResourceFileAsClass(String filename, Class<T> clazz) {
        var mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {
            return mapper.readValue(getResourceFileAsString(filename), clazz);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String getResourceFileAsString(String fileName) {
        var is = getResourceFileAsInputStream(fileName);
        if (is != null) {
            var reader = new BufferedReader(new InputStreamReader(is));
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } else {
            throw new RuntimeException("File not found");
        }
    }

    public static InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader classLoader = JsonCustomTimeParser.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}
