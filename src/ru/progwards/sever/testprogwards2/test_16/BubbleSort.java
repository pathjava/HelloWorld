package ru.progwards.sever.testprogwards2.test_16;

public class BubbleSort {
    public static<T extends Comparable<T>> void sort(T[] a) {
        for (int i=0; i<a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                int n = j+1;
                if (a[j].compareTo(a[n]) < 0) {
                    T tmp = a[j];
                    a[j] = a[n];
                    a[j] = tmp;
                }
            }
        }
    }
}
