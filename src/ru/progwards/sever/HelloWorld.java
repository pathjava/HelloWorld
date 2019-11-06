package ru.progwards.sever;

public class HelloWorld {
    static void println(String str){
        System.out.println(str);
    }

    static int addition(int x, int y){
        return x + y;
    }

    static int addition1(int x, int y){
        return x + y;
    }

    public static void main(String[] args) {
        println("Hello World!");
        println("World, are you hear me?");
        System.out.print("Я знаю, что 7 + 5 = ");
        System.out.println(addition(7, 5));
    }
}