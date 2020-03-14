package ru.progwards.java1.lessons.basics;

public class ReverseDigits {
    public static void main(String[] args) {
        int d = 135;
        System.out.println(reverseDigits(d));
    }

    // реализовал - это копипаст, но теперь хотя бы понятно как это работает
    public static int reverseDigits(int d) {
        int a = 0;
        int e = 0;
        int b = 0;
        int c = 0;
        int s = 0;
        a = d % 10;
        e = d / 10;
        b = e % 10;
        c = d / 100;
        s = (a * 100) + (b * 10) + c;
        return s;
    }
}
