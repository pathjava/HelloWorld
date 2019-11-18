package ru.progwards.java1.lessons.classes;

public class Cow extends Animal {
    @Override
    public animalKind getKind(){
        return animalKind.COW;
    }
    @Override
    public foodKind getFoodKind(){
        return foodKind.HAY;
    }
}
