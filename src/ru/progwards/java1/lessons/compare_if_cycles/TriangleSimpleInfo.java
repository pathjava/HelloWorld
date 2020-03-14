package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleSimpleInfo {
    public static int maxSide(int a, int b, int c) {
        if (a > b & a > c)
            return a;
        else if (b > c & b > a)
            return b;
        else
            return c;
    }

    public static int minSide(int a, int b, int c) {
        if (a < b & a < c)
            return a;
        else if (b < c & b < a)
            return b;
        else
            return c;
    }

    public static boolean isEquilateralTriangle(int a, int b, int c) {
        return (a == b && b == c);
    }

    public static void main(String[] args) {
        System.out.println(maxSide(20, 21, 19));
        System.out.println(minSide(19, 20, 21));
        System.out.println(isEquilateralTriangle(22, 22, 22));
    }
}
