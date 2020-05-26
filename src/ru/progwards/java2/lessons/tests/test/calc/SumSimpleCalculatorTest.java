// Oleg Kiselev
// 26.05.2020, 15:28

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
public class SumSimpleCalculatorTest {

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
}