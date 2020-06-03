// Oleg Kiselev
// 03.06.2020, 12:24

package ru.progwards.java2.lessons.reflection;


import java.lang.reflect.Modifier;

public class ClassInspector {

    public static StringBuilder stringBuilder = new StringBuilder();

    public static void inspect(String clazz) throws ClassNotFoundException {

        Class<?> inspectedClass = Class.forName(clazz);

//        int result = Modifier.classModifiers();
//        String str = Modifier.toString(result);
//        System.out.println(str);

        printClass(inspectedClass);

//        String classOrInterface = Modifier.isInterface(mod) ? "interface " : "class ";
//        System.out.println(classOrInterface);

//        System.out.println(inspectedClass.getSimpleName());
//
//        int modifierClass = inspectedClass.getModifiers();
//        String modStr = Modifier.toString(modifierClass);
    }

    private static void printClass(Class<?> inspectedClass) {
        int mod = inspectedClass.getModifiers();
        checkModifiers(mod);
        checkClassOrInterface(mod);
        System.out.print(inspectedClass.getSimpleName() + " {");
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
