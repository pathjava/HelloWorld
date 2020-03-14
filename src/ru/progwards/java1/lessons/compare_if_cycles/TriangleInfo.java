package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleInfo {
    public static boolean isTriangle(int a, int b, int c) {
        return ((a < b + c) && (b < a + c) && (c < a + b));
    }

    // при самостоятельном решении допустил ошибку со скобками и только прибегнув к сети нашел ошибку
    public static boolean isRightTriangle(int a, int b, int c) {
        return (a * a + b * b == c * c) || (a * a + c * c == b * b) || (b * b + c * c == a * a);
    }

    public static boolean isIsoscelesTriangle(int a, int b, int c) {
        return a == b || b == c || a == c;
//        return (a == b && b != c)||(a == c && c != b)||(b == c && c != a);
    }

    public static void main(String[] args) {
        System.out.println(isTriangle(20, 20, 20));
        System.out.println(isRightTriangle(3, 4, 5));
        System.out.println(isIsoscelesTriangle(20, 20, 17));
    }
}
