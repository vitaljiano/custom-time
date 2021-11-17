package com.example.time.demo.demo.exampleJavaTime;

/**
 LocalDate.of(2020, Month.SEPTEMBER, 23); // 2020-09-23
 LocalDate.of(2021, 1, 1); // 2021-01-01
 LocalDate.ofYearDay(2020, 361); // 2020-12-26

 * ******* setting the desired time *********
 * LocalTime.of(12, 10); // 12:10
 * LocalTime.of(18, 15, 10); // 18:15:10
 * LocalTime.of(23, 59, 59, 700); // 23:59:59.000000700
 * LocalTime.ofSecondOfDay(9_124); // 02:32:04
 * LocalTime.ofNanoOfDay(100_000_000_000L); // 00:01:40
 *
 * ******* setting the desired date *********
 * LocalDateTime.of(1992, Month.AUGUST, 24, 12, 0); // 1992-08-24T12:00
 * LocalDateTime.of(2004, Month.AUGUST, 23, 17, 15, 2); // 2004-08-23T17:15:02
 * LocalDateTime.of(2008, Month.JANUARY, 6, 20, 45, 2, 2); // 2008-01-06T20:45:02.000000002
 * LocalDateTime.of(2009, 1, 13, 9, 7); // 2009-01-13T09:07
 * LocalDateTime.of(2012, 4, 4, 6, 16); // 2012-04-04T06:16
 * LocalDateTime.of(2018, 10, 14, 4, 20); // 2018-10-14T04:20
 * LocalDateTime.of(LocalDate.now(), LocalTime.now()); // 2018-01-20T09:19:48.603054
 *
 * ************************* operation in LocalDate ********************
 * LocalDate now = LocalDate.now(); // 2018-01-21
 * LocalDate plus2Days = now.plusDays(2); // 2018-01-23
 * LocalDate plusWeek = now.plusWeeks(1); // 2018-01-28
 * LocalDate plus3Months = now.plusMonths(3); // 2018-04-21
 * LocalDate plusPeriod = now.plus(Period.ofDays(3)); // 2018-01-24
 * LocalDate plusMillennia = now.plus(1, ChronoUnit.MILLENNIA); // 3018-01-21
 *
 * LocalDate now = LocalDate.now(); // 2018-01-21
 * LocalDate minusDays = now.minusDays(3); // 2018-01-18
 * LocalDate minusWeeks = now.minusWeeks(2); // 2018-01-07
 * LocalDate minusMonths = now.minusMonths(4); // 2017-09-21
 * LocalDate minusPeriod = now.minus(Period.ofDays(1)); // 2018-01-20
 * LocalDate minusEras = now.minus(1, ChronoUnit.CENTURIES); // 1918-01-21
 *
 *  ******************* Compare *****************************************
 * LocalDate now = LocalDate.now();
 * LocalDate _2017 = LocalDate.of(2017, 9, 23);
 * boolean after = now.isAfter(_2017);// true
 * boolean before = now.isBefore(_2017);// false
 *
 * LocalDate localDate = LocalDate.now();
 * LocalDate tomorrow = LocalDate.now().plusDays(1);
 * boolean isDateAfter = localDate.compareTo(tomorrow) &gt; 0; // false
 *
 * LocalTime localTime = LocalTime.now();
 * LocalTime hourLater = localTime.plusHours(1);
 * boolean isTimeBefore = localTime.compareTo(hourLater) &lt; 0; // true
 *
 *
 * LocalDateTime localDateTime = LocalDateTime.now();
 * LocalDateTime lastYear = localDateTime.minusYears(1);
 * boolean isDateTimeAfter = localDateTime.compareTo(lastYear) &gt; 0; // true
 *
 * ****************************** DateTimeFormatter ***************************
 * LocalDate now = LocalDate.now();
 * String basicIsoDate = now.format(DateTimeFormatter.BASIC_ISO_DATE); // 20180128
 * String isoDate = now.format(DateTimeFormatter.ISO_DATE); // 2018-01-28
 * String isoWeekDate = now.format(DateTimeFormatter.ISO_WEEK_DATE); // 2018-W04-7
 * String isoLocalDate = now.format(DateTimeFormatter.ISO_LOCAL_DATE); // 2018-01-28
 * String isoOrdinalDate = now.format(DateTimeFormatter.ISO_ORDINAL_DATE); // 2018-028
 *
 * LocalTime now = LocalTime.now();
 * String nativeFormat = now.format(DateTimeFormatter.ofPattern("hh:mm:ss ")); // 08:10:43
 * String engFormat = now.format(DateTimeFormatter.ofPattern("h:mm a")); // 08:10 AM
 *
 *
 */


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class MyLocalDataTime {
    public static void main(String[] args) {
        LocalDateTime todayMax = LocalDateTime.MAX;
        LocalDateTime todayMin = LocalDateTime.MIN;
        LocalDateTime today = LocalDateTime.now();
        System.out.println("Max: " + todayMax + "  Min:" + todayMin + "  Now:" + today);

        LocalDateTime randDate = LocalDateTime.of(
                2021, Month.NOVEMBER, 10, 12, 01, 00);
        System.out.println("entered date time: " + randDate);

        LocalDateTime futureDateTime = LocalDateTime.ofEpochSecond(
                2000, 0, ZoneOffset.UTC);
        System.out.println("Time after 2000 sec: " + futureDateTime);

        System.out.println("***********************************************");
        LocalDate date = LocalDate.now();
        System.out.println("A leap year " + date.getYear() + " is: " + date.isLeapYear());
        System.out.println("Compare date with 10.11.2021 : " + date.isBefore(LocalDate.of(2021, 11, 10)));
        System.out.println("Time from LocalDate: " + date.atTime(LocalTime.now()));
        System.out.println("3 week after today date: " + date.plusWeeks(3));
        System.out.println("First day this month: " + date.with(TemporalAdjusters.firstDayOfMonth()));

        System.out.println("*************************************************");

        System.out.println("standard format to LocalDate : " + date);

        System.out.println(date.format(DateTimeFormatter.ofPattern("d::MMM::uuuu")));
        System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));


        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(" LocalDateTime : " + dateTime);

        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss")));
        System.out.println(dateTime.format(DateTimeFormatter.BASIC_ISO_DATE));

        Instant timestamp = Instant.now();

        System.out.println("стандартный формат : " + timestamp);


    }

}
