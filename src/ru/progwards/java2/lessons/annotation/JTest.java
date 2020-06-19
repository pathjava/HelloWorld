// Oleg Kiselev
// 17.06.2020, 15:21

package ru.progwards.java2.lessons.annotation;

import java.lang.reflect.*;
import java.util.*;

public class JTest {

    private final Map<Integer, Method> testMethods = new TreeMap<>();

    public void run(String name) throws ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (name.equals("")) /* проверяем, не является ли строка с путем к классу пустой */
            throw new IllegalArgumentException("Путь к файлу не указан!");
        Class<?> testingClass = Class.forName(name); /* получаем класс */
        dataHandler(testingClass); /* передаем полученный класс на обработку */
    }

    private void dataHandler(Class<?> testingClass) throws NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        Method[] methods = testingClass.getDeclaredMethods(); /* получаем массив методов */
        if (methods.length == 0) /* если массив имеет размер 0, прерываем метод */
            return;
        Method beforeMethod = null; /* заводим переменные для методов Before и After */
        Method afterMethod = null;
        int countBefore = 0; /* счетчик Before и After, так как они не могут встречаться в классе более 1 раза */
        int countAfter = 0;
        for (Method m : methods) {
            if (m.isAnnotationPresent(Before.class)) { /* если метод содержит аннотацию Before */
                if (countBefore > 0) /* проверяем счетчик */
                    throw new RuntimeException();
                countBefore++;
                beforeMethod = m; /* присваиваем метод с аннотацией Before в переменную */
            } else if (m.isAnnotationPresent(Test.class)) { /* если метод содержит аннотацию Test */
                int priority = m.getAnnotation(Test.class).priority(); /* получаем значение приоритета */
                if (priority != 0) /* если приоритет не 0, добавляем метод в TreeMap */
                    testMethods.put(priority, m);
            } else if (m.isAnnotationPresent(After.class)) { /* если метод содержит аннотацию After */
                if (countAfter > 0) /* проверяем счетчик */
                    throw new RuntimeException();
                countAfter++;
                afterMethod = m; /* присваиваем метод с аннотацией After в переменную */
            } else {
                System.out.println("Нет методов с аннотациями: Before, Test, After");
                return;
            }
        }
        assert beforeMethod != null;
        testRunner(testingClass, beforeMethod, afterMethod);
    }

    private void testRunner(Class<?> testingClass, Method before, Method after) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, InstantiationException {
        Object object = testingClass.getConstructor().newInstance();
        for (Method method : testMethods.values()) {
            before.invoke(object); /* создаем объект */
            method.invoke(object); /* запускаем тестовый метод */
            after.invoke(object); /* удаляем объект */
        }
    }


    public static void main(String[] args) {
        JTest test = new JTest();
        try {
            test.run("ru.progwards.java2.lessons.annotation.tests.CalcTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
