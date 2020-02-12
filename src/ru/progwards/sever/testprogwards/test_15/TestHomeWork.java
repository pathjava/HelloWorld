package ru.progwards.sever.testprogwards.test_15;

import java.time.Duration;

public class TestHomeWork {
    public static void main(String[] args) {
        Duration duration = Duration.parse("PT20.345S");
        System.out.println(duration);

        duration = Duration.parse("PT15M");
        System.out.println(duration);

        duration = Duration.parse("PT10H");
        System.out.println(duration);

        duration = Duration.parse("P2D");
        System.out.println(duration);

        duration = Duration.parse("P2DT3H4M");
        System.out.println(duration);

        duration = Duration.parse("P2DT3H4M");
        System.out.println(duration);

        duration = Duration.parse("P-6H3M");
        System.out.println(duration);

        duration = Duration.parse("-P6H3M");
        System.out.println(duration);

        duration = Duration.parse("-P-6H+3M");
        System.out.println(duration);
    }
}
