package ru.progwards.java1.lessons.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Insurance {
    public static enum FormatStyle {SHORT, LONG, FULL}

    private ZonedDateTime start;
    private Duration duration;

    public Insurance(ZonedDateTime start) {
//        this.start = start;
//        Instant instant = Instant.from(start);
        this.start = ZonedDateTime.from(start);
//        this.start = instant;
//        this.start = ZonedDateTime.now(ZoneId.of(String.valueOf(start)));
//        LocalDate localDate = start.toLocalDate();
    }

    public Insurance(String strStart, FormatStyle style){

//        Instant inst = Instant.parse(strStart);
//        ZonedDateTime strStart = Instant.now().atZone(ZoneId.of("Europe/Moscow"));
//        DateTimeFormatter isoLocalDate = DateTimeFormatter.ISO_LOCAL_DATE;
//        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(style);
//        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(style);
//        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(style);
    }


    /* установить продолжительность действия страховки */
    public void setDuration(Duration duration){

    }
    /* установить продолжительность действия страховки, задав дату-время окончания */
    public void setDuration(ZonedDateTime expiration){

    }
    /* установить продолжительность действия страховки, задав целыми числами количество месяцев, дней и часов */
    public void setDuration(int months, int days, int hours){

    }
    /* установить продолжительность действия страховки
    * SHORT - целое число миллисекунд (тип long)
    * LONG  - ISO_LOCAL_DATE_TIME - как период, например “0000-06-03T10:00:00” означает, что продолжительность действия страховки 0 лет, 6 месяцев, 3 дня 10 часов.
    * FULL - стандартный формат Duration, который получается через toString() */
    public void setDuration(String strDuration, FormatStyle style){

    }
    /* проверить действительна ли страховка на указанную дату-время. Если продолжительность не задана считать страховку бессрочной */
    public boolean checkValid(ZonedDateTime dateTime){
        return true;
    }
    /* вернуть строку формата "Insurance issued on " + start + validStr, где validStr = " is valid",
    * если страховка действительна на данный момент и " is not valid", если она недействительна */
    @Override
    public String toString() {
        return "Insurance issued on " + start;
//        return "Insurance issued on " + start + validStr;
    }
}
