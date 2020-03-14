package ru.progwards.java1.lessons.compare_if_cycles;

public class CyclesGoldenFibo {
    // решение дал Григорий
    public static boolean containsDigit(int number, int digit) {
        if (number == 0 && digit == 0) return true;
        int n = number;
        while (n > 0) {
            if (n % 10 == digit) return true;
            n /= 10;
        }
        return false;
    }

    public static int fiboNumber(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fiboNumber(n - 1) + fiboNumber(n - 2);
        }
    }

    // решение дал Григорий
    public static boolean isGoldenTriangle(int a, int b, int c) {
        float k = 0;
        if (a == b)
            k = (float) a / c;
        else if (b == c)
            k = (float) b / a;
        else if (a == c)
            k = (float) a / b;
        return k > 1.61703f && k < 1.61903f;
    }

    public static void main(String[] args) {
        System.out.println(containsDigit(12345, 2));
        System.out.println(fiboNumber(20));
        System.out.println(isGoldenTriangle(25, 25, 25));
    }
}
