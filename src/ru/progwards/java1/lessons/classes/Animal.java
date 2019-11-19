package ru.progwards.java1.lessons.classes;

public class Animal {

    private double weight;

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
        return "I am" + getKind() + ", eat" + getFoodKind() + calculateFoodWeight();
    }

    private Animal animal = new Animal(1);

    public double getWeight(){
        return weight;
    }
    public Animal(double weight){
        this.weight = weight;
    }
    public double getFoodCoeff(){
        return animal.getWeight() * 0.02;
    }

    public double calculateFoodWeight(){
         return getWeight() * getFoodCoeff();
    }

    //честно говоря запутался и не понял как вывести информацию для проверки
//    public static void main(String[] args) {
//        Animal animal = new Animal(20);
//        System.out.println(animal);
//    }
}
