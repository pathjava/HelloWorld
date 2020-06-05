// Oleg Kiselev
// 05.06.2020, 12:36

package ru.progwards.sever.testprogwards2.test_07;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ChangeFieldValue {

    void setName(Person person, String name) {
        Class<?> clazz = person.getClass();
        Field field = null;
        try {
            field = clazz.getDeclaredField("name");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert field != null;
        field.setAccessible(true);
        try {
            field.set(person, name);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    void callSetName(Person person, String name)  {
        Class<?> clazz = person.getClass();
        Method method = null;
        try {
            method = clazz.getDeclaredMethod("setName", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assert method != null;
        method.setAccessible(true);
        try {
            method.invoke(person, name);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Person person = new Person("Mike");
        new ChangeFieldValue().callSetName(person, "Bill");

    }
}

class Person {
    private String name;

    Person(String name) {
        this.name = name;
    }

    private void setName(String name) {
        this.name = name;
    }
}

