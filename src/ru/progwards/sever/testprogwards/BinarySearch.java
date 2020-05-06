// Oleg Kiselev
// 06.05.2020, 14:50

package ru.progwards.sever.testprogwards;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{8, 74, -12, 1, 25, 9, 7, 0, 9, 120, 247, -30};
        Arrays.sort(arr);
        System.out.println(quickSort(arr, 25));
    }

    public static int quickSort(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (key < arr[middle])
                high = middle - 1;
            else if (key > arr[middle])
                low = middle + 1;
            else
                return middle;
        }
        return -1;
    }
}
