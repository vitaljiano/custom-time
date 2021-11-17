package com.example.time.demo.demo.exampleJavaTime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * LocalDate localDate = LocalDate.of(2018, Month.AUGUST, 24);
 * TemporalAdjuster fourthSunday = TemporalAdjusters.dayOfWeekInMonth(4, DayOfWeek.SUNDAY);
 * System.out.println(localDate.with(fourthSunday)); // 2018-08-26
 *
 * LocalDate localDate = LocalDate.of(2017, Month.April, 21);
 * TemporalAdjuster lastFriInMonthAdjuster = TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY);
 * LocalDate lastFri = localDate.with(lastFriInMonthAdjuster); // 2017-04-28
 *
 * Period until = localDate.until(lastFri);
 * System.out.println(until.getDays()); // 7
 */
public class MyTemporalAdjuster {
    public static void main(String[] args) {

        LocalDate localDate = LocalDate.of(2017, Month.MARCH, 19);
        TemporalAdjuster firstMonInMonth = TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY);
        System.out.println(localDate.with(firstMonInMonth));

        LocalDate localDate1 = LocalDate.of(2017, Month.MAY, 10);

        TemporalAdjuster firstDayOfMonth = TemporalAdjusters.firstDayOfMonth();
        TemporalAdjuster firstDayOfNextMonth = TemporalAdjusters.firstDayOfNextMonth();
        TemporalAdjuster firstDayOfYear = TemporalAdjusters.firstDayOfYear();
        TemporalAdjuster firstDayOfNextYear = TemporalAdjusters.firstDayOfNextYear();

        System.out.println(localDate1.with(firstDayOfMonth)); // 2017-04-01
        System.out.println(localDate1.with(firstDayOfNextMonth)); // 2017-05-01
        System.out.println(localDate1.with(firstDayOfYear)); // 2017-01-01
        System.out.println(localDate1.with(firstDayOfNextYear)); // 2018-01-01

        TemporalAdjuster nextOrSameWed = TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY);
        System.out.println(localDate.with(nextOrSameWed)); // 2020-10-28
    }
}
