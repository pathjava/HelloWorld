package ru.progwards.java1.lessons.classes;

public class Hamster extends Animal {
    public Hamster(double weight) {
        super(weight);
    }

    @Override
    public animalKind getKind(){
        return animalKind.HAMSTER;
    }
    @Override
    public foodKind getFoodKind(){
        return foodKind.CORN;
    }

    private Hamster animal = new Hamster(getWeight());

    public double getFoodCoeff(){
        return animal.getFoodCoeff() * 0.03;
    }
}
