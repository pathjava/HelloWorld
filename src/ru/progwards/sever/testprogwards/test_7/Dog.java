package ru.progwards.sever.testprogwards.test_7;

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
