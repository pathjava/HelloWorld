// Oleg Kiselev
// 24.05.2020, 15:21

package ru.progwards.java2.lessons.tests.test.calc;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.progwards.java2.lessons.tests.calc.SimpleCalculator;

import static org.junit.Assert.*;

public class SimpleCalculatorTest {

    private int val1 = 5;
    private int val2 = 7;
    public static SimpleCalculator simpleCalculator;

    @BeforeClass
    public static void createInstance() {
        simpleCalculator = new SimpleCalculator();
    }

    @Test
    public void sumTestMethod() {
        int actual = simpleCalculator.sum(7, 5);
        int expected = 12;
        assertEquals(expected, actual);
    }

    @Test
    public void diffTestMethod() {
        int actual = simpleCalculator.diff(7, 5);
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void multTestMethod() {
        int actual = simpleCalculator.mult(7, 5);
        int expected = 35;
        assertEquals(expected, actual);
    }

    @Test
    public void divTestMethod() {
        int actual = simpleCalculator.div(7, 5);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test(expected = ArithmeticException.class)
    public void divTestByZero() {
        int actual = simpleCalculator.div(7, 0);
        int expected = 1;
        assertEquals(expected, actual);
    }
}