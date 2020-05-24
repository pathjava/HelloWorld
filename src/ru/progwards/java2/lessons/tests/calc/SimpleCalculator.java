// Oleg Kiselev
// 24.05.2020, 15:04

package ru.progwards.java2.lessons.tests.calc;

public class SimpleCalculator implements SimpleCalculatorInterface {

    private boolean checkMaxOrMin(int val1, int val2){
        if (val1 == Integer.MAX_VALUE || val1 == Integer.MIN_VALUE || val2 == Integer.MAX_VALUE || val2 == Integer.MIN_VALUE)
            return false;
        return true;
    }

    @Override
    public int sum(int val1, int val2) {
        int result = 0;
        if (checkMaxOrMin(val1, val2))
            result = val1 + val2;
        return result;
    }

    @Override
    public int diff(int val1, int val2) {
        int result = 0;
        if (checkMaxOrMin(val1, val2))
            result = val1 - val2;
        return result;
    }

    @Override
    public int mult(int val1, int val2) {
        int result = 0;
        if (checkMaxOrMin(val1, val2))
            result = val1 * val2;
        return result;
    }

    @Override
    public int div(int val1, int val2) {
        int result = 0;
        if (checkMaxOrMin(val1, val2) && val2 != 0)
            result = val1 / val2;
        return result;
    }
}
