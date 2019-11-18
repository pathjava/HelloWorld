package ru.progwards.java1.lessons.classes;

public class Duck extends Animal {
    @Override
    public animalKind getKind(){
        return animalKind.DUCK;
    }
    @Override
    public foodKind getFoodKind(){
        return foodKind.CORN;
    }
}
