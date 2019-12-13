package ru.progwards.sever.testprogwards;

public class Goat implements Eating, Speaking{
    @Override
    public String eat() {
        return "Сено";
    }

    @Override
    public String say() {
        return "Мее";
    }
}