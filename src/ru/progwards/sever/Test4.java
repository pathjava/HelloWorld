package ru.progwards.sever;

import java.util.Arrays;

public class Test4 {
    public static void main(String[] args) {
        int[] array = new int[] {1, 2, 3};
        int num = 5;
        System.out.println(Arrays.toString(array));
        array = Arrays.copyOf(array, array.length + 1); //create new array from old array and allocate one more element
        array[array.length - 1] = num;
        System.out.println(Arrays.toString(array));
    }
}
