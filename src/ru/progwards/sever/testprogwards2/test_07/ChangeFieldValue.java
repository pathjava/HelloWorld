// Oleg Kiselev
// 05.06.2020, 12:36

package ru.progwards.sever.testprogwards2.test_07;

import java.lang.reflect.Constructor;
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

    Person callConstructor(String name){
        Class<?> clazz = Person.class;
        Constructor<?> constructor = null;
        try {
            constructor = clazz.getDeclaredConstructor(String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assert constructor != null;
        constructor.setAccessible(true);
        try {
            return (Person) constructor.newInstance(name);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        Person person = new Person("Mike");
        ChangeFieldValue test = new ChangeFieldValue();

        System.out.println(person.getName());
        test.setName(person, "John");
        System.out.println(person.getName());
        test.callSetName(person, "Bill");
        System.out.println(person.getName());
        test.callConstructor("George");
        System.out.println(person.getName());
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

    public String getName() {
        return name;
    }
}

