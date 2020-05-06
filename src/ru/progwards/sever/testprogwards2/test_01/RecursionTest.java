// Oleg Kiselev
// 06.05.2020, 20:45

package ru.progwards.sever.testprogwards2.test_01;

public class RecursionTest {
    public static int sumSequence(int n) {
        int result;
        if (n == 1)
            return n;
        result = sumSequence(n - 2) + n;

        return result;
    }


    public static int factorial(int val) {
        // условие выхода
        if (val <= 1)
            return 1;

        // рекурсия (n - 1)! * n
        return factorial(val - 1) * val;
    }


    public static void main(String[] args) {
        System.out.println(sumSequence(3));
        System.out.println(factorial(5));
    }
}
