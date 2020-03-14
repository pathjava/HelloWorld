package ru.progwards.java1.lessons.classes;

public class Cow extends Animal {
    public Cow(double weight) {
        super(weight);
    }

    @Override
    public AnimalKind getKind() {
        return AnimalKind.COW;
    }

    @Override
    public FoodKind getFoodKind() {
        return FoodKind.HAY;
    }

    public double getFoodCoeff() {
        return 0.05;
    }

    public static void main(String[] args) {
//        Cow animal = new Cow(250);
//        System.out.println(animal);
//        System.out.println(animal.getFoodCoeff());
    }
}
