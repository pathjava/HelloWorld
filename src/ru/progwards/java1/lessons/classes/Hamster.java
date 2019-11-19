package ru.progwards.java1.lessons.classes;

public class Hamster extends Animal {
//    public Hamster(double i) {
//    }

    @Override
    public AnimalKind getKind(){
        return AnimalKind.HAMSTER;
    }
    @Override
    public FoodKind getFoodKind(){
        return FoodKind.CORN;
    }

    public double getFoodCoeff(){
        return 0.03;
    }
}
