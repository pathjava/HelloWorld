package ru.progwards.java1.lessons.basics;

public class AccuracyDoubleFloat {
    public static double volumeBallDouble(double radius){
        return (4 / 3) * 3.14 * Math.pow(radius, 3);
    }

    public static float volumeBallFloat(float radius){
        return (float) ((4 / 3) * 3.14 * Math.pow(radius, 3));
    }
    // не понял как реализовать данное решение
//    public static double calculateAccuracy(double radius){
//
//    }

    public static void main(String[] args) {
        int radius = (int) 6371.2;
        System.out.println(volumeBallDouble(radius));
        System.out.println(volumeBallFloat(radius));
    }
}
