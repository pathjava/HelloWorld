// Oleg Kiselev
// 04.06.2020, 17:34

package ru.progwards.java2.lessons.reflection;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GettersAndSetters {

    public static void check(String string) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(string);

        checkFields(clazz);
    }

    private static final List<String> listFields = new ArrayList<>();

    private static void checkFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isPrivate(mod) && !Modifier.isStatic(mod) && !Modifier.isFinal(mod)) {
                String tempName = field.getName();
                String getName = "get" + tempName.substring(0, 1).toUpperCase() + tempName.substring(1);
                listFields.add(getName);
                String setName = "set" + tempName.substring(0, 1).toUpperCase() + tempName.substring(1);
                listFields.add(setName);
            }
        }
        checkGettersAndSetters(clazz);
    }

    private static void checkGettersAndSetters(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Iterator<String> it = listFields.iterator(); it.hasNext(); ) {
            String listField = it.next();
            for (Method method : methods) {
                String nameStr = method.getName();
                if (listField.equals(nameStr))
                    it.remove();
            }
        }
        builderGettersAndSetters(clazz);
    }

    private static void builderGettersAndSetters(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String typeField = field.getType().getSimpleName();
            for (String listField : listFields) {
                String name = listField.substring(3).toLowerCase();
                String prefName = listField.substring(0, 3).toLowerCase();
                if (fieldName.equals(name) && prefName.equals("get"))
                    builderGetters(typeField, listField);
                if (fieldName.equals(name) && prefName.equals("set"))
                    builderSetters(typeField, listField, fieldName);
            }
        }
    }

    private static void builderSetters(String typeField, String listField, String fieldName) {
        StringBuilder builder = new StringBuilder();
        builder.append("public void ").append(listField).append("(")
                .append(typeField).append(" ").append(fieldName).append(")");
        System.out.println(builder.toString());
    }

    private static void builderGetters(String typeField, String listField) {
        StringBuilder builder = new StringBuilder();
        builder.append("public ").append(typeField).append(" ").append(listField).append("()");
        System.out.println(builder.toString());
    }


    public static void main(String[] args) {
        try {
            check("ru.progwards.java2.lessons.reflection.testfiles.PersonGettersAndSetters");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
