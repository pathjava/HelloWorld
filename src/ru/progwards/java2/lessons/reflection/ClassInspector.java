// Oleg Kiselev
// 03.06.2020, 12:24

package ru.progwards.java2.lessons.reflection;


import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class ClassInspector {

    public static void inspect(String clazz) throws ClassNotFoundException {

        Class inspectedClass = Class.forName(clazz);

        int mod = inspectedClass.getModifiers();

        System.out.println(Modifier.isInterface(mod));

        System.out.println(inspectedClass.getSimpleName());

        int modifierClass = inspectedClass.getModifiers();
        String modStr = Modifier.toString(modifierClass);
    }


    public static void main(String[] args) throws ClassNotFoundException {
        inspect("ru.progwards.java2.lessons.reflection.Person");
    }

}
