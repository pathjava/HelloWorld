package ru.progwards.java1.lessons.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Insurance {
    public static enum FormatStyle {SHORT, LONG, FULL}

    private ZonedDateTime start;
    private Duration duration;

    /* установить дату-время начала действия страховки */
    public Insurance(ZonedDateTime start) {
        this.start = start;
//        this.start = ZonedDateTime.from(start);
    }

    /* установить дату-время начала действия страховки
    * SHORT соответствует ISO_LOCAL_DATE
    * LONG  - ISO_LOCAL_DATE_TIME
    * FULL - ISO_ZONED_DATE_TIME
    * Для вариантов, когда не задан явно часовой пояс использовать таковой по умолчанию */
    public Insurance(String strStart, FormatStyle style){
        LocalDate localDate;
        LocalTime localTime;
        switch (style){
            case SHORT:
                localDate = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(strStart));
                localTime = LocalTime.of(00,00,00);
                start = ZonedDateTime.of(localDate, localTime, ZoneId.of("Europe/Moscow"));
                break;
            case LONG:
                localDate = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(strStart));
                localTime = LocalTime.of(00,00,00);
                start = ZonedDateTime.of(localDate, localTime, ZoneId.of("Europe/Moscow"));
                break;
            case FULL:
                localDate = LocalDate.from(DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(strStart));
                localTime = LocalTime.of(00,00,00);
                start = ZonedDateTime.of(localDate, localTime, ZoneId.of("Europe/Moscow"));
                break;
        }
    }


    /* установить продолжительность действия страховки */
    public void setDuration(Duration duration){
// сложить
    }
    /* установить продолжительность действия страховки, задав дату-время окончания */
    public void setDuration(ZonedDateTime expiration){
// старт и окончание
    }
    /* установить продолжительность действия страховки, задав целыми числами количество месяцев, дней и часов */
    public void setDuration(int months, int days, int hours){
// к старту прибавить аргументы
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
