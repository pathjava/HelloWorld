package ru.progwards.java1.lessons.interfaces;

public interface CompareWeight {
//    int getWeight();

    public enum CompareResult{LESS, EQUAL, GREATER;}

    public boolean compareWeight(CompareWeight smthHasWeigt);
}
