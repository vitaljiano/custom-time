package com.example.time.demo.demo.exampleJavaTime;


import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * ZonedDateTime now = ZonedDateTime.now(); // 2018-02-10T08:49:50.886682+01:00[Europe/Warsaw]
 * <p>
 * LocalDate localDate = LocalDate.of(2018, 1, 1);
 * LocalTime localTime = LocalTime.of(10, 30);
 * ZoneId zone = ZoneId.of("Europe/Kiev");
 * ZonedDateTime kievTime = ZonedDateTime.of(localDate, localTime, zone); // 2018-01-01T10:30+02:00[Europe/Kiev]
 * <p>
 * ***************** withZoneSameInstant() - конвертація зон *****************
 * LocalDate localDate = LocalDate.of(2018, 1, 1);
 * LocalTime localTime = LocalTime.of(10, 30);
 * ZoneId zone = ZoneId.of("Europe/Kiev");
 * <p>
 * ZonedDateTime kievTime = ZonedDateTime.of(localDate, localTime, zone); // 2018-01-01T10:30+02:00[Europe/Kiev]
 * ZonedDateTime nyTime = kievTime.withZoneSameInstant(ZoneId.of("America/New_York")); // 2018-01-01T03:30-05:00[America/New_York]
 * ZonedDateTime japanTime = kievTime.withZoneSameInstant(ZoneOffset.of("-09:00")); // 2017-12-31T23:30-09:00
 * <p>
 * ************** All zones **********************************************
 * List<String> zones = new ArrayList<>(ZoneId.getAvailableZoneIds());
 * zones.forEach(System.out::println);
 */
public class MyZoneDateTime {
    private static final LocalDateTime LDT = LocalDateTime.now();

    public static void main(String[] args) {
//        List<String> zones = new ArrayList<>(ZoneId.getAvailableZoneIds());
//        zones.forEach(System.out::println);
//
//        Map<String, String> map = zones.stream()
//                .collect(Collectors.toMap(zone -> zone, MyZoneDateTime::getOffset));
//        TreeMap<String, String> sortedMap = new TreeMap<>(map);
//        sortedMap.forEach((zone, offset) -> System.out.printf("%s (UTC%s) \n", zone, offset));
//    }
//
//    private static String getOffset(String zone) {
//        ZonedDateTime zdt = LDT.atZone(ZoneId.of(zone));
//        return zdt.getOffset().getId().replace("Z", "+00:00");
//    }

        ZoneOffset zoneOffSet= ZoneOffset.of("+03:00");
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        ZoneId zoneId = ZoneId.of("Europe/Kiev");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        System.out.println(zoneOffSet);
        System.out.println(offsetDateTime);
        System.out.println(zonedDateTime);
    }
}
