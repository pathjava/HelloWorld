// Oleg Kiselev
// 19.06.2020, 15:29

package ru.progwards.sever.testprogwards2.test_09;

import java.lang.reflect.Method;

public class Test9 {

    public static void printAnnotation() {
        Method[] methods = Greetings.class.getDeclaredMethods();
        for (Method m : methods) {
            String name = m.getName();
            if (m.isAnnotationPresent(AnnotationTest.class)) {
                String str = m.getAnnotation(AnnotationTest.class).text();
                System.out.println(name + " " + str);
            }
        }
    }


    public static void main(String[] args) {
        printAnnotation();
    }
}

class Greetings {
    @AnnotationTest(text = "111")
    void hello() {
    }

    @AnnotationTest(text = "222")
    void goodday() {
    }

    @AnnotationTest(text = "333")
    void goodnight() {
    }

    @AnnotationTest(text = "444")
    void hi() {
    }

    ;
}