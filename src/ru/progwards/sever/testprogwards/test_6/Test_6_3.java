package ru.progwards.sever.testprogwards.test_6;

import java.util.Arrays;

public class Test_6_3 {
    public static int arrayMax(int[] a) {
        int s = a[0];
        for (int i = 0; i < a.length; i++) {
            if (s < a[i]) {
                s = a[i];
            }
        }
        return s;
    }

    public static void main(String[] args) {
        int[] max = {1, -145, 77, 0, 32};
        System.out.println(arrayMax(max));
        Arrays.sort(max);
        System.out.println(arrayMax(max));
    }
}
