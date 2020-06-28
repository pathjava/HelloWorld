// Oleg Kiselev
// 27.06.2020, 12:00

package ru.progwards.java2.lessons.classloader.profiler;

import java.security.SecureRandom;
import java.util.Arrays;

public class TestSpeed {

    private int sizeTestingArrays;
    private final int[] tempArray = new int[sizeTestingArrays];

    public TestSpeed(int sizeTestingArrays) {
        this.sizeTestingArrays = sizeTestingArrays;
    }

    private void fillArray() {
        SecureRandom random = new SecureRandom();
        Arrays.setAll(tempArray, i -> random.nextInt());
    }

    public void bubbleSort() {
        int[] arr = Arrays.copyOf(tempArray, tempArray.length);
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        Arrays.stream(arr).forEach(System.out::println);
    }

    public void selectionSort() {
        int[] arr = Arrays.copyOf(tempArray, tempArray.length);
        for (int i = 0; i < arr.length; i++)
            for (int j = i + 1; j < arr.length; j++)
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
        Arrays.stream(arr).forEach(System.out::println);
    }

    public void insertionSort() {
        int[] arr = Arrays.copyOf(tempArray, tempArray.length);
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int j = i - 1;
            while (j >= 0 && current < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
        Arrays.stream(arr).forEach(System.out::println);
    }

    public static void main(String[] args) {
        TestSpeed test = new TestSpeed(10);
        test.fillArray();

        test.bubbleSort();
        test.selectionSort();
        test.insertionSort();
    }
}
