// Oleg Kiselev
// 17.06.2020, 17:25

package ru.progwards.java2.lessons.annotation.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.progwards.java2.lessons.annotation.Calc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CalcTest {

    public Calc calc;

    @Before
    public void setUpTest() {
        calc = new Calc();
    }

    @Test
    public void calculateTest() {
    }

    @Test
    public void readStringTest() {
    }

    @Test
    public void searchBracketsTest() {
    }

    @Test
    public void operationsInBracketsTest() {
    }

    @Test
    public void multiplicationAndDivisionTest() {
    }

    @Test
    public void additionalAndSubtractionTest() {
    }

    @Test
    public void multiplicationInBracketsTest() {
    }

    @Test
    public void divisionInBracketsTest() {
    }

    @Test
    public void additionalInBracketsTest() {
    }

    @Test
    public void subtractionInBracketsTest() {
    }

    @Test
    public void searchArithmeticSignsTest() {
    }

    @Test
    public void multiplicationTest() {
    }

    @Test
    public void divisionTest() {
    }

    @Test
    public void additionalTest() {
    }

    @Test
    public void subtractionTest() {
    }

    @Test
    public void deleteTest() {
    }

    @Test
    public void checkBracketsTestTrue() {
        calc.list = new ArrayList<>(List.of("(", "1", "/"));
        assertTrue(calc.checkBrackets());
    }

    @Test
    public void checkBracketsTestFalse() {
        calc.list = new ArrayList<>(List.of("*", "1", "/"));
        assertFalse(calc.checkBrackets());
    }

    @Test
    public void checkMulTest() {
        List<String> list = Collections.singletonList("*");
        assertTrue(calc.checkMul(list));
    }

    @Test
    public void checkDivTest() {
        List<String> list = Collections.singletonList("/");
        assertTrue(calc.checkDiv(list));
    }

    @Test
    public void checkPlusTest() {
        List<String> list = Collections.singletonList("+");
        assertTrue(calc.checkPlus(list));
    }

    @Test
    public void checkMinusTest() {
        List<String> list = Collections.singletonList("-");
        assertTrue( calc.checkMinus(list));
    }

    @Test
    public void addTest() {
        assertEquals("8", calc.add("6", "2"));
    }

    @Test
    public void diffTest() {
        assertEquals("4", calc.diff("6", "2"));
    }

    @Test
    public void multTest() {
        assertEquals("12", calc.mult("6", "2"));
    }

    @Test
    public void divTest() {
        assertEquals("3", calc.div("6", "2"));
    }

    @Test(expected = ArithmeticException.class)
    public void divByZeroTest() {
        assertEquals("3", calc.div("6", "0"));
    }

    @Test
    public void mainTest() {
    }

    @After
    public void tearDownTest() {
        calc = null;
    }
}