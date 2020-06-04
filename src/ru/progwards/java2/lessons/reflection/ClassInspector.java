// Oleg Kiselev
// 03.06.2020, 12:24

package ru.progwards.java2.lessons.reflection;


import java.io.IOException;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassInspector {

    private static List<String> list = new ArrayList<>();
    private static StringBuilder result = new StringBuilder();
    public static void inspect(String clazz, String outFolder) throws ClassNotFoundException {
        Class<?> inspectedClass = Class.forName(clazz);

        showClass(inspectedClass);

        String className = inspectedClass.getSimpleName();
        Path dirOut = Paths.get(outFolder).resolve("output");
        Path newFile = dirOut.resolve(className + ".java");
        if (!Files.exists(dirOut)) {
            try {
                Files.createDirectory(dirOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!Files.exists(newFile)) {
            try {
                Files.createFile(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Files.exists(newFile)) {
            try {
                Files.write(newFile, className.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void showClass(Class<?> inspectedClass) {
        int mod = inspectedClass.getModifiers();
        checkModifiers(mod);
        checkClassOrInterface(mod);
        System.out.println(inspectedClass.getSimpleName() + " {");
        showFields(inspectedClass);
        showConstructors(inspectedClass);
        showMethods(inspectedClass);
        System.out.println("}");
    }

    private static void showFields(Class<?> inspectedClass) {
        Field[] fields = inspectedClass.getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            String modStr = Modifier.toString(mod);
            System.out.println("    " + modStr + " " + field.getType().getSimpleName() + " " + field.getName() + ";");
        }
        System.out.println();
    }

    private static void showConstructors(Class<?> inspectedClass) {
        Constructor<?>[] constructors = inspectedClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            int mod = constructor.getModifiers();
            String modStr = Modifier.toString(mod);
            String nameCons = constructor.getDeclaringClass().getSimpleName();
            System.out.print("    " + modStr + " " + nameCons);
            checkParameters(constructor.getParameters());
        }
        System.out.println();
    }

    private static void showMethods(Class<?> inspectedClass) {
        Method[] methods = inspectedClass.getDeclaredMethods();
        Arrays.sort(methods, (o1, o2) -> Integer.compare(o1.getName().compareTo(o2.getName()), 1));
        for (Method method : methods) {
            int mod = method.getModifiers();
            System.out.print("    ");
            checkModifiers(mod);
            System.out.print(method.getReturnType().getSimpleName() + " ");
            System.out.print(method.getName());
            checkParameters(method.getParameters());
        }
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
            inspect("ru.progwards.java2.lessons.reflection.Person",
                    "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java2\\lessons\\reflection");
//            inspect("ru.progwards.java2.lessons.gc.Heap");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
