// Oleg Kiselev
// 03.06.2020, 12:24

package ru.progwards.java2.lessons.reflection;


import java.lang.reflect.*;

public class ClassInspector {

    public static StringBuilder stringBuilder = new StringBuilder();

    public static void inspect(String clazz) throws ClassNotFoundException {

        Class<?> inspectedClass = Class.forName(clazz);

        printClass(inspectedClass);
        printFields(inspectedClass);

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
            System.out.println(modStr + " " + field.getType().getSimpleName() + " " + field.getName());
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


    public static void main(String[] args) throws ClassNotFoundException {
        inspect("ru.progwards.java2.lessons.reflection.Person");
    }

}
