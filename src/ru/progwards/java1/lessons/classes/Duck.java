package ru.progwards.java1.lessons.classes;

public class Duck extends Animal {
    public Duck(double weight) {
        super(weight);
    }

    @Override
    public animalKind getKind(){
        return animalKind.DUCK;
    }
    @Override
    public foodKind getFoodKind(){
        return foodKind.CORN;
    }

    private Duck animal = new Duck(5);

    public double getFoodCoeff(){
        return animal.getFoodCoeff() * 0.04;
    }
}
