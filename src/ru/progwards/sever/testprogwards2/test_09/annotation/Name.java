// Oleg Kiselev
// 10.06.2020, 21:25

package ru.progwards.sever.testprogwards2.test_09.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
    String value() default "ggg";
    String name();
}
