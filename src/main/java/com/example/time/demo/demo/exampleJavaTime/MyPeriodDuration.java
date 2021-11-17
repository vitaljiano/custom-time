package com.example.time.demo.demo.exampleJavaTime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class MyPeriodDuration {
    public static void main(String[] args) {

        LocalDate nextBirthday = LocalDate.of(2022, 4, 23);
        LocalDate now = LocalDate.now();

        Period period = Period.between(now, nextBirthday);
        int days = period.getDays();

        System.out.println(now);
        System.out.println(period);
        System.out.println("days left until my birthday "+days + " - use Period");

        LocalDate localDate = LocalDate.now();
        LocalDate birthday = LocalDate.of(2022, 4, 23);

        Duration duration = Duration.between(localDate.atStartOfDay(), birthday.atStartOfDay());
        System.out.println("days left until my birthday "+duration.toDays() + " - use Duration");

        long daysToBirthday = ChronoUnit.DAYS.between(localDate, birthday);
        System.out.println("days left until my birthday "+ daysToBirthday + " - use ChronoUtil");


    }
}
