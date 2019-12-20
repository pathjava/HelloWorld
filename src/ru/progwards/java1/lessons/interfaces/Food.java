package ru.progwards.java1.lessons.interfaces;

public class Food implements CompareWeight {
    private int weight;

    public Food(int weight) {
        this.weight = weight;
    }

    public int getWeight(){
        return weight;
    }

    @Override
    public boolean compareWeight(CompareWeight smthHasWeigt) {
        Food otherWeight = (Food) smthHasWeigt;
         if (this.getWeight() < otherWeight.getWeight())
             return CompareResult.LESS;
         else if (this.getWeight() == otherWeight.getWeight())
             return CompareResult.EQUAL;
         else
             return CompareResult.GREATER;
    }

    public static void main(String[] args) {
        Food food = new Food(12);
        System.out.println();
    }
}
