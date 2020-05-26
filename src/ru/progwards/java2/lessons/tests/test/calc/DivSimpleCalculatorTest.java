// Oleg Kiselev
// 26.05.2020, 15:34

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
public class DivSimpleCalculatorTest {

    public static SimpleCalculator simpleCalculator;

    @BeforeClass
    public static void createInstance() {
        simpleCalculator = new SimpleCalculator();
    }

    @AfterClass
    public static void removeInstance(){
        simpleCalculator = null;
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