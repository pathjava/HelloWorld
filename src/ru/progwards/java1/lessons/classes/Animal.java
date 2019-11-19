package ru.progwards.java1.lessons.classes;

public class Animal {

    public double weight;

    enum AnimalKind{
        ANIMAL,
        COW,
        HAMSTER,
        DUCK,
    }
    public AnimalKind getKind(){
        return AnimalKind.ANIMAL;
    }
    enum FoodKind{
        UNKNOWN,
        HAY,
        CORN,
    }
    public FoodKind getFoodKind(){
        return FoodKind.UNKNOWN;
    }
    public String toString(){
        return "I am " + getKind() + ", eat " + getFoodKind() + " " + calculateFoodWeight();
    }
    //метод calculateFoodWeight
    public double getWeight(){
        return weight;
    }
    public Animal(double weight){
        this.weight = weight;
    }
    public double getFoodCoeff(){
        return getWeight() * 0.02;
    }

    public double calculateFoodWeight(){
         return getWeight() * getFoodCoeff();
    }

    //не сразу понял как вывести, но после подсказки Григория получилось
    public static void main(String[] args) {
        Animal animal = new Animal(50);
        System.out.println(animal);
        System.out.println(animal.getFoodCoeff());

        Cow animal1 = new Cow(250);
        System.out.println(animal1);
        System.out.println(animal1.getFoodCoeff());
        Hamster animal2 = new Hamster(150);
        System.out.println(animal2);
        System.out.println(animal2.getFoodCoeff());
        Duck animal3 = new Duck(100);
        System.out.println(animal3);
        System.out.println(animal3.getFoodCoeff());
    }
}
