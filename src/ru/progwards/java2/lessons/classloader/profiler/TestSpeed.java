// Oleg Kiselev
// 27.06.2020, 12:00

package ru.progwards.java2.lessons.classloader.profiler;

import java.security.SecureRandom;
import java.util.Arrays;

public class TestSpeed {

    private final SecureRandom random = new SecureRandom();
    private static final int SIZE_ARRAY = 10;

    public void bubbleSort() {
        int[] arr = new int[SIZE_ARRAY];
        Arrays.setAll(arr, i -> random.nextInt());
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        Arrays.stream(arr).forEach(System.out::println);
    }

    public void selectionSort() {
        int[] arr = new int[SIZE_ARRAY];
        Arrays.setAll(arr, i -> random.nextInt());
        for (int i = 0; i < arr.length; i++)
            for (int j = i + 1; j < arr.length; j++)
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
    }

    public void insertionSort() {
        int[] arr = new int[SIZE_ARRAY];
        Arrays.setAll(arr, i -> random.nextInt());
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;
            while (j >= 0 && current < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
    }

    public static void main(String[] args) {
        TestSpeed test = new TestSpeed();
        test.bubbleSort();
        test.selectionSort();
        test.insertionSort();
    }
}
