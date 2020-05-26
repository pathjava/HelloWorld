// Oleg Kiselev
// 26.05.2020, 15:31

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
public class DiffSimpleCalculatorTest {

    public static SimpleCalculator simpleCalculator;

    @BeforeClass
    public static void createInstance() {
        simpleCalculator = new SimpleCalculator();
    }

    @AfterClass
    public static void removeInstance(){
        simpleCalculator = null;
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
}