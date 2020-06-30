// Oleg Kiselev
// 27.06.2020, 9:51

package ru.progwards.java2.lessons.classloader.profiler;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

public class ProfilerTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        try {
            ClassPool cp = ClassPool.getDefault();
            /* можно исключить из профилировки определенный пакет */
//            if (className.startsWith("ru/progwards/java2/lessons/classloader/profiler"))
//                return classfileBuffer;
            /* допуск к профилировке классов, имя которых начинается на заданный пакет */
            if (!className.startsWith("ru/progwards/java2/lessons/classloader/test"))
                return classfileBuffer;
            CtClass ct = cp.get(className.replace("/", "."));
            /* исключаем из профилировки ненужные классы */
            if (excludedClasses(ct))
                return classfileBuffer;
            /* испортируем в тестируемую программу ссылку на пакет профилировщика */
            cp.importPackage("ru.progwards.java1.lessons.datetime");
            /* получаем все методы класса */
            CtMethod[] ctMethods = ct.getDeclaredMethods();
            for (CtMethod ctMethod : ctMethods) {
                if (!excludedMethods(ctMethod)) { /* допускаем к обработке только не исключенные методы */
                    /* формируем строку вызова метода профилировщика */
                    String nameEnterSection = "{ Profiler.enterSection(\"" + ctMethod.getLongName() + "\"); }";
                    /* вставляем вызов метода прфилировщика в начало тела тестируемого метода */
                    ctMethod.insertBefore(nameEnterSection);
                    String nameExitSection = "{ Profiler.exitSection(\"" + ctMethod.getLongName() + "\"); }";
                    ctMethod.insertAfter(nameExitSection);
                } else if (ctMethod.getName().contains("main")) { /* добавляем метод вывода статистики профилировки */
                    String nameTestingClass = "{ Profiler.printStatisticInfo(\"" + ct.getSimpleName() + ".stat\"); }";
                    ctMethod.insertAfter(nameTestingClass);
                }
            }
            classfileBuffer = ct.toBytecode(); /* присваиваем измененный байт-код */
        } catch (IOException | CannotCompileException | NotFoundException e) {
            e.printStackTrace();
        }
        return classfileBuffer; /* возвращаем измененный байт-код */
    }

    /* профилировщик находится по ссылке:
     * https://github.com/pathjava/java1/blob/master/src/ru/progwards/java1/lessons/datetime/Profiler.java */

    private boolean excludedClasses(CtClass ct) { /* исключаем классы из профилировки */
        List<String> exClasses = new ArrayList<>(List.of("TestExcludedClass"));
        for (String exClass : exClasses)
            if (exClass.equals(ct.getSimpleName()))
                return true;
        return false;
    }

    private boolean excludedMethods(CtMethod ctMethod) { /* исключаем методы из профилировки */
        List<String> methods = new ArrayList<>(List.of("fillArray", "main", "mergeSorting",
                "merge", "heapify", "quickSortRealisation", "lambda$fillArray$0"));
        for (String method : methods)
            if (method.equals(ctMethod.getName()))
                return true;
        return false;
    }
}