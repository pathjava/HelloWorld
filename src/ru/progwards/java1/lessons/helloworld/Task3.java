package ru.progwards.java1.lessons.helloworld;

public class Task3 {
    public static int addition(int x, int y){
        int z = x + y;
        System.out.print("Вызвана функция addition() ");
        System.out.println(z);
        return z;
    }

    public static int subtraction(int x, int y){
        int w = x - y;
        System.out.print("Вызвана функция subtraction() ");
        System.out.println(w);
        return w;
    }

    public static int multiplication(int x, int y){
        int d = x * y;
        System.out.print("Вызвана функция multiplication() ");
        System.out.println(d);
        return d;
    }

    public static void main(String[] args) {
        int a = 34;
        int b = 55;
        int c;

        System.out.print("a = ");
        System.out.println(a);
        System.out.print("b = ");
        System.out.println(b);

        System.out.println();

        c = addition(a, b);
        System.out.print("a + b = ");
        System.out.println(c);

        c = subtraction(a, b);
        System.out.print("a - b = ");
        System.out.println(c);

        c = multiplication(a, b);
        System.out.print("a * b = ");
        System.out.println(c);

    }
}
