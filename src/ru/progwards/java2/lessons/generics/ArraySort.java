// Oleg Kiselev
// 12.05.2020, 21:06

package ru.progwards.java2.lessons.generics;

import java.util.Arrays;

public class ArraySort {

    private static void sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int temp;
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    public static void main(String[] args) {
        sort(new int[]{5, 1, 14, 34, -4, 22, 3, 1, 3, 100, 17});
    }

}
