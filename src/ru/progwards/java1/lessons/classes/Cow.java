package ru.progwards.java1.lessons.classes;

public class Cow extends Animal {
    public Cow(double weight) {
        super(weight);
    }

    @Override
    public animalKind getKind(){
        return animalKind.COW;
    }
    @Override
    public foodKind getFoodKind(){
        return foodKind.HAY;
    }

    private Cow animal = new Cow(20);

    public double getFoodCoeff(){
        return animal.getFoodCoeff() * 0.05;
    }
}
