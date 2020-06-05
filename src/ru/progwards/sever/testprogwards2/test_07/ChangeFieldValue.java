// Oleg Kiselev
// 05.06.2020, 12:36

package ru.progwards.sever.testprogwards2.test_07;

import java.lang.reflect.Field;

public class ChangeFieldValue {

    public static void setName(Person person, String name) {
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

    public static void callSetName(Person person, String name){

    }


    public static void main(String[] args) {

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

