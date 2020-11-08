// Oleg Kiselev
// 06.05.2020, 14:50

package ru.progwards.sever.testprogwards;

public class HelloWorld {
    static void println(String str) {
        System.out.println(str);
    }

    static int addition(int x, int y) {
        return x + y;
    }

    public static void main(String[] args) {
        println("Hello World!");
        println("World, are you hear me?");
        System.out.print("Я знаю, что 7 + 5 = ");
        System.out.println(addition(7, 5));
    }
}