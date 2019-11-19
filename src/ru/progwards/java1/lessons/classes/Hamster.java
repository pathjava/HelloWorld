package ru.progwards.java1.lessons.classes;

public class Hamster extends Animal {
    public Hamster(double weight) {
        super(weight);
    }

    @Override
    public AnimalKind getKind(){
        return AnimalKind.HAMSTER;
    }
    @Override
    public FoodKind getFoodKind(){
        return FoodKind.CORN;
    }

    private Hamster animal = new Hamster(3);

    public double getFoodCoeff(){
        return animal.getFoodCoeff() * 0.03;
    }
}
