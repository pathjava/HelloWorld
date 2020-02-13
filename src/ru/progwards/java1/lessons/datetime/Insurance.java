package ru.progwards.java1.lessons.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;

public class Insurance {
    public static enum FormatStyle {SHORT, LONG, FULL}

    private ZonedDateTime start;
    private Duration duration; /* продолжительность действия */

    public Insurance(ZonedDateTime start) {
        this.start = start;
    }
    /* установить дату-время начала действия страховки
    * SHORT соответствует ISO_LOCAL_DATE
    * LONG  - ISO_LOCAL_DATE_TIME
    * FULL - ISO_ZONED_DATE_TIME
    * Для вариантов, когда не задан явно часовой пояс использовать таковой по умолчанию. */
    public Insurance(String strStart, FormatStyle style){
        LocalDate localDate;
        LocalTime localTime;
        switch (style){
            case SHORT:
                localDate = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(strStart));
                localTime = LocalTime.of(0, 0, 0);
                start = ZonedDateTime.of(localDate, localTime, ZoneId.of("Europe/Moscow"));

//                ZonedDateTime zonedDateTime = ZonedDateTime.parse(strStart, DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneOffset.UTC));
//                long timeMillis = zonedDateTime.toInstant().toEpochMilli();
//                LocalDate date = Instant.ofEpochMilli(timeMillis).atZone(ZoneId.systemDefault()).toLocalDate();
//                start = date.atStartOfDay(ZoneId.systemDefault());
                System.out.println(start);
                break;
            case LONG:
//                localDate = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(strStart));
//                localTime = LocalTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(strStart));
//                start = ZonedDateTime.of(localDate, localTime, ZoneId.systemDefault());
//                System.out.println(start);
                break;
            case FULL:
//                localDate = LocalDate.from(DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(strStart));
//                localTime = LocalTime.from(DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(strStart));
//                start = ZonedDateTime.of(localDate, localTime, ZoneId.systemDefault());
//                System.out.println(start);
                break;
        }
    }
    /* установить продолжительность действия страховки */
    public void setDuration(Duration duration){
        this.duration = duration;
    }
    /* установить продолжительность действия страховки, задав дату-время окончания */
    public void setDuration(ZonedDateTime expiration){
        duration = Duration.between(expiration, start);
    }
    /* установить продолжительность действия страховки, задав целыми числами количество месяцев, дней и часов */
    public void setDuration(int months, int days, int hours){
        ZonedDateTime endDate = start.plusMonths(months).plusDays(days).plusHours(hours);
        setDuration(endDate);
    }
    /* установить продолжительность действия страховки
    * SHORT - целое число миллисекунд (тип long)
    * LONG  - ISO_LOCAL_DATE_TIME - как период, например “0000-06-03T10:00:00” означает, что продолжительность действия страховки 0 лет, 6 месяцев, 3 дня 10 часов.
    * FULL - стандартный формат Duration, который получается через toString() */
    public void setDuration(String strDuration, FormatStyle style){
        switch (style){
            case SHORT:
//                duration = Duration.of(Long.parseLong(strDuration), ChronoUnit.MILLIS);
                duration = Duration.ofMillis(Long.parseLong(strDuration));
                break;
            case LONG:
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(strDuration, DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneOffset.UTC));
                long timeMillis = zonedDateTime.toInstant().toEpochMilli();
                duration = Duration.ofMillis(timeMillis);
                break;
            case FULL:
                duration = Duration.parse(strDuration);
                break;
        }
    }
    /* проверить действительна ли страховка на указанную дату-время. Если продолжительность не задана считать страховку бессрочной */
    public boolean checkValid(ZonedDateTime dateTime){
        return true;
    }
    /* вернуть строку формата "Insurance issued on " + start + validStr, где validStr = " is valid",
    * если страховка действительна на данный момент и " is not valid", если она недействительна */
    @Override
    public String toString() {
//        return "Insurance issued on " + start;
        return "Insurance issued on " + start + true;
//        return "Insurance issued on " + start + validStr;
    }


    public static void main(String[] args) {
        Insurance insurance = new Insurance(ZonedDateTime.now());
        Insurance insurance2 = new Insurance("2020-02-13", Insurance.FormatStyle.SHORT);
//        Insurance insurance3 = new Insurance("2020-02-13T19:48:15.2316539", FormatStyle.LONG);
//        Insurance insurance4 = new Insurance("2020-02-13T19:49:38.3652724+03:00[Europe/Moscow]", FormatStyle.FULL);
        insurance.setDuration(Duration.ofDays(30));
        insurance.setDuration(ZonedDateTime.parse("2020-02-13T19:56:13.370819+03:00[Europe/Moscow]"));
        insurance.setDuration(1,5,7);
        insurance.setDuration("1000000000", Insurance.FormatStyle.SHORT);
        insurance.setDuration("0000-01-01T00:00:00", Insurance.FormatStyle.LONG);
        insurance.setDuration("0000-01-01T00:00:00", Insurance.FormatStyle.FULL);
//        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2000-06-03T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneOffset.UTC));
//        System.out.println(zonedDateTime);
    }
}
