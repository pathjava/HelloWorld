// Oleg Kiselev
// 17.06.2020, 17:25

package ru.progwards.java2.lessons.annotation.tests;

import ru.progwards.java2.lessons.annotation.*;
import ru.progwards.java2.lessons.annotation.Calc;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.instanceOf;

import static org.junit.Assert.*;

public class CalcTest {

    public Calc calc;

    @Before
    public void setUp() {
        calc = new Calc();
    }

    @Test(priority = 1)
    public void checkTypeList() {
        Field field = null;
        try {
            field = Calc.class.getDeclaredField("list");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert field != null;
        ParameterizedType param = (ParameterizedType) field.getGenericType();
        Class<?> stringClass = (Class<?>) param.getActualTypeArguments()[0];
        System.out.println(stringClass);
        assertThat(stringClass, instanceOf(Object.class));
    }

    @Test(priority = 2, expected = NoSuchElementException.class)
    public void calculateTestException() {
        String str = "";
        calc.calculate(str);
    }

    @Test(priority = 2)
    public void calculateTest() {
        calc.list = new ArrayList<>();
        String str = "3*(6+2)/3-2";
        assertEquals(0, calc.list.size());
        calc.calculate(str);
        assertEquals(1, calc.list.size());
        assertEquals(String.valueOf(6), calc.list.get(0));
    }

    @Test(priority = 1)
    public void readStringTest() {
        calc.list = new ArrayList<>();
        String str = "3*(6+2)/3-2";
        assertEquals(0, calc.list.size());
        calc.readString(str);
        assertEquals(11, calc.list.size());
    }

    @Test(priority = 5)
    public void searchBracketsTest() {
        calc.list = new ArrayList<>(List.of("3", "*", "(", "6", "+", "2", ")", "/", "3", "-", "2"));
        calc.tempList = new ArrayList<>();
        assertEquals(11, calc.list.size());
        calc.searchBrackets();
        assertEquals(7, calc.list.size());
        assertEquals(String.valueOf(8), calc.list.get(2));
    }

    @Test(priority = 6)
    public void operationsInBracketsTest() {
        calc.tempList = new ArrayList<>(List.of("(", "6", "*", "2", ")"));
        String str = calc.operationsInBrackets();
        assertNotNull(str);
        assertEquals("12", str);
    }

    @Test
    public void multiplicationAndDivisionTest() {
    }

    @Test
    public void additionalAndSubtractionTest() {
    }

    @Test
    public void multiplicationInBracketsTest() {
        calc.tempList = new ArrayList<>(List.of("(", "6", "*", "2", ")"));
        assertEquals(5, calc.tempList.size());
        calc.multiplicationInBrackets();
        assertEquals(3, calc.tempList.size());
        assertEquals(String.valueOf(12), calc.tempList.get(1));
    }

    @Test
    public void divisionInBracketsTest() {
        calc.tempList = new ArrayList<>(List.of("(", "6", "/", "2", ")"));
        assertEquals(5, calc.tempList.size());
        calc.divisionInBrackets();
        assertEquals(3, calc.tempList.size());
        assertEquals(String.valueOf(3), calc.tempList.get(1));
    }

    @Test
    public void additionalInBracketsTest() {
        calc.tempList = new ArrayList<>(List.of("(", "6", "+", "2", ")"));
        assertEquals(5, calc.tempList.size());
        calc.additionalInBrackets();
        assertEquals(3, calc.tempList.size());
        assertEquals(String.valueOf(8), calc.tempList.get(1));
    }

    @Test
    public void subtractionInBracketsTest() {
        calc.tempList = new ArrayList<>(List.of("(", "6", "-", "2", ")"));
        assertEquals(5, calc.tempList.size());
        calc.subtractionInBrackets();
        assertEquals(3, calc.tempList.size());
        assertEquals(String.valueOf(4), calc.tempList.get(1));
    }

    @Test
    public void searchArithmeticSignsTest() {
        calc.list = new ArrayList<>(List.of("5", "*", "16", "/", "4", "+", "5", "-", "3"));
        assertEquals(9, calc.list.size());
        calc.searchArithmeticSigns();
        assertEquals(1, calc.list.size());
        assertEquals(String.valueOf(22), calc.list.get(0));
    }

    @Test
    public void multiplicationTest() {
        calc.list = new ArrayList<>(List.of("(", "28", "/", "4", "*", "2", ")"));
        calc.tempList = new ArrayList<>();
        assertEquals(7, calc.list.size());
        calc.multiplication();
        assertEquals(0, calc.tempList.size());
        assertEquals(5, calc.list.size());
        assertEquals(String.valueOf(8), calc.list.get(3));
    }

    @Test
    public void divisionTest() {
        calc.list = new ArrayList<>(List.of("(", "18", "+", "6", "/", "2", ")"));
        calc.tempList = new ArrayList<>();
        assertEquals(7, calc.list.size());
        calc.division();
        assertEquals(0, calc.tempList.size());
        assertEquals(5, calc.list.size());
        assertEquals(String.valueOf(3), calc.list.get(3));
    }

    @Test
    public void additionalTest() {
        calc.list = new ArrayList<>(List.of("(", "8", "/", "4", "+", "2", ")"));
        calc.tempList = new ArrayList<>();
        assertEquals(7, calc.list.size());
        calc.additional();
        assertEquals(0, calc.tempList.size());
        assertEquals(5, calc.list.size());
        assertEquals(String.valueOf(6), calc.list.get(3));
    }

    @Test
    public void subtractionTest() {
        calc.list = new ArrayList<>(List.of("(", "38", "/", "6", "-", "2", ")"));
        calc.tempList = new ArrayList<>();
        assertEquals(7, calc.list.size());
        calc.subtraction();
        assertEquals(0, calc.tempList.size());
        assertEquals(5, calc.list.size());
        assertEquals(String.valueOf(4), calc.list.get(3));
    }

    @Test
    public void deleteTest() {
        List<String> list = new ArrayList<>(List.of("str1", "str2", "str3"));
        assertEquals(3, list.size());
        calc.delete(list, 0, 3);
        assertEquals(0, list.size());
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
    public void checkMulTestTrue() {
        List<String> list = new ArrayList<>(List.of("*", "1", "/"));
        assertTrue(calc.checkMul(list));
    }

    @Test
    public void checkMulTestFalse() {
        List<String> list = new ArrayList<>(List.of("+", "1", "/"));
        assertFalse(calc.checkMul(list));
    }

    @Test
    public void checkDivTestTrue() {
        List<String> list = new ArrayList<>(List.of("*", "1", "/"));
        assertTrue(calc.checkDiv(list));
    }

    @Test
    public void checkDivTestFalse() {
        List<String> list = new ArrayList<>(List.of("*", "1", "7"));
        assertFalse(calc.checkDiv(list));
    }

    @Test
    public void checkPlusTestTrue() {
        List<String> list = new ArrayList<>(List.of("*", "+", "/"));
        assertTrue(calc.checkPlus(list));
    }

    @Test
    public void checkPlusTestFalse() {
        List<String> list = new ArrayList<>(List.of("*", "1", "/"));
        assertFalse(calc.checkPlus(list));
    }

    @Test
    public void checkMinusTestTrue() {
        List<String> list = new ArrayList<>(List.of("*", "1", "-"));
        assertTrue(calc.checkMinus(list));
    }

    @Test
    public void checkMinusTestFalse() {
        List<String> list = new ArrayList<>(List.of("*", "1", "/"));
        assertFalse(calc.checkMinus(list));
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
    public void tearDown() {
        calc = null;
    }
}