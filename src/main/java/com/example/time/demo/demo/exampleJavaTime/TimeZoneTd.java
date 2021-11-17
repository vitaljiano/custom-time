package com.example.time.demo.demo.exampleJavaTime;

import java.time.*;

public class TimeZoneTd {
    public static void main(String[] args) {
//        Instant instant = Clock.systemDefaultZone().instant();
//
//        System.out.println(instant.getEpochSecond());
//        System.out.println(instant.getNano());
//
//        System.out.println(instant.toEpochMilli());

        LocalDateTime ldt = LocalDateTime.of(2015, 1, 10, 0, 0, 0, 0);
        ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.of("Europe/Kiev"));
        System.out.println(zdt);
    }
}
