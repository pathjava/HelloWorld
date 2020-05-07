// Oleg Kiselev
// 06.05.2020, 20:45

package ru.progwards.sever.testprogwards2.test_02;

public class RecursionTest {

    public static int sumSequence(int n) {
        if (n == 1)
            return n;
        return sumSequence(n - 2) + n;
    }

    public static int sumSequenceTwo(int n) {
        if (n == 1)
            return n;
        int sumSequence = sumSequenceTwo(n - 2);
        return n + sumSequence;
    }



    public static int factorial(int val) {
        if (val <= 1)
            return 1;
        return factorial(val - 1) * val;
    }

    public static int factorialTwo(int n) {
        if (n == 0)
            return 1;
        int factorialMinusOne = factorialTwo(n - 1);
        return n * factorialMinusOne;
    }

    public static int factorialThree(int x) {
        int result = 1;
        for (int i = 1; i <= x; i++) {
            result *= i;
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(sumSequence(3));
        System.out.println(sumSequenceTwo(3));
        System.out.println(factorial(5));
        System.out.println(factorialTwo(5));
        System.out.println(factorialThree(5));
    }
}
