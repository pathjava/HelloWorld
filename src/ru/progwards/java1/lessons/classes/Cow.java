package ru.progwards.java1.lessons.classes;

public class Cow extends Animal {

    private Cow(double weight) {
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

    private Cow animal = new Cow(20);

    public double getFoodCoeff(){
        return animal.getFoodCoeff() * 0.05;
    }

    public static void main(String[] args) {
//        Cow animal = new Cow(20);
//        System.out.println(animal);
    }
}
