package ru.progwards.java1.lessons.classes;

public class Animal {

    private double weight;

    enum animalKind{
        ANIMAL,
        COW,
        HAMSTER,
        DUCK,
    }
    public animalKind getKind(){
        return animalKind.ANIMAL;
    }
    enum foodKind{
        UNKNOWN,
        HAY,
        CORN,
    }
    public foodKind getFoodKind(){
        return foodKind.UNKNOWN;
    }
    public String toString(){
        return "I am" + getKind() + ", eat" + getFoodKind() + calculateFoodWeight();
    }

    public Animal animal = new Animal(1);

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
    public static void main(String[] args) {
        System.out.println();
    }
}
