// Oleg Kiselev
// 19.06.2020, 15:31

package ru.progwards.sever.testprogwards2.test_09;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface AnnotationTest {
    String text() default "Всегда говори привет";
}