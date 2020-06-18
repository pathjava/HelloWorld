// Oleg Kiselev
// 17.06.2020, 15:21

package ru.progwards.java2.lessons.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class JTest {
    Map<Integer, Method> testMethods = new TreeMap<>();

    private void run(String name) {
        if (name.equals(""))
            throw new IllegalArgumentException();
        Class<?> testingClass = null;
        try {
            testingClass = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert testingClass != null;
        dataHandler(testingClass);
    }

    private void dataHandler(Class<?> testingClass) {
        Method[] methods = testingClass.getDeclaredMethods();
        if (methods.length == 0)
            return;
        Method beforeMethod = null;
        Method afterMethod = null;

        int countBefore = 0;
        int countAfter = 0;
        for (Method m : methods) {
            if (m.isAnnotationPresent(Before.class)) {
                if (countBefore > 1)
                    throw new RuntimeException();
                countBefore++;
                beforeMethod = m;
            } else if (m.isAnnotationPresent(Test.class)) {
                int priority = m.getAnnotation(Test.class).priority();
                if (priority != 0)
                    testMethods.put(priority, m);
            } else if (m.isAnnotationPresent(After.class)) {
                if (countAfter > 1)
                    throw new RuntimeException();
                countAfter++;
                afterMethod = m;
            }
        }
        assert beforeMethod != null;
        tester(testingClass, beforeMethod, afterMethod);
    }

    private void tester(Class<?> testingClass, Method before, Method after) {
        Object object = null;
        try {
            object = testingClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        assert before != null;
        try {
            before.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        for (Method m : testMethods.values()) {
            try {
                m.invoke(object);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        assert after != null;
        try {
            after.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
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
