// Oleg Kiselev
// 06.05.2020, 20:23

package ru.progwards.sever.testprogwards2.test_01;

public class FactorialStepInfo {
    public static int factorial(int val) {
        System.out.println("Прямой ход, val = " + val);

        int result = val <= 1 ? 1 : factorial(val - 1) * val;

        System.out.println("Обратный ход, val = " + val + ", result = " + result);

        return result;
    }

    public static void main(String[] args) {
        factorial(5);
    }
}
