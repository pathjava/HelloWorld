package ru.progwards.java1.lessons.classes;

public class Animal {
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
        return "I am" + getKind() + ", eat" + getFoodKind();
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
