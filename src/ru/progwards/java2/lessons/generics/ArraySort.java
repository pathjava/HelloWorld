// Oleg Kiselev
// 12.05.2020, 21:06

package ru.progwards.java2.lessons.generics;

import java.util.Arrays;

public class ArraySort {

    private static <T extends Comparable<T>> void sort(T[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                T temp;
                if (arr[i].compareTo(arr[j]) > 0) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    public static void main(String[] args) {

        Integer[] intArr = {5, 1, 14, 34, -4, 22, 3, 1, 3, 100, 17};
        Double[] doubleArr = {5.91, 1.8, 14.90, 34.7, -4.2, 22.25, 3.3, 1.8, 3.3, 100.10, 17.120};
        String[] strArr = {"Михаил", "Алексей", "Сергей", "Игорь", "Даша", "Лена", "Bill", "Mike", "Alex"};
        sort(intArr);
        sort(strArr);
        sort(doubleArr);
    }

}
