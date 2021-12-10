package com.example.time.demo.demo.model;


import java.time.*;

public class CustomTimes {
    public static CustomTime getSimpleInsnace(){
        return CustomTime.builder()
                .localDateTime(LocalDateTime.of(2021, 11, 15, 10, 13, 00))
                .localTime(LocalTime.of(10, 13, 00))
                .offsetDateTime(OffsetDateTime.of(2021, 11, 15, 10, 13, 00, 2, ZoneOffset.UTC))
                .zonedDateTime(ZonedDateTime.of(2021, 11, 15, 10, 13, 00, 1, ZoneId.of("Europe/Kiev")))
                .instantTime(Instant.ofEpochSecond(1500000000))
                .build();
    }
}
