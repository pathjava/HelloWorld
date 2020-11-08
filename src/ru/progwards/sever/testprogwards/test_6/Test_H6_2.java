package ru.progwards.sever.testprogwards.test_6;

import java.util.Arrays;

//public class Test_H6_2 {
//    public static void main(String[] args) {
//        int [] mas = {11, 3, 14, 16, 7};
//
//        boolean isSorted = false;
//        int buf;
//        while(!isSorted) {
//            isSorted = true;
//            for (int i = 0; i < mas.length-1; i++) {
//                if(mas[i] > mas[i+1]){
//                    isSorted = false;
//
//                    buf = mas[i];
//                    mas[i] = mas[i+1];
//                    mas[i+1] = buf;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(mas));
//    }
//}


public class Test_H6_2 {
    public static void main(String args[]) {
        int[] ar = {12, -3, 8, 1, 19, 22, 0};
        int temp;

        for (int i = 0; i < ar.length; i++) {
            for (int j = 1; j < (ar.length - i); j++) {
                if (ar[j - 1] > ar[j]) {
                    temp = ar[j - 1];
                    ar[j - 1] = ar[j];
                    ar[j] = temp;
                }
            }
        }

        for (int i = 0; i < ar.length; i++) {
            System.out.print(ar[i] + " ");
        }
    }
}
