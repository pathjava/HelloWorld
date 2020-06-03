// Oleg Kiselev
// 03.06.2020, 12:24

package ru.progwards.java2.lessons.reflection;


import java.lang.reflect.*;
import java.util.Arrays;

public class ClassInspector {

    public static StringBuilder stringBuilder = new StringBuilder();

    public static void inspect(String clazz) throws ClassNotFoundException {

        Class<?> inspectedClass = Class.forName(clazz);

        printClass(inspectedClass);
        printFields(inspectedClass);
        System.out.println("");
        printConstructors(inspectedClass);

    }

    private static void printClass(Class<?> inspectedClass) {
        int mod = inspectedClass.getModifiers();
        checkModifiers(mod);
        checkClassOrInterface(mod);
        System.out.println(inspectedClass.getSimpleName() + " {");
    }

    private static void printFields(Class<?> inspectedClass) {
        Field[] fields = inspectedClass.getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            String modStr = Modifier.toString(mod);
            System.out.println(modStr + " " + field.getType().getSimpleName() + " " + field.getName() + ";"); //TODO value
        }
    }

    private static void printConstructors(Class<?> inspectedClass) {
        Constructor<?>[] constructors = inspectedClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            int mod = constructor.getModifiers();
            String modStr = Modifier.toString(mod);
            String name = constructor.getDeclaringClass().getSimpleName();

            Parameter[] params = constructor.getParameters();
            checkParameters(params);
            System.out.println(modStr + " " + name + " ()");
        }
    }

    private static void checkParameters(Parameter[] parameters) {
        StringBuilder stringParam = new StringBuilder();
        for (Parameter parameter : parameters) {

        }
    }

    private static void checkModifiers(int mod) {
        stringBuilder.append(Modifier.isPublic(mod) ? "public " : "")
                .append(Modifier.isPrivate(mod) ? "private " : "")
                .append(Modifier.isProtected(mod) ? "protected " : "")
                .append(Modifier.isStatic(mod) ? "static " : "")
                .append(Modifier.isAbstract(mod) ? "abstract " : "")
                .append(Modifier.isNative(mod) ? "native " : "")
                .append(Modifier.isTransient(mod) ? "transient " : "")
                .append(Modifier.isSynchronized(mod) ? "synchronized " : "")
                .append(Modifier.isVolatile(mod) ? "volatile " : "")
                .append(Modifier.isStrict(mod) ? "strictfp " : "")
                .append(Modifier.isFinal(mod) ? "final " : "");
    }

    private static void checkClassOrInterface(int mod) {
        stringBuilder.append(Modifier.isInterface(mod) ? "interface " : "class ");
        System.out.print(stringBuilder.toString());
    }


    public static void main(String[] args) {
        try {
            inspect("ru.progwards.java2.lessons.reflection.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
