package ru.progwards.java1.lessons.interfaces;

public class ArraySort implements CompareWeight {
    public static void sort(CompareWeight[] a){
        int n = a.length;
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++){
                if (a[j] > a[j+1]){
                    CompareWeight temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }

//    public static void sort(CompareWeight[] a) {
//        int n = a.length;
//        for (int i = 0; i < n; i++) {
//            int min = i;
//            for (int j = i+1; j < n; j++) {
//                if (less(a[j], a[min])) min = j;
//            }
//            exch(a, i, min);
//            assert isSorted(a, 0, i);
//        }
//        assert isSorted(a);
//    }
//    // is the array sorted from a[lo] to a[hi]
//    private static boolean isSorted(CompareWeight[] a, int lo, int hi) {
//        for (int i = lo + 1; i <= hi; i++)
//            if (less(a[i], a[i-1])) return false;
//        return true;
//    }

    @Override
    public CompareResult compareWeight(CompareWeight smthHasWeigt) {
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {23, 55, 3, 3, -45, 270, 15, 1};
        sort(arr);
        for (int value : arr) {
            System.out.print(value + " ");
        }
    }
}