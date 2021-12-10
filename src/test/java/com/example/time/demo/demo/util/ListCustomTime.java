package com.example.time.demo.demo.util;


import com.example.time.demo.demo.model.CustomTime;

import java.time.*;
import java.util.List;

public class ListCustomTime {
    public static List<CustomTime> getListCustomTime() {
        return List.of(
                CustomTime.builder()
                        .localDateTime(LocalDateTime.of(2021, 1, 1, 0, 0, 0))
                        .localTime(LocalTime.of(1, 1, 1))
                        .offsetDateTime(OffsetDateTime.of(2021, 1, 1, 0, 0, 0, 1, ZoneOffset.UTC))
                        .zonedDateTime(ZonedDateTime.now())
                        .instantTime(Instant.now())
                        .build(),
                CustomTime.builder()
                        .localDateTime(LocalDateTime.of(2020, 12, 30, 23, 59, 59))
                        .localTime(LocalTime.of(23, 59, 59))
                        .offsetDateTime(OffsetDateTime.of(2021, 12, 30, 23, 59, 59, 2, ZoneOffset.UTC))
                        .zonedDateTime(ZonedDateTime.now())
                        .instantTime(Instant.now())
                        .build(),
                CustomTime.builder()
                        .localDateTime(LocalDateTime.of(2020, 12, 30, 23, 59, 59))
                        .localTime(LocalTime.of(23, 59, 59))
                        .offsetDateTime(OffsetDateTime.of(2021, 12, 30, 23, 59, 59, 2, ZoneOffset.UTC))
                        .zonedDateTime(ZonedDateTime.of(2021, 12, 30, 1, 1, 1, 1, ZoneId.of("Europe/Kiev")))
                        .instantTime(Instant.EPOCH)
                        .build(),
                CustomTime.builder()
                        .localDateTime(LocalDateTime.of(2020, 12, 30, 23, 59, 59))
                        .localTime(LocalTime.of(23, 59, 59))
                        .offsetDateTime(OffsetDateTime.of(2021, 12, 30, 23, 59, 59, 2, ZoneOffset.UTC))
                        .zonedDateTime(ZonedDateTime.of(2021, 12, 30, 1, 1, 1, 1, ZoneId.of("Europe/Kiev")))
                        .build(),
                CustomTime.builder()
                        .localDateTime(LocalDateTime.of(2021, 11, 15, 10, 13, 00))
                        .localTime(LocalTime.of(10, 13, 00))
                        .offsetDateTime(OffsetDateTime.of(2021, 11, 15, 10, 13, 00, 2, ZoneOffset.UTC))
                        .zonedDateTime(ZonedDateTime.of(2021, 11, 15, 10, 13, 00, 1, ZoneId.of("Europe/Kiev")))
                        .instantTime(Instant.ofEpochSecond(1500000000))
                        .build()
        );

    }
}
