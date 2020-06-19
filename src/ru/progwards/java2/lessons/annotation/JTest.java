// Oleg Kiselev
// 17.06.2020, 15:21

package ru.progwards.java2.lessons.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class JTest {
    private final Map<Integer, Method> testMethods = new TreeMap<>();

    public void run(String name) throws ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (name.equals(""))
            throw new IllegalArgumentException();
        Class<?> testingClass = Class.forName(name);
        dataHandler(testingClass);
    }

    private void dataHandler(Class<?> testingClass) throws NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        Method[] methods = testingClass.getDeclaredMethods();
        if (methods.length == 0)
            return;
        Method beforeMethod = null;
        Method afterMethod = null;
        int countBefore = 0;
        int countAfter = 0;
        for (Method m : methods) {
            if (m.isAnnotationPresent(Before.class)) {
                if (countBefore > 0)
                    throw new RuntimeException();
                countBefore++;
                beforeMethod = m;
            } else if (m.isAnnotationPresent(Test.class)) {
                int priority = m.getAnnotation(Test.class).priority();
                if (priority != 0)
                    testMethods.put(priority, m);
            } else if (m.isAnnotationPresent(After.class)) {
                if (countAfter > 0)
                    throw new RuntimeException();
                countAfter++;
                afterMethod = m;
            }
        }
        assert beforeMethod != null;
        testRunner(testingClass, beforeMethod, afterMethod);
    }

    private void testRunner(Class<?> testingClass, Method before, Method after) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, InstantiationException {
        Object object = testingClass.getConstructor().newInstance();
        for (Method m : testMethods.values()) {
            before.invoke(object);
            m.invoke(object);
            after.invoke(object);
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
