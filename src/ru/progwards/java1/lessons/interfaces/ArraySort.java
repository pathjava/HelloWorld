package ru.progwards.java1.lessons.interfaces;

import java.util.Arrays;

public class ArraySort {
    public static void sort(CompareWeight[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j].compareWeight(a[j + 1]) == CompareWeight.CompareResult.GREATER) {
                    CompareWeight temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Animal animal = new Animal(0);
        Cow animal1 = new Cow(250);
        Hamster animal2 = new Hamster(150);
        Duck animal3 = new Duck(100);

        CompareWeight[] a = new CompareWeight[4];
        a[1] = animal;
        a[2] = animal1;
        a[3] = animal2;
        a[0] = animal3;

        CompareWeight[] a2 = new CompareWeight[4];
        a2[0] = new Food(60);
        a2[1] = new Food(90);
        a2[2] = new Food(45);
        a2[3] = new Food(20);

        sort(a);
        sort(a2);

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(a2));
    }
}