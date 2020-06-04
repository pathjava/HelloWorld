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

    private static final List<String> list = new ArrayList<>();

    public static void inspect(String clazz, String outFolder) throws ClassNotFoundException {
        Class<?> inspectedClass = Class.forName(clazz);

        showClass(inspectedClass);
        showFields(inspectedClass);
        showConstructors(inspectedClass);
        showMethods(inspectedClass);
        list.add("} \n */");

        for (String s : list) {
            System.out.print(s);
        }

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
                Files.write(newFile, list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void showClass(Class<?> inspectedClass) {
        StringBuilder builder = new StringBuilder();
        int mod = inspectedClass.getModifiers();
        builder.append("/* \n").append(checkModifiers(mod)).append(checkClassOrInterface(mod))
                .append(inspectedClass.getSimpleName()).append(" {\n");
        list.add(builder.toString());
    }

    private static void showFields(Class<?> inspectedClass) {
        StringBuilder builder = new StringBuilder();
        Field[] fields = inspectedClass.getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            String modStr = Modifier.toString(mod);
            builder.append("    ").append(modStr).append(" ").append(field.getType().getSimpleName()).
                    append(" ").append(field.getName()).append(";\n");
        }
        list.add(builder.toString());
//        list.add("\n");
    }

    private static void showConstructors(Class<?> inspectedClass) {
        StringBuilder builder = new StringBuilder();
        Constructor<?>[] constructors = inspectedClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            int mod = constructor.getModifiers();
            String modStr = Modifier.toString(mod);
            String nameCons = constructor.getDeclaringClass().getSimpleName();
            builder.append("    ").append(modStr).append(" ").append(nameCons)
                    .append(checkParameters(constructor.getParameters())).append("\n");
        }
        list.add(builder.toString());
//        list.add("\n");
    }

    private static void showMethods(Class<?> inspectedClass) {
        StringBuilder builder = new StringBuilder();
        Method[] methods = inspectedClass.getDeclaredMethods();
        Arrays.sort(methods, (o1, o2) -> Integer.compare(o1.getName().compareTo(o2.getName()), 1));
        for (Method method : methods) {
            int mod = method.getModifiers();
            builder.append("    ").append(checkModifiers(mod)).append(method.getReturnType().getSimpleName())
                    .append(" ").append(method.getName()).append(checkParameters(method.getParameters())).append("\n");
        }
        list.add(builder.toString());
    }

    private static String checkParameters(Parameter[] parameters) {
        StringBuilder builder = new StringBuilder();
        StringBuilder stringParam = new StringBuilder();
        int count = parameters.length;
        for (Parameter parameter : parameters) {
            String type = parameter.getType().getSimpleName();
            String nameParam = parameter.getName();
            String comma = count > 1 ? ", " : "";
            stringParam.append(type).append(" ").append(nameParam).append(comma);
            count--;
        }
        builder.append("(").append(stringParam.toString()).append(") {}");
        return builder.toString();
    }

    private static String checkModifiers(int mod) {
        StringBuilder builder = new StringBuilder();
        builder.append(Modifier.isPublic(mod) ? "public " : "")
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
        return builder.toString();
    }

    private static String checkClassOrInterface(int mod) {
        return Modifier.isInterface(mod) ? "interface " : "class ";
    }


    public static void main(String[] args) {
        try {
//            inspect("ru.progwards.java2.lessons.reflection.PersonInterface",
//                    "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java2\\lessons\\reflection");
//            inspect("ru.progwards.java2.lessons.reflection.PersonAbstract",
//                    "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java2\\lessons\\reflection");
            inspect("ru.progwards.java2.lessons.reflection.Person",
                    "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java2\\lessons\\reflection");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
