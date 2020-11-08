package ru.progwards.sever.testprogwards.test_7;

public class Goat implements Eating, Speaking {
    @Override
    public String eat() {
        return "Сено";
    }

    @Override
    public String say() {
        return "Мее";
    }
}