package ru.progwards.java1.lessons.interfaces;

public class Animal implements FoodCompare {

    public double weight;

    public Animal(double weight) {
        this.weight = weight;
    }

    //private AnimalKind animalKind = AnimalKind.ANIMAL;
    static enum AnimalKind{ANIMAL, COW, HAMSTER, DUCK,}
    public AnimalKind getKind(){
        return AnimalKind.ANIMAL;
    }

    private FoodKind foodKind = FoodKind.UNKNOWN;
    static enum FoodKind{UNKNOWN, HAY, CORN,}
    public FoodKind getFoodKind(){
        return FoodKind.UNKNOWN;
    }

    public String toString(){
        return "I am " + getKind() + ", eat " + getFoodKind();
    }

    //метод calculateFoodWeight
    public double getWeight(){
        return weight;
    }
    public double getFoodCoeff(){
        return 0.02;
    }
    public double calculateFoodWeight(){
        return getWeight() * getFoodCoeff();
    }
    public String toStringFull(){
        return "I am " + getKind() + ", eat " + getFoodKind() + " " + calculateFoodWeight();
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;

        Animal animal = (Animal) anObject;

        return Double.compare(animal.calculateFoodWeight(), calculateFoodWeight()) == 0;
    }

    public double getFood1kgPrice(){
        double result = 0;
        switch (foodKind){
            case HAY:
                return 20;
            case CORN:
                return 50;
            case UNKNOWN:
                return 0;
        }
        return result;
    }
    public double getFoodPrice(){
        return calculateFoodWeight() * getFood1kgPrice();
    }

    @Override
    public int compareFoodPrice(Animal animal) {
        return Double.compare(getFoodPrice(), animal.getFoodPrice());
    }

    //не сразу понял как вывести, но после подсказки Григория получилось
    public static void main(String[] args) {
        Animal animal = new Animal(403);
        System.out.println(animal);
        System.out.println(animal + " " + animal.calculateFoodWeight());
        System.out.println(animal + " " + animal.getFoodPrice());
        System.out.println();
        Cow animal1 = new Cow(250);
        System.out.println(animal1);
        System.out.println(animal1 + " " + animal1.calculateFoodWeight());
        System.out.println(animal1 + " " + animal1.getFoodPrice());
        System.out.println();
        Hamster animal2 = new Hamster(150);
        System.out.println(animal2);
        System.out.println(animal2 + " " + animal2.calculateFoodWeight());
        System.out.println(animal2 + " " + animal2.getFoodPrice());
        System.out.println();
        Duck animal3 = new Duck(100);
        System.out.println(animal3);
        System.out.println(animal3 + " " + animal3.calculateFoodWeight());
        System.out.println(animal3 + " " + animal3.getFoodPrice());

    }
}