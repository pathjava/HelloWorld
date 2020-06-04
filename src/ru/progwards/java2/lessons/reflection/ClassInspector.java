// Oleg Kiselev
// 03.06.2020, 12:24

package ru.progwards.java2.lessons.reflection;


import java.lang.reflect.*;

public class ClassInspector {

    public static void inspect(String clazz) throws ClassNotFoundException {
        Class<?> inspectedClass = Class.forName(clazz);

        showClass(inspectedClass);
        showFields(inspectedClass);
        showConstructors(inspectedClass);
        showMethods(inspectedClass);

    }

    private static void showMethods(Class<?> inspectedClass) {
        Method[] methods = inspectedClass.getDeclaredMethods();
        for (Method method : methods) {
            int mod = method.getModifiers();
            checkModifiers(mod);
            System.out.print(method.getName());
            checkParameters(method.getParameters());
        }
        System.out.println();
    }

    private static void showClass(Class<?> inspectedClass) {
        int mod = inspectedClass.getModifiers();
        checkModifiers(mod);
        checkClassOrInterface(mod);
        System.out.println(inspectedClass.getSimpleName() + " {");
    }

    private static void showFields(Class<?> inspectedClass) {
        Field[] fields = inspectedClass.getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            String modStr = Modifier.toString(mod);
            System.out.println(modStr + " " + field.getType().getSimpleName() + " " + field.getName() + ";"); //TODO value
        }
        System.out.println();
    }

    private static void showConstructors(Class<?> inspectedClass) {
        Constructor<?>[] constructors = inspectedClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            int mod = constructor.getModifiers();
            String modStr = Modifier.toString(mod);
            String nameCons = constructor.getDeclaringClass().getSimpleName();
            System.out.print(modStr + " " + nameCons);
            checkParameters(constructor.getParameters());
        }
        System.out.println();
    }

    private static void checkParameters(Parameter[] parameters) {
        StringBuilder stringParam = new StringBuilder();
        int count = parameters.length;
        for (Parameter parameter : parameters) {
            String type = parameter.getType().getSimpleName();
            String nameParam = parameter.getName();
            String comma = count > 1 ? ", " : "";
            stringParam.append(type).append(" ").append(nameParam).append(comma);
            count--;
        }
        System.out.println("(" + stringParam.toString() + ") {}");
    }

    private static void checkModifiers(int mod) {
        StringBuilder stringBuilder = new StringBuilder();
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
        System.out.print(stringBuilder.toString());
    }

    private static void checkClassOrInterface(int mod) {
        System.out.print(Modifier.isInterface(mod) ? "interface " : "class ");
    }


    public static void main(String[] args) {
        try {
//            inspect("ru.progwards.java2.lessons.reflection.PersonInterface");
//            inspect("ru.progwards.java2.lessons.reflection.PersonAbstract");
            inspect("ru.progwards.java2.lessons.reflection.Person");
//            inspect("ru.progwards.java2.lessons.gc.Heap");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
