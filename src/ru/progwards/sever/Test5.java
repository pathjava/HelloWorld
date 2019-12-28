package ru.progwards.sever;

public class Test5 {
    public static void printStrAndHash(String str) {
        System.out.println(str.hashCode() + " " + str);
    }

    public static void main(String[] args) {
        printStrAndHash("Зачем");
        printStrAndHash("нужны");
        printStrAndHash("хеш-таблицы?");
        printStrAndHash("Чтобы");
        printStrAndHash("поиск");
        printStrAndHash("летал");
        printStrAndHash("как");
        printStrAndHash("птица.");
    }
}