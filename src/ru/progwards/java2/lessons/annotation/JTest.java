// Oleg Kiselev
// 17.06.2020, 15:21

package ru.progwards.java2.lessons.annotation;

import java.lang.reflect.Method;
import java.util.TreeMap;

public class JTest {

    private void run(String name) throws Exception {
        Class<?> testingClass = Class.forName(name);
        Method[] methods = testingClass.getDeclaredMethods();
            //...
        TreeMap<Integer, Method> testMethods = new TreeMap<>();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Before.class)) {
                //...
            } else if (m.isAnnotationPresent(Test.class)) {
                int priority = m.getAnnotation(Test.class).priority();
                //...
                testMethods.put(priority, m);
            } else if (m.isAnnotationPresent(After.class)) {
                //...
            }
        }
        Object object = testingClass.getConstructor().newInstance();
//        beforeMethod.invoke(object);
        for (Method m : testMethods.values()) {
            m.invoke(object);
        }
//        afterMethod.invoke(object);
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
