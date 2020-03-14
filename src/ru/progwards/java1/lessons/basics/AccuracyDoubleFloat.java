package ru.progwards.java1.lessons.basics;

public class AccuracyDoubleFloat {
    public static double volumeBallDouble(double radius) {
        return (4.0 / 3.0) * 3.14 * (radius * radius * radius);
    }

    public static float volumeBallFloat(float radius) {
        return (float) ((4.0 / 3.0) * 3.14 * (radius * radius * radius));
    }

    // не совсем понял как реализовать данное решение
    public static double calculateAccuracy(double radius) {
        double a = volumeBallDouble(radius);
        float b = volumeBallFloat((float) radius);
        return a - b;
    }

    public static void main(String[] args) {
        int radius = (int) 6371.2;
        System.out.println(volumeBallDouble(radius));
        System.out.println(volumeBallFloat(radius));
        System.out.println(calculateAccuracy(radius));
    }
}
