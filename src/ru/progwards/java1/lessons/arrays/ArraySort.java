package ru.progwards.java1.lessons.arrays;

public class ArraySort {
    /* данную сортировку написал сам, но только уже после прохождения 13 урока */
    public static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    public static void sort2(int[] a) {
        int i = 0;
        while (i < a.length) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
            i++;
        }
    }

    public static void sort3(int[] a) {
        //данное решение подсмотрел в Гугл после долгой самостоятельной битвы над задачей (мое решение закомментировано ниже)
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {23, 55, 3, 3, -45, 270, 15, 1};
        sort(arr);
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();

        int[] arr2 = {23, 55, 3, 3, -45, 270, 15, 1};
        sort2(arr2);
        for (int value : arr2) {
            System.out.print(value + " ");
        }
        System.out.println();

        int[] arr3 = {23, 55, 3, 3, -45, 270, 15, 1};
        sort3(arr3);
        for (int value : arr3) {
            System.out.print(value + " ");
        }
    }
}