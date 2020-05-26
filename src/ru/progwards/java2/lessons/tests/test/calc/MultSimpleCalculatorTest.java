// Oleg Kiselev
// 26.05.2020, 15:33

package ru.progwards.java2.lessons.tests.test.calc;

import org.junit.AfterClass;
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
public class MultSimpleCalculatorTest {

    public static SimpleCalculator simpleCalculator;

    @BeforeClass
    public static void createInstance() {
        simpleCalculator = new SimpleCalculator();
    }

    @AfterClass
    public static void removeInstance(){
        simpleCalculator = null;
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
}