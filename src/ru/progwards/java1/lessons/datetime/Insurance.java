package ru.progwards.java1.lessons.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Insurance {
    public static enum FormatStyle {SHORT, LONG, FULL}

    private ZonedDateTime start;
    private Duration duration; /* продолжительность действия */

    public Insurance(ZonedDateTime start) {
        this.start = start;
    }

    public Insurance(String strStart, FormatStyle style) {
        LocalDate localDate;
        LocalTime localTime;
        switch (style) {
            case SHORT:
                localDate = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(strStart));
                localTime = LocalTime.of(0, 0, 0);
                start = ZonedDateTime.of(localDate, localTime, ZoneId.systemDefault());
                break;
            case LONG:
                LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(strStart));
                start = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
                break;
            case FULL:
                DateTimeFormatter dtFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
                start = ZonedDateTime.parse(strStart, dtFormatter);
                break;
        }
    }

    /* установить продолжительность действия страховки */
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setDuration(ZonedDateTime expiration) {
        duration = Duration.between(start, expiration);
    }

    /* установить продолжительность действия страховки, задав целыми числами количество месяцев, дней и часов */
    public void setDuration(int months, int days, int hours) {
        ZonedDateTime endDate = start.plusMonths(months).plusDays(days).plusHours(hours);
        setDuration(endDate);
    }

    /* установить продолжительность действия страховки
     * SHORT - целое число миллисекунд (тип long)
     * LONG  - ISO_LOCAL_DATE_TIME - как период, например “0000-06-03T10:00:00” означает, что продолжительность действия страховки 0 лет, 6 месяцев, 3 дня 10 часов.
     * FULL - стандартный формат Duration, который получается через toString() */
    public void setDuration(String strDuration, FormatStyle style) {
        switch (style) {
            case SHORT:
//                duration = Duration.of(Long.parseLong(strDuration), ChronoUnit.MILLIS);
                duration = Duration.ofMillis(Long.parseLong(strDuration));
                break;
            case LONG:
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(strDuration, DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneOffset.UTC));
                long timeMillis = zonedDateTime.toInstant().toEpochMilli();
                duration = Duration.ofMillis(timeMillis);

//                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
//                LocalDateTime localDateTime = LocalDateTime.parse(strDuration, dateTimeFormatter);
//                Instant millis = localDateTime.toInstant(ZoneOffset.UTC);
//                Long ms = Long.parseLong(String.valueOf(millis.getEpochSecond()));
//                duration = Duration.from(millis);

//                LocalDateTime localDateTime = LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(strDuration));
//                long timeMillis = localDateTime.getSecond();
//                long timeMillis = localDateTime.toEpochSecond(ZoneOffset.UTC);
//                duration = Duration.ofMillis(timeMillis);
//                duration = Duration.of(timeMillis, ChronoUnit.MILLIS);
                break;
            case FULL:
                duration = Duration.parse(strDuration);
                break;
        }
    }

    /* проверить действительна ли страховка на указанную дату-время. Если продолжительность не задана считать страховку бессрочной */
    public boolean checkValid(ZonedDateTime dateTime) {

//        long longEnd = (start.plus(duration)).toEpochSecond();

        long longStart = start.toEpochSecond();
        long longDateTime = dateTime.toEpochSecond();

        if (duration == null){
//        if (duration == null || duration.isNegative()){
            return longDateTime >= longStart;
        } else if (longDateTime < longStart || longDateTime > (start.plus(duration)).toEpochSecond() || duration.isNegative()){
            return false;
        } else
        return longDateTime <= start.plus(duration).toEpochSecond();
    }

    /* вернуть строку формата "Insurance issued on " + start + validStr, где validStr = " is valid",
     * если страховка действительна на данный момент и " is not valid", если она недействительна */
    @Override
    public String toString() {
        String validStr = checkValid(ZonedDateTime.now()) ? " is valid" : " is not valid";
        return "Insurance issued on " + start + validStr;
    }


    public static void main(String[] args) {
//        Insurance insurance = new Insurance(ZonedDateTime.now().minusDays(1));
        Insurance insurance = new Insurance(ZonedDateTime.now());
        Insurance insurance2 = new Insurance("2020-02-16", Insurance.FormatStyle.SHORT);
        Insurance insurance3 = new Insurance("2020-02-16T19:48:15.2316539", FormatStyle.LONG);
        Insurance insurance4 = new Insurance("2020-02-16T19:49:38.3652724+03:00[Europe/Moscow]", FormatStyle.FULL);
//        insurance.setDuration(Duration.ofDays(1));
//        insurance.setDuration(ZonedDateTime.now().plusDays(7));
//        insurance.setDuration(ZonedDateTime.parse("2020-02-20T09:00:14.722911+03:00[Europe/Moscow]"));
//        insurance.setDuration(0, 5, 7);
//        insurance.setDuration("1000000000", Insurance.FormatStyle.SHORT);
//        insurance.setDuration("2020-01-01T11:30:00", Insurance.FormatStyle.LONG);
        insurance.setDuration("0000-01-01T00:00:00", Insurance.FormatStyle.LONG);
//        insurance.setDuration("PT48H", Insurance.FormatStyle.FULL);
//        insurance.setDuration("P2DT3H4M", Insurance.FormatStyle.FULL);

        System.out.println(insurance.checkValid(ZonedDateTime.now().plusDays(5)));

    }
}
