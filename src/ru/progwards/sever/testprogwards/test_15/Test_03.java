package ru.progwards.sever.testprogwards.test_15;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Test_03 {

    public static ZonedDateTime parseZDT(String str) {
        Locale locale = Locale.forLanguageTag("EN");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("DD.MM.yyyy HH:mm:ss.SSS Z zzzz", locale);
        return ZonedDateTime.parse(str, dtf.withZone(ZoneId.of("Europe/Moscow")));
    }

    public static void main(String[] args) {
//        SimpleDateFormat format = new SimpleDateFormat("'От''езд' - EEEE dd MMMM 'в' ha");
//        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.S");
//        System.out.println(format.format(new Date()));

//        System.out.println(Instant.now());
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss:S").withZone(ZoneOffset.UTC);
//        System.out.println(dtf.format(Instant.now()));

        System.out.println(parseZDT("01.01.2020 16:27:14.444 +0300 Moscow Standard Time"));
    }
}
