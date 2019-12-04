package ru.progwards.java1.lessons.arrays;

public class ArraySort {
    public static void sort(int[] a){
        //int i = a[0];
        int j = a[0];
        for (int i = 0; i < i+1; i++){
            int temp = a[i];
            a[i] = a[i+1];
            a[i] = temp;
            if (a[i] >= a.length){
                for (j = i+1; j < j+1; j++){
                    int temp2 = a[j];
                    a[j] = a[j+1];
                    a[j] = temp2;
                }
            }
        }
    }

    public static void main(String[] args) {
        //System.out.println(a);
    }
}