// Oleg Kiselev
// 24.05.2020, 15:04

package ru.progwards.java2.lessons.tests.calc;

public class SimpleCalculator implements SimpleCalculatorInterface {

    private boolean checkMaxOrMin(long result) {
        return result > Integer.MAX_VALUE || result < Integer.MIN_VALUE;
    }

    @Override
    public int sum(int val1, int val2) {
        long result = (long) val1 + val2;
        if (checkMaxOrMin(result))
            throw new ArithmeticException();

        return val1 + val2;
    }

    @Override
    public int diff(int val1, int val2) {
        long result = (long) val1 - val2;
        if (checkMaxOrMin(result))
            throw new ArithmeticException();

        return val1 - val2;
    }

    @Override
    public int mult(int val1, int val2) {
        long result = (long) val1 * val2;
        if (checkMaxOrMin(result))
            throw new ArithmeticException();

        return val1 * val2;
    }

    @Override
    public int div(int val1, int val2) {
        long result = (long) val1 / val2;
        if (checkMaxOrMin(result))
            throw new ArithmeticException();
        if (val2 == 0)
            throw new ArithmeticException();

        return val1 / val2;
    }
}
