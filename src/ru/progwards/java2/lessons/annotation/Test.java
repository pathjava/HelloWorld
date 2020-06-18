// Oleg Kiselev
// 18.06.2020, 17:46

package ru.progwards.java2.lessons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    int priority() default 0;

    Class<? extends Exception> expected() default Exception.class;
}
