// Oleg Kiselev
// 29.06.2020, 20:47

package ru.progwards.java2.lessons.classloader.test;

import java.security.SecureRandom;
import java.util.Arrays;

public class TestExcludedClass {

    private final boolean showResultSort;
    private final int[] tempArray;

    public TestExcludedClass(int sizeTestingArrays, boolean showResultSort) {
        tempArray = new int[sizeTestingArrays];
        this.showResultSort = showResultSort;
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
        if (showResultSort)
            Arrays.stream(arr).forEach(System.out::println);
    }

    public static void main(String[] args) {
        TestExcludedClass test = new TestExcludedClass(100, true);
        test.fillArray();
        test.bubbleSort();
    }
}
