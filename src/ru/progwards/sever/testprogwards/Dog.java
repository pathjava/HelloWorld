package ru.progwards.sever.testprogwards;

public class Dog implements Eating, Speaking {
    @Override
    public String eat() {
        return "Мясо";
    }

    @Override
    public String say() {
        return "Гав";
    }
}
