// Oleg Kiselev
// 17.06.2020, 15:21

//package ru.progwards.java2.lessons.annotation;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.lang.reflect.Method;
//import java.util.TreeMap;
//
//public class JTest {
//
//    private void run(String name) throws Exception {
//        Class<?> testClass = Class.forName(name);
//        Method[] methods = testClass.getDeclaredMethods();
//            //...
//        TreeMap<Integer, Method> testMethods = new TreeMap<>();
//        for (Method m : methods) {
//            if (m.isAnnotationPresent(Before.class)) {
//                //...
//            } else if (m.isAnnotationPresent(Test.class)) {
//                int priority = m.getAnnotation(Test.class).priority();
//                //...
//                testMethods.put(priority, m);
//            } else if (m.isAnnotationPresent(After.class)) {
//                //...
//            }
//        }
//        Object object = testClass.getConstructor().newInstance();
//        beforeMethod.invoke(object);
//        for (Method m : testMethods.values()) {
//            m.invoke(object);
//        }
//        afterMethod.invoke(object);
//    }
//
//    private void priority(){
//
//    }
//
//}
