// Oleg Kiselev
// 24.05.2020, 15:21

package ru.progwards.java2.lessons.tests.test.calc;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.progwards.java2.lessons.tests.calc.SimpleCalculator;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SimpleCalculatorTest {

    private final int valOne;
    private final int valTwo;
    private final int expected;

    public SimpleCalculatorTest(int valOne, int valTwo, int expected) {
        this.valOne = valOne;
        this.valTwo = valTwo;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static List<Integer[]> isEmptyData() {
        return Arrays.asList(new Integer[][]{
                {7, 5, 12},
                {7, 0, 7},
                {20, 20, 40},
                {55, 5, 60},
        });
    }

    public static SimpleCalculator simpleCalculator;

    @BeforeClass
    public static void createInstance() {
        simpleCalculator = new SimpleCalculator();
    }

    @Test
    public void sumTestMethod() {
        assertEquals(expected, simpleCalculator.sum(valOne, valTwo));
    }

    @Test
    public void diffTestMethod() {
        assertEquals(expected, simpleCalculator.diff(valOne, valTwo));
    }

    @Test
    public void multTestMethod() {
        assertEquals(expected, simpleCalculator.mult(valOne, valTwo));
    }

    @Test
    public void divTestMethod() {
        assertEquals(expected, simpleCalculator.div(valOne, valTwo));
    }

    @Test(expected = ArithmeticException.class)
    public void divTestByZero() {
        assertEquals(expected, simpleCalculator.div(valOne, valTwo));
    }
}