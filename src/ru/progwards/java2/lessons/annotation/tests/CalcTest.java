// Oleg Kiselev
// 17.06.2020, 17:25

package ru.progwards.java2.lessons.annotation.tests;

import ru.progwards.java2.lessons.annotation.*;

import java.lang.reflect.*;
import java.util.*;

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
        assertThat(stringClass, instanceOf(Object.class));
    }

    @Test(priority = 31)
    public void checkTypeListName() {
        Field field = null;
        try {
            field = Calc.class.getDeclaredField("list");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert field != null;
        ParameterizedType param = (ParameterizedType) field.getGenericType();
        Class<?> stringClass = (Class<?>) param.getActualTypeArguments()[0];
        String str = stringClass.getSimpleName();
        assertEquals("String", str);
    }

    @Test(priority = 2, expected = NoSuchElementException.class)
    public void calculateTestException() {
        try {
            String str = "";
            calc.calculate(str);
            fail("Expected exception NoSuchElementException");
        } catch (NoSuchElementException ignored) {
        }
    }

    @Test(priority = 3)
    public void calculateTest() {
        calc.list = new ArrayList<>();
        String str = "3*(6+2)/3-2";
        assertEquals(0, calc.list.size());
        calc.calculate(str);
        assertEquals(1, calc.list.size());
        assertEquals(String.valueOf(6), calc.list.get(0));
    }

    @Test(priority = 4)
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

    @Test(priority = 7)
    public void multiplicationInBracketsTest() {
        calc.tempList = new ArrayList<>(List.of("(", "6", "*", "2", ")"));
        assertEquals(5, calc.tempList.size());
        calc.multiplicationInBrackets();
        assertEquals(3, calc.tempList.size());
        assertEquals(String.valueOf(12), calc.tempList.get(1));
    }

    @Test(priority = 8)
    public void divisionInBracketsTest() {
        calc.tempList = new ArrayList<>(List.of("(", "6", "/", "2", ")"));
        assertEquals(5, calc.tempList.size());
        calc.divisionInBrackets();
        assertEquals(3, calc.tempList.size());
        assertEquals(String.valueOf(3), calc.tempList.get(1));
    }

    @Test(priority = 9)
    public void additionalInBracketsTest() {
        calc.tempList = new ArrayList<>(List.of("(", "6", "+", "2", ")"));
        assertEquals(5, calc.tempList.size());
        calc.additionalInBrackets();
        assertEquals(3, calc.tempList.size());
        assertEquals(String.valueOf(8), calc.tempList.get(1));
    }

    @Test(priority = 10)
    public void subtractionInBracketsTest() {
        calc.tempList = new ArrayList<>(List.of("(", "6", "-", "2", ")"));
        assertEquals(5, calc.tempList.size());
        calc.subtractionInBrackets();
        assertEquals(3, calc.tempList.size());
        assertEquals(String.valueOf(4), calc.tempList.get(1));
    }

    @Test(priority = 11)
    public void searchArithmeticSignsTest() {
        calc.list = new ArrayList<>(List.of("5", "*", "16", "/", "4", "+", "5", "-", "3"));
        assertEquals(9, calc.list.size());
        calc.searchArithmeticSigns();
        assertEquals(1, calc.list.size());
        assertEquals(String.valueOf(22), calc.list.get(0));
    }

    @Test(priority = 12)
    public void multiplicationTest() {
        calc.list = new ArrayList<>(List.of("(", "28", "/", "4", "*", "2", ")"));
        calc.tempList = new ArrayList<>();
        assertEquals(7, calc.list.size());
        calc.multiplication();
        assertEquals(0, calc.tempList.size());
        assertEquals(5, calc.list.size());
        assertEquals(String.valueOf(8), calc.list.get(3));
    }

    @Test(priority = 13)
    public void divisionTest() {
        calc.list = new ArrayList<>(List.of("(", "18", "+", "6", "/", "2", ")"));
        calc.tempList = new ArrayList<>();
        assertEquals(7, calc.list.size());
        calc.division();
        assertEquals(0, calc.tempList.size());
        assertEquals(5, calc.list.size());
        assertEquals(String.valueOf(3), calc.list.get(3));
    }

    @Test(priority = 14)
    public void additionalTest() {
        calc.list = new ArrayList<>(List.of("(", "8", "/", "4", "+", "2", ")"));
        calc.tempList = new ArrayList<>();
        assertEquals(7, calc.list.size());
        calc.additional();
        assertEquals(0, calc.tempList.size());
        assertEquals(5, calc.list.size());
        assertEquals(String.valueOf(6), calc.list.get(3));
    }

    @Test(priority = 15)
    public void subtractionTest() {
        calc.list = new ArrayList<>(List.of("(", "38", "/", "6", "-", "2", ")"));
        calc.tempList = new ArrayList<>();
        assertEquals(7, calc.list.size());
        calc.subtraction();
        assertEquals(0, calc.tempList.size());
        assertEquals(5, calc.list.size());
        assertEquals(String.valueOf(4), calc.list.get(3));
    }

    @Test(priority = 16)
    public void deleteTest() {
        List<String> list = new ArrayList<>(List.of("str1", "str2", "str3"));
        assertEquals(3, list.size());
        calc.delete(list, 0, 3);
        assertEquals(0, list.size());
    }

    @Test(priority = 17)
    public void checkBracketsTestTrue() {
        calc.list = new ArrayList<>(List.of("(", "1", "/"));
        assertTrue(calc.checkBrackets());
    }

    @Test(priority = 18)
    public void checkBracketsTestFalse() {
        calc.list = new ArrayList<>(List.of("*", "1", "/"));
        assertFalse(calc.checkBrackets());
    }

    @Test(priority = 19)
    public void checkMulTestTrue() {
        List<String> list = new ArrayList<>(List.of("*", "1", "/"));
        assertTrue(calc.checkMul(list));
    }

    @Test(priority = 20)
    public void checkMulTestFalse() {
        List<String> list = new ArrayList<>(List.of("+", "1", "/"));
        assertFalse(calc.checkMul(list));
    }

    @Test(priority = 21)
    public void checkDivTestTrue() {
        List<String> list = new ArrayList<>(List.of("*", "1", "/"));
        assertTrue(calc.checkDiv(list));
    }

    @Test(priority = 22)
    public void checkDivTestFalse() {
        List<String> list = new ArrayList<>(List.of("*", "1", "7"));
        assertFalse(calc.checkDiv(list));
    }

    @Test(priority = 23)
    public void checkPlusTestTrue() {
        List<String> list = new ArrayList<>(List.of("*", "+", "/"));
        assertTrue(calc.checkPlus(list));
    }

    @Test(priority = 24)
    public void checkPlusTestFalse() {
        List<String> list = new ArrayList<>(List.of("*", "1", "/"));
        assertFalse(calc.checkPlus(list));
    }

    @Test(priority = 25)
    public void checkMinusTestTrue() {
        List<String> list = new ArrayList<>(List.of("*", "1", "-"));
        assertTrue(calc.checkMinus(list));
    }

    @Test(priority = 26)
    public void checkMinusTestFalse() {
        List<String> list = new ArrayList<>(List.of("*", "1", "/"));
        assertFalse(calc.checkMinus(list));
    }

    @Test(priority = 27)
    public void addTest() {
        assertEquals("8", calc.add("6", "2"));
    }

    @Test(priority = 28)
    public void diffTest() {
        assertEquals("4", calc.diff("6", "2"));
    }

    @Test(priority = 29)
    public void multTest() {
        assertEquals("12", calc.mult("6", "2"));
    }

    @Test(priority = 30)
    public void divTest() {
        assertEquals("3", calc.div("6", "2"));
    }

    @Test(priority = 31, expected = ArithmeticException.class)
    public void divByZeroTest() {
        try {
            assertEquals("3", calc.div("6", "0"));
            fail("Expected exception ArithmeticException");
        } catch (ArithmeticException ignored) {
        }
    }

    @Test
    public void mainTest() {
    }

    @After
    public void tearDown() {
        calc = null;
    }
}