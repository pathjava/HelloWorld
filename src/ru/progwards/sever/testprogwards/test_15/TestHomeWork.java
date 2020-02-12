package ru.progwards.sever.testprogwards.test_15;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TestHomeWork {
    public static void main(String[] args) {
//        Duration duration = Duration.parse("PT20.345S");
//        System.out.println(duration);
//
//        duration = Duration.parse("PT15M");
//        System.out.println(duration);
//
//        duration = Duration.of(Long.parseLong("1000000000"), ChronoUnit.MILLIS);
//        System.out.println(duration);
//
//        duration = Duration.parse("PT10H");
//        System.out.println(duration);
//
//        duration = Duration.parse("P2D");
//        System.out.println(duration);
//
//        duration = Duration.parse("P2DT3H4M");
//        System.out.println(duration);
//
//        duration = Duration.parse("P2DT3H4M");
//        System.out.println(duration);
//
//        duration = Duration.parse("P-6H3M");
//        System.out.println(duration);
//
//        duration = Duration.parse("-P6H3M");
//        System.out.println(duration);
//
//        duration = Duration.parse("-P-6H+3M");
//        System.out.println(duration);
//        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2000-06-03T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//        System.out.println(zonedDateTime);

//        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
//        ZonedDateTime dateTime = ZonedDateTime.parse("2000-06-03T10:00:00", formatter);
//        System.out.println(dateTime);

        // Default pattern

        ZonedDateTime today = ZonedDateTime.parse("2019-04-01T16:24:11.252+05:30[Asia/Calcutta]");
        System.out.println(today);

// Custom pattern

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
        ZonedDateTime dateTime = ZonedDateTime.parse("2019-03-27 10:15:30 AM +05:30", formatter);
        System.out.println(dateTime);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("E, d MMM yyyy HH:mm:ss z");
        ZonedDateTime dateTime1 = ZonedDateTime.parse("Mon, 1 Apr 2019 11:05:30 GMT", formatter1);
        System.out.println(dateTime1);
    }
}
