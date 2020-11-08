// Oleg Kiselev
// 27.05.2020, 17:57

package ru.progwards.java2.lessons.tests.test.calc;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.progwards.java2.lessons.tests.calc.SimpleCalculator;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SumSingleSimpleCalculatorTest {

    public static SimpleCalculator simpleCalculator;

    @BeforeClass
    public static void createInstance() {
        simpleCalculator = new SimpleCalculator();
    }

    @AfterClass
    public static void removeInstance() {
        simpleCalculator = null;
    }

    private final int valOne;
    private final int valTwo;
    private final int expected;

    public SumSingleSimpleCalculatorTest(int valOne, int valTwo, int expected) {
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