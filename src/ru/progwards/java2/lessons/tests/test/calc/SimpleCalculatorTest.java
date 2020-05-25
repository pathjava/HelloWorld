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

    /* Summation */
    @RunWith(Parameterized.class)
    public static class SumNormalValuesCalculatorTest {
        private final int valOne;
        private final int valTwo;
        private final int expected;

        public SumNormalValuesCalculatorTest(int valOne, int valTwo, int expected) {
            this.valOne = valOne;
            this.valTwo = valTwo;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static List<Integer[]> sumTest() {
            return Arrays.asList(new Integer[][]{
                    {0, 0, 0},
                    {0, 5, 5},
                    {5, 0, 5},
                    {7, 5, 12},
                    {-7, 0, -7},
                    {20, -20, 0},
                    {-12, -5, -17},
            });
        }

        @Test
        public void sumWithNormalValuesTestMethod() {
            assertEquals(expected, simpleCalculator.sum(valOne, valTwo));
        }
    }

    @RunWith(Parameterized.class)
    public static class SumGetArithmeticExceptionCalculatorTest {
        private final int valOne;
        private final int valTwo;
        private final int expected;

        public SumGetArithmeticExceptionCalculatorTest(int valOne, int valTwo, int expected) {
            this.valOne = valOne;
            this.valTwo = valTwo;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static List<Integer[]> sumTest() {
            return Arrays.asList(new Integer[][]{
                    {7, Integer.MAX_VALUE, 12},
                    {Integer.MAX_VALUE, 7, 12},
                    {Integer.MAX_VALUE, Integer.MAX_VALUE, 12},
            });
        }

        @Test(expected = ArithmeticException.class)
        public void sumMethodShouldGetExceptionTest() {
            assertEquals(expected, simpleCalculator.sum(valOne, valTwo));
        }
    }

    /* Subtraction */
    @RunWith(Parameterized.class)
    public static class DiffNormalValuesCalculatorTest {
        private final int valOne;
        private final int valTwo;
        private final int expected;

        public DiffNormalValuesCalculatorTest(int valOne, int valTwo, int expected) {
            this.valOne = valOne;
            this.valTwo = valTwo;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static List<Integer[]> diffTest() {
            return Arrays.asList(new Integer[][]{
                    {0, 0, 0},
                    {7, 5, 2},
                    {7, 0, 7},
                    {0, 7, -7},
                    {20, 20, 0},
                    {55, 5, 50},
                    {5, 55, -50},
                    {-5, 55, -60},
                    {-55, 5, -60},
                    {-55, -5, -50},
                    {Integer.MIN_VALUE, Integer.MIN_VALUE, 0},
                    {Integer.MAX_VALUE, Integer.MAX_VALUE, 0},
            });
        }

        @Test
        public void diffWithNormalValuesTestMethod() {
            assertEquals(expected, simpleCalculator.diff(valOne, valTwo));
        }
    }

    @RunWith(Parameterized.class)
    public static class DiffGetArithmeticExceptionCalculatorTest {
        private final int valOne;
        private final int valTwo;
        private final int expected;

        public DiffGetArithmeticExceptionCalculatorTest(int valOne, int valTwo, int expected) {
            this.valOne = valOne;
            this.valTwo = valTwo;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static List<Integer[]> diffTest() {
            return Arrays.asList(new Integer[][]{
                    {Integer.MIN_VALUE, 5, 2},
                    {5, Integer.MIN_VALUE, 2},
                    {Integer.MIN_VALUE, Integer.MAX_VALUE, 0},
                    {Integer.MAX_VALUE, Integer.MIN_VALUE, 0},
                    {Integer.MIN_VALUE, Integer.MAX_VALUE, 0},
            });
        }

        @Test(expected = ArithmeticException.class)
        public void diffMethodShouldGetExceptionTest() {
            assertEquals(expected, simpleCalculator.diff(valOne, valTwo));
        }
    }

    /* Multiplication */
    @RunWith(Parameterized.class)
    public static class MultNormalValuesCalculatorTest {
        private final int valOne;
        private final int valTwo;
        private final int expected;

        public MultNormalValuesCalculatorTest(int valOne, int valTwo, int expected) {
            this.valOne = valOne;
            this.valTwo = valTwo;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static List<Integer[]> multTest() {
            return Arrays.asList(new Integer[][]{
                    {0, 0, 0},
                    {7, 0, 0},
                    {0, 7, 0},
                    {7, 5, 35},
                    {55, 5, 275},
                    {20, 20, 400},
                    {55, -5, -275},
                    {-55, 5, -275},
                    {-55, -5, 275},
            });
        }

        @Test
        public void multWithNormalValuesTestMethod() {
            assertEquals(expected, simpleCalculator.mult(valOne, valTwo));
        }
    }

    @RunWith(Parameterized.class)
    public static class MultGetArithmeticExceptionCalculatorTest {
        private final int valOne;
        private final int valTwo;
        private final int expected;

        public MultGetArithmeticExceptionCalculatorTest(int valOne, int valTwo, int expected) {
            this.valOne = valOne;
            this.valTwo = valTwo;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static List<Integer[]> multTest() {
            return Arrays.asList(new Integer[][]{
                    {7, Integer.MAX_VALUE, 35},
                    {Integer.MAX_VALUE, 7, 35},
                    {Integer.MAX_VALUE, Integer.MAX_VALUE, 35},
                    {Integer.MAX_VALUE, Integer.MIN_VALUE, 35},
                    {Integer.MIN_VALUE, Integer.MAX_VALUE, 35},
            });
        }

        @Test(expected = ArithmeticException.class)
        public void multMethodShouldGetExceptionTest() {
            assertEquals(expected, simpleCalculator.mult(valOne, valTwo));
        }
    }

    /* Division */
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
        public static List<Integer[]> divTest() {
            return Arrays.asList(new Integer[][]{
                    {7, 5, 1},
                    {0, 3, 0},
                    {27, 9, 3},
                    {20, 20, 1},
                    {55, 5, 11},
                    {55, -5, -11},
                    {-55, 5, -11},
                    {-55, -5, 11},
            });
        }

        @Test
        public void divWithNormalValuesTestMethod() {
            assertEquals(expected, simpleCalculator.div(valOne, valTwo));
        }
    }

    @RunWith(Parameterized.class)
    public static class DivByZeroArithmeticExceptionCalculatorTest {
        private final int valOne;
        private final int valTwo;
        private final int expected;

        public DivByZeroArithmeticExceptionCalculatorTest(int valOne, int valTwo, int expected) {
            this.valOne = valOne;
            this.valTwo = valTwo;
            this.expected = expected;
        }

        @Parameterized.Parameters
        public static List<Integer[]> divTest() {
            return Arrays.asList(new Integer[][]{
                    {7, 0, 7},
            });
        }

        @Test(expected = ArithmeticException.class)
        public void divMethodShouldGetExceptionByDivisionZeroTest() {
            assertEquals(expected, simpleCalculator.div(valOne, valTwo));
        }
    }
}