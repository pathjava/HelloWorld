package ru.progwards.java1.lessons.basics;

public class Astronomy {
    public static Double sphereSquare(Double r) {
        return 4 * 3.14 * Math.pow(r, 2);
    }

    public static Double earthSquare() {
        double r = 6371.2;
        return sphereSquare(r);
    }

    public static Double mercurySquare() {
        double r = 2439.7;
        return sphereSquare(r);
    }

    public static Double jupiterSquare() {
        double r = 71492;
        return sphereSquare(r);
    }

    // сравниваем планеты - написал, но не уверен в правильности
    public static Double earthVsMercury() {
        return earthSquare() / mercurySquare();
    }

    public static Double earthVsJupiter() {
        return earthSquare() / jupiterSquare();
    }

    public static void main(String[] args) {
        System.out.println(earthSquare());
        System.out.println(mercurySquare());
        System.out.println(jupiterSquare());
        System.out.println(earthVsMercury());
        System.out.println(earthVsJupiter());
    }
}
