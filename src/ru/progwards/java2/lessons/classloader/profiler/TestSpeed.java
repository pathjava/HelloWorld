// Oleg Kiselev
// 27.06.2020, 12:00

package ru.progwards.java2.lessons.classloader.profiler;

import java.security.SecureRandom;
import java.util.Arrays;

public class TestSpeed {

    public void bubbleSort() {
        SecureRandom random = new SecureRandom();
        int[] arr = new int[10];
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

    public static void main(String[] args) {
        TestSpeed test = new TestSpeed();
        test.bubbleSort();
    }
}
