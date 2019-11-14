package ru.progwards.java1.lessons.compare_if_cycles;

public class CyclesGoldenFibo {
    // абсолютно нет никакого решения
//    public static boolean containsDigit(int number, int digit){
//
//    }

    public static int fiboNumber(int n){
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fiboNumber(n - 1) + fiboNumber(n - 2);
        }
    }

    // абсолютно нет никакого решения
//    public static boolean isGoldenTriangle(int a, int b, int c){
//
//    }

    public static void main(String[] args) {
        System.out.println(fiboNumber(20));
    }
}
