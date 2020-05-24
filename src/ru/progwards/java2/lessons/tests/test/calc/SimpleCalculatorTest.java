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

    @BeforeClass
    public static void createInstance(){
        SimpleCalculator simpleCalculator = new SimpleCalculator();
    }

    @Test
    public void sumTestMethod() {
    }

    @Test
    public void diffTestMethod() {
    }

    @Test
    public void multTestMethod() {
    }

    @Test
    public void divTestMethod() {
    }
}