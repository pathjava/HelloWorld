package ru.progwards.java1.lessons.classes;

public class Animal {

    public double weight;

    public Animal(double weight) {
        this.weight = weight;
    }

    static enum AnimalKind {ANIMAL, COW, HAMSTER, DUCK,}

    public AnimalKind getKind() {
        return AnimalKind.ANIMAL;
    }

    static enum FoodKind {UNKNOWN, HAY, CORN,}

    public FoodKind getFoodKind() {
        return FoodKind.UNKNOWN;
    }

    public String toString() {
        return "I am " + getKind() + ", eat " + getFoodKind();
    }

    //метод calculateFoodWeight
    public double getWeight() {
        return weight;
    }

    public double getFoodCoeff() {
        return 0.02;
    }

    public double calculateFoodWeight() {
        return getWeight() * getFoodCoeff();
    }

    public String toStringFull() {
        return "I am " + getKind() + ", eat " + getFoodKind() + " " + calculateFoodWeight();
    }

    //не сразу понял как вывести, но после подсказки Григория получилось
    public static void main(String[] args) {
        Animal animal = new Animal(403);
        System.out.println(animal);
        System.out.println(animal + " " + animal.calculateFoodWeight());

        Cow animal1 = new Cow(250);
        System.out.println(animal1);
        System.out.println(animal1 + " " + animal1.calculateFoodWeight());
        Hamster animal2 = new Hamster(150);
        System.out.println(animal2);
        System.out.println(animal2 + " " + animal2.calculateFoodWeight());
        Duck animal3 = new Duck(100);
        System.out.println(animal3);
        System.out.println(animal3 + " " + animal3.calculateFoodWeight());

    }
}
