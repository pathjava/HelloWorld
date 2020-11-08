package ru.progwards.sever.testprogwards.test_15;

import java.time.*;
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

//        ZonedDateTime today = ZonedDateTime.parse("2019-04-01T16:24:11.252+05:30[Asia/Calcutta]");
//        System.out.println(today);

// Custom pattern

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
//        ZonedDateTime dateTime = ZonedDateTime.parse("2019-03-27 10:15:30 AM +05:30", formatter);
//        System.out.println(dateTime);
//
//        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("E, d MMM yyyy HH:mm:ss z");
//        ZonedDateTime dateTime1 = ZonedDateTime.parse("Mon, 1 Apr 2019 11:05:30 GMT", formatter1);
//        System.out.println(dateTime1);

//        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
//        ZonedDateTime dateTime = ZonedDateTime.parse("0000-01-01T00:00:00", formatter);
//        System.out.println(dateTime);

//        ZonedDateTime start;
//        LocalDate localDate;
//        LocalTime localTime;
//        localDate = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse("0000-01-01T00:00:00"));
//        localTime = LocalTime.of(00, 00, 00);
//        start = ZonedDateTime.of(localDate, localTime, ZoneId.of("Europe/Moscow"));
//        System.out.println(start);

//        localDate = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("0000-01-01T00:00:00"));
//        localTime = LocalTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("0000-01-01T00:00:00"));
//        start = ZonedDateTime.of(localDate, localTime, ZoneId.of("Europe/Moscow"));

//        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneOffset.UTC);
//        ZonedDateTime dateTime = ZonedDateTime.parse("0000-01-01T00:00:00", formatter);
//        start = ZonedDateTime.from(dateTime);
//        System.out.println(start);

//        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2016-08-16T10:15:30+08:00", DateTimeFormatter.ISO_LOCAL_DATE);
//////        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
//        LocalDate localDate = zonedDateTime.toLocalDate();
//////        LocalDate localDate = LocalDate.parse("0000-01-01T00:00:00", formatter);
//        System.out.println(localDate);

//        String date = "2016-08-16T10:15:30+08:00";
//        ZonedDateTime result = ZonedDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
//        LocalDate localDate = result.toLocalDate();
//        System.out.println("LocalDate : " + localDate);

//        LocalDateTime localDateTime = LocalDateTime.parse("0000-01-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneOffset.UTC));
//        long timeMillis = localDateTime.toEpochSecond(ZoneOffset.UTC);
//        System.out.println(timeMillis);


//        ZonedDateTime zonedDateTime = ZonedDateTime.parse("0000-01-01T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneOffset.UTC));
//        long timeMillis = zonedDateTime.toInstant().toEpochMilli();
//        LocalDate date = Instant.ofEpochMilli(timeMillis).atZone(ZoneId.systemDefault()).toLocalDate();
//        System.out.println(date);


//        ZonedDateTime start;
//        LocalDate localDate;
//        LocalTime localTime;
//        localDate = LocalDate.from(DateTimeFormatter.ISO_ZONED_DATE_TIME.parse("0000-01-01T00:00:00"));
//        localTime = LocalTime.from(DateTimeFormatter.ISO_ZONED_DATE_TIME.parse("0000-01-01T00:00:00"));
//        start = ZonedDateTime.of(localDate, localTime, ZoneId.systemDefault());
//        System.out.println(start);

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");
//        ZonedDateTime dateTime = ZonedDateTime.parse("2019-03-27 10:15:30 AM +05:30", formatter);
//        System.out.println(dateTime);

//        ZonedDateTime zdt = ZonedDateTime.parse("2000-06-03T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneOffset.UTC));
//        System.out.println(zdt);

        ZoneId usCentral = ZoneId.of("America/Chicago");
        LocalDateTime ldt = LocalDateTime.of(2012, Month.MARCH, 10, 7, 30);
        ZonedDateTime zdt1 = ZonedDateTime.of(ldt, usCentral);
        Duration d1 = Duration.ofHours(24);
        ZonedDateTime zdt2 = zdt1.plus(d1);
        System.out.println(zdt2);

    }
}
