package ru.progwards.java1.lessons.arrays;

public class ArraySort {
    public static void sort(int[] a){
        //int i = a[0];
        //int j = a[0];
//        for (int i = 0; i > i+1; i++){
//            int temp = a[i];
//            a[i] = a[i+1];
//            a[i] = temp;
//
//        }
        int n = a.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (a[j] > a[j+1])
                {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
    }

    public static void main(String[] args) {
//        ArraySort arraySort = new ArraySort();
//        int[] a = {64, 34, 25, 12, 22, 11, 90};
//        arraySort.sort(a);
//        System.out.println(a);
        //arraySort.printArray(a);
    }
}