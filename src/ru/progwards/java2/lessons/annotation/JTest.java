// Oleg Kiselev
// 17.06.2020, 15:21

package ru.progwards.java2.lessons.annotation;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class JTest {

    private void run(String name) throws Exception {
        Class<?> testingClass = Class.forName(name);
        Method[] methods = testingClass.getDeclaredMethods();
        if (methods.length == 0)
            return;
        Method beforeMethod = null;
        Method afterMethod = null;
        Map<Integer, Method> testMethods = new TreeMap<>();
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
                //...
                testMethods.put(priority, m);
            } else if (m.isAnnotationPresent(After.class)) {
                if (countAfter > 1)
                    throw new RuntimeException();
                countAfter++;
                afterMethod = m;
            }
        }
        Object object = testingClass.getConstructor().newInstance();
        assert beforeMethod != null;
        beforeMethod.invoke(object);
        for (Method m : testMethods.values()) {
            m.invoke(object);
        }
        assert afterMethod != null;
        afterMethod.invoke(object);
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
