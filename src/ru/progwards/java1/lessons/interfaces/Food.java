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
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
         if (this.getWeight() < getWeight())
             return CompareResult.LESS;
         else if (this.getWeight() == getWeight())
             return CompareResult.EQUAL;
         else
             return CompareResult.GREATER;
    }
}
