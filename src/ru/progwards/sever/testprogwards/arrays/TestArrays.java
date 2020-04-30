// Oleg Kiselev
// 30.04.2020, 19:05

package ru.progwards.sever.testprogwards.arrays;

import java.util.Arrays;

public class TestArrays {
    public static int[] subArray(int[] array, int from, int to) {
        if (from < 0 || from > array.length)
            return null;
        if (to > array.length - 1 || to < 0)
            return null;
        if (from > to)
            return null;

        int[] arr = new int[(to - from) + 1];
        int index = 0;
        for (int i = from; i <= to; i++) {
            arr[index] = array[i];
            index++;
        }
        return arr;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(subArray(new int[]{12, 4, 33, 17, 98, 0}, 2, 4)));
    }
}
