package ru.progwards.java1.lessons.interfaces;

public interface CompareWeight {
//    int getWeight();

    enum CompareResult {LESS, EQUAL, GREATER;}

    CompareResult compareWeight(CompareWeight smthHasWeigt);
}
