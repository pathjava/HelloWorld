// Oleg Kiselev
// 24.05.2020, 15:21

package ru.progwards.java2.lessons.tests.test.calc;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.progwards.java2.lessons.tests.calc.SimpleCalculator;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class SimpleCalculatorTest {

    public static SimpleCalculator simpleCalculator;

    @BeforeClass
    public static void createInstance() {
        simpleCalculator = new SimpleCalculator();
    }

    @RunWith(Parameterized.class)
    public static class SumCalculatorTest {
        private final int valOne;
        private final int valTwo;
        private final int expected;

        public SumCalculatorTest(int valOne, int valTwo, int expected) {
            this.valOne = valOne;
            this.valTwo = valTwo;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static List<Integer[]> sumTest() {
            return Arrays.asList(new Integer[][]{
                    {7, 5, 12},
                    {7, 0, 7},
                    {20, 20, 40},
                    {55, 5, 60},
            });
        }

        @Test
        public void sumTestMethod() {
            assertEquals(expected, simpleCalculator.sum(valOne, valTwo));
        }
    }

    @RunWith(Parameterized.class)
    public static class DiffCalculatorTest {
        private final int valOne;
        private final int valTwo;
        private final int expected;

        public DiffCalculatorTest(int valOne, int valTwo, int expected) {
            this.valOne = valOne;
            this.valTwo = valTwo;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static List<Integer[]> sumTest() {
            return Arrays.asList(new Integer[][]{
                    {7, 5, 2},
                    {7, 0, 7},
                    {20, 20, 0},
                    {55, 5, 50},
            });
        }

        @Test
        public void diffTestMethod() {
            assertEquals(expected, simpleCalculator.diff(valOne, valTwo));
        }
    }

    @RunWith(Parameterized.class)
    public static class MultCalculatorTest {
        private final int valOne;
        private final int valTwo;
        private final int expected;

        public MultCalculatorTest(int valOne, int valTwo, int expected) {
            this.valOne = valOne;
            this.valTwo = valTwo;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static List<Integer[]> sumTest() {
            return Arrays.asList(new Integer[][]{
                    {7, 5, 35},
                    {7, 0, 0},
                    {20, 20, 400},
                    {55, 5, 275},
            });
        }

        @Test
        public void multTestMethod() {
            assertEquals(expected, simpleCalculator.mult(valOne, valTwo));
        }
    }

    @RunWith(Parameterized.class)
    public static class DivNormalValuesCalculatorTest {
        private final int valOne;
        private final int valTwo;
        private final int expected;

        public DivNormalValuesCalculatorTest(int valOne, int valTwo, int expected) {
            this.valOne = valOne;
            this.valTwo = valTwo;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static List<Integer[]> sumTest() {
            return Arrays.asList(new Integer[][]{
                    {7, 5, 1},
                    {27, 9, 3},
                    {20, 20, 1},
                    {55, 5, 11},
            });
        }

        @Test
        public void divByNormalValuesTestMethod() {
            assertEquals(expected, simpleCalculator.div(valOne, valTwo));
        }
    }

    @RunWith(Parameterized.class)
    public static class DivByZeroCalculatorTest {
        private final int valOne;
        private final int valTwo;
        private final int expected;

        public DivByZeroCalculatorTest(int valOne, int valTwo, int expected) {
            this.valOne = valOne;
            this.valTwo = valTwo;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static List<Integer[]> sumTest() {
            return Arrays.asList(new Integer[][]{
                    {7, 0, 7},
                    {27, 0, 27},
                    {20, 0, 20},
            });
        }

        @Test(expected = ArithmeticException.class)
        public void divTestByZero() {
            assertEquals(expected, simpleCalculator.div(valOne, valTwo));
        }
    }
}