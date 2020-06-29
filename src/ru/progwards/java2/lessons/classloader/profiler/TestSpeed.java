// Oleg Kiselev
// 27.06.2020, 12:00

package ru.progwards.java2.lessons.classloader.profiler;

import ru.progwards.java1.lessons.datetime.Profiler;

import java.security.SecureRandom;
import java.util.Arrays;


public class TestSpeed {

    private final boolean showResultSort;
    private final int[] tempArray;

    public TestSpeed(int sizeTestingArrays, boolean showResultSort) {
        tempArray = new int[sizeTestingArrays];
        this.showResultSort = showResultSort;
    }

    private void fillArray() {
        SecureRandom random = new SecureRandom();
        Arrays.setAll(tempArray, i -> random.nextInt());
    }

    public void bubbleSort() {
        Profiler.enterSection("ru.progwards.java2.lessons.classloader.profiler.TestSpeed.bubbleSort()");
        int[] arr = Arrays.copyOf(tempArray, tempArray.length);
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        if (showResultSort)
            Arrays.stream(arr).forEach(System.out::println);
        Profiler.exitSection("ru.progwards.java2.lessons.classloader.profiler.TestSpeed.bubbleSort()");
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
        if (showResultSort)
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
        if (showResultSort)
            Arrays.stream(arr).forEach(System.out::println);
    }

    public static void main(String[] args) {
        TestSpeed test = new TestSpeed(10, false);
        test.fillArray();

        test.bubbleSort();
        test.selectionSort();
        test.insertionSort();
    }
}
