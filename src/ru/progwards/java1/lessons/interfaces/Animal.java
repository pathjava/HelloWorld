package ru.progwards.java1.lessons.interfaces;

public class Animal implements FoodCompare, CompareWeight {

    public double weight;

    public Animal(double weight) {
        this.weight = weight;
    }

    enum AnimalKind {ANIMAL, COW, HAMSTER, DUCK;}

    public AnimalKind getKind() {
        return AnimalKind.ANIMAL;
    }

    enum FoodKind {UNKNOWN, HAY, CORN;}

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

    //возвращает true, если объекты равны и false если не равны по параметру - вес животного
    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;

        Animal animal = (Animal) anObject;

        return Double.compare(animal.calculateFoodWeight(), calculateFoodWeight()) == 0;
    }

    //возвращает информацию о цене 1 кг еды
    public double getFood1kgPrice() {
        switch (getFoodKind()) {
            case HAY:
                return 20;
            case CORN:
                return 50;
            case UNKNOWN:
                return 0;
        }
        return 0;
    }

    //возвращает информацию о цене еды для данного животного по формуле calculateFoodWeight() * getFood1kgPrice()
    public double getFoodPrice() {
        return calculateFoodWeight() * getFood1kgPrice();
    }

    //возвращает результаты сравнения цены еды для данного животного с ценой еды для другого животного
    @Override
    public int compareFoodPrice(Animal animal) {
        return Double.compare(getFoodPrice(), animal.getFoodPrice());
    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        Animal otherWeight = (Animal) smthHasWeigt;
        if (this.getWeight() < otherWeight.getWeight())
            return CompareResult.LESS;
        else if (this.getWeight() == otherWeight.getWeight())
            return CompareResult.EQUAL;
        else
            return CompareResult.GREATER;
    }

    public static void main(String[] args) {
        Animal animal = new Animal(0);
        System.out.println(animal);
        System.out.println("Food Weight " + animal.calculateFoodWeight());
        System.out.println("Food Price " + animal.getFoodPrice());
        System.out.println("Food 1kg Price " + animal.getFood1kgPrice());
        System.out.println();
        Cow animal1 = new Cow(250);
        System.out.println(animal1);
        System.out.println("Food Weight " + animal1.calculateFoodWeight());
        System.out.println("Food Price " + animal1.getFoodPrice());
        System.out.println("Food 1kg Price " + animal1.getFood1kgPrice());
        System.out.println();
        Hamster animal2 = new Hamster(150);
        System.out.println(animal2);
        System.out.println("Food Weight " + animal2.calculateFoodWeight());
        System.out.println("Food Price " + animal2.getFoodPrice());
        System.out.println("Food 1kg Price " + animal2.getFood1kgPrice());
        System.out.println();
        Duck animal3 = new Duck(100);
        System.out.println(animal3);
        System.out.println("Food Weight " + animal3.calculateFoodWeight());
        System.out.println("Food Price " + animal3.getFoodPrice());
        System.out.println("Food 1kg Price " + animal3.getFood1kgPrice());
    }
}