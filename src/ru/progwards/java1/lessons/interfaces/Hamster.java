package ru.progwards.java1.lessons.interfaces;

public class Hamster extends Animal {
    public Hamster(double weight) {
        super(weight);
    }

    @Override
    public AnimalKind getKind() {
        return AnimalKind.HAMSTER;
    }

    @Override
    public FoodKind getFoodKind() {
        return FoodKind.CORN;
    }

    public double getFoodCoeff() {
        return 0.03;
    }

    public static void main(String[] args) {
        Hamster animal = new Hamster(3);
        System.out.println(animal);
        System.out.println(animal.getFoodCoeff());
    }
}