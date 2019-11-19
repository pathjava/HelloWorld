package ru.progwards.java1.lessons.classes;

public class Cow extends Animal {
    Cow(double weight) {
        super(weight);
    }

    @Override
    public AnimalKind getKind(){
        return AnimalKind.COW;
    }
    @Override
    public FoodKind getFoodKind(){
        return FoodKind.HAY;
    }

    public double getFoodCoeff(){
        return getFoodCoeff() * 0.05;
    }

    public static void main(String[] args) {
        Cow animal = new Cow(250);
        System.out.println(animal);
        System.out.println(animal.getFoodCoeff());
//        System.out.println(animal.getCoeff());
    }
}
