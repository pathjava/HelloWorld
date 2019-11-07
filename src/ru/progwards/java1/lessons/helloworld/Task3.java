package ru.progwards.java1.lessons.helloworld;

public class Task3 {
    public static int addition(int x, int y){
        int z = x + y;
        System.out.println("Вызвана функция addition() " +z);
        return z;
    }

    public static int subtraction(int x, int y){
        int w = x - y;
        System.out.println("Вызвана функция subtraction() " +w);
        return w;
    }

    public static int multiplication(int x, int y){
        int d = x * y;
        System.out.println("Вызвана функция multiplication() " +d);
        return d;
    }

    public static void main(String[] args) {
        int a = 34;
        int b = 55;
        int x = a;
        int y = b;
        int c = x + y;
        int z = addition(a, b);
        int w = subtraction(a, b);
        int d = multiplication(a, b);

        System.out.println("a = "+ a);
        System.out.println("b = "+ b);
        System.out.println("a + b = "+ z);
        System.out.println("a - b = "+ w);
        System.out.println("a * b = "+ d);
    }
}
