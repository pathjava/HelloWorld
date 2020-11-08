package ru.progwards.sever.testprogwards.test_15;

import java.time.*;

public class Test_02 {

    public static Instant createInstant() {
        java.time.LocalDateTime ldt1 = java.time.LocalDateTime.of(2020, 1, 1, 15, 0, 0, 1);
        return ldt1.toInstant(java.time.ZoneOffset.of("+03:00:00"));

//        LocalDateTime ldt1 = LocalDateTime.of(2020,1,1,15,0,0,1);
//        return ldt1.toInstant(ZoneOffset.of("+03:00:00"));
    }


    public static void main(String[] args) {
        LocalDateTime ldt1 = LocalDateTime.now();
        LocalDateTime ldt2 = ldt1.plusDays(4);
        Duration duration = Duration.between(ldt1, ldt2);
        System.out.println(duration.toHours());

//        LocalDateTime ldt2= LocalDateTime.of(2019, 05, 05, 22, 24);
//        System.out.println(ldt2);
//
//        ZoneId zid1 = ZoneId.of("Europe/Moscow");
//        System.out.println(zid1.getRules().getOffset(Instant.now()));
//
//        System.out.println(createInstant());
    }
}
