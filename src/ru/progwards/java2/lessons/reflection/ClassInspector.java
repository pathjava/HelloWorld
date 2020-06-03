// Oleg Kiselev
// 03.06.2020, 12:24

package ru.progwards.java2.lessons.reflection;


import java.lang.reflect.Modifier;

public class ClassInspector {

    public static StringBuilder stringBuilder = new StringBuilder();

    public static void inspect(String clazz) throws ClassNotFoundException {

        Class<?> inspectedClass = Class.forName(clazz);
//        int mod = inspectedClass.getModifiers();

        printClass(inspectedClass);

//        String classOrInterface = Modifier.isInterface(mod) ? "interface " : "class ";
//        System.out.println(classOrInterface);



//        System.out.println(inspectedClass.getSimpleName());
//
//        int modifierClass = inspectedClass.getModifiers();
//        String modStr = Modifier.toString(modifierClass);
    }

    private static void printClass(Class<?> inspectedClass){
        int mod = inspectedClass.getModifiers();
        checkClassOrInterface(mod);
        System.out.print(inspectedClass.getSimpleName() + " {");
    }

    private static void checkClassOrInterface(int mod){
        stringBuilder.append(Modifier.isInterface(mod) ? "interface " : "class ");
        System.out.print(stringBuilder.toString());
    }







    public static void main(String[] args) throws ClassNotFoundException {
        inspect("ru.progwards.java2.lessons.reflection.Person");
    }

}
