// Oleg Kiselev
// 10.06.2020, 21:37

package ru.progwards.sever.testprogwards2.test_09.annotation;

import java.lang.reflect.Method;

public class ResearchAnnotation {
    public static void main(String[] args) {
        Method[] methods = Employee.class.getDeclaredMethods();
        for (Method m: methods) {
            if(m.isAnnotationPresent(First.class)){
                First annotation = m.getAnnotation(First.class);
                System.out.println(annotation.name());
                System.out.println(annotation.value());
            }
        }

    }
}
