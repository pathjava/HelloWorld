package ru.progwards.java1.lessons.interfaces;

public class ArraySort {
    public static void sort(int[] a){
        //данное решение подсмотрел в Гугл после долгой самостоятельной битвы над задачей (мое решение закомментировано ниже)
        int n = a.length;
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++){
                if (a[j] > a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
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
    }
}