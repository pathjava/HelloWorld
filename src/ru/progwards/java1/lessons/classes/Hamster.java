package ru.progwards.java1.lessons.classes;

public class Hamster extends Animal {
    @Override
    public animalKind getKind(){
        return animalKind.HAMSTER;
    }
    @Override
    public foodKind getFoodKind(){
        return foodKind.CORN;
    }
}
