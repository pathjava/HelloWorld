package ru.progwards.java1.lessons.arrays;

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
    //это мое самостоятельное решение - судя по варианту выше, мыслил в верном направлении, но не совсем
//    public static void sort(int[] a){
//        int i = a[0];
//        int j = a[0];
//        for (int i = 0; i < i+1; i++){
//            int temp = a[i];
//            a[i] = a[i+1];
//            a[i] = temp;
//            if (a[i] >= a.length){
//                for (j = i+1; j < j+1; j++){
//                    if (a[i] > a[j]){
//                        int temp2 = a[j];
//                        a[j] = a[j+1];
//                        a[j] = temp2;
//                    }
//                }
//            }
//        }
//    }
}