// Oleg Kiselev
// 04.06.2020, 17:34

package ru.progwards.java2.lessons.reflection;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GettersAndSetters {

    public static void check(String string) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(string);

        checkFields(clazz);
        checkGettersAndSetters(clazz);
    }

    private static final List<String> listFields = new ArrayList<>();

    private static void checkFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String tempName = field.getName();
            String getName = "get" + tempName.substring(0, 1).toUpperCase() + tempName.substring(1);
            listFields.add(getName);
            String setName = "set" + tempName.substring(0, 1).toUpperCase() + tempName.substring(1);
            listFields.add(setName);
        }
    }

    private static void checkGettersAndSetters(Class<?> clazz) {
        StringBuilder builder = new StringBuilder();
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> listMethods = new ArrayList<>(Arrays.asList(methods));

        for (Iterator<String> it = listFields.iterator(); it.hasNext(); ) {
            String listField = it.next();
            for (Method listMethod : listMethods) {
                String nameStr = listMethod.getName();
                if (listField.equals(nameStr))
                    it.remove();
            }
        }
        for (String listField : listFields) {
            System.out.println(listField);
        }
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
        builder.append("(").append(stringParam.toString()).append(")");
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

    public static void main(String[] args) {
        try {
            check("ru.progwards.java2.lessons.reflection.testfiles.PersonGettersAndSetters");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
