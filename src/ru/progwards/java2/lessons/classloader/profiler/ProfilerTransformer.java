// Oleg Kiselev
// 27.06.2020, 9:51

package ru.progwards.java2.lessons.classloader.profiler;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ProfilerTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        ClassPool cp = ClassPool.getDefault();
        cp.importPackage("ru.progwards.java2.lessons.classloader.profiler");

        if (className.startsWith("ru/progwards/java2/lessons/classloader/profiler"))
            return null;

        try {
            CtClass ct = cp.makeClass(new ByteArrayInputStream(classfileBuffer));

            CtMethod[] ctMethods = ct.getDeclaredMethods();
            for (CtMethod ctMethod : ctMethods) {
                ctMethod.addLocalVariable("start", CtClass.longType);
                ctMethod.insertBefore("start = System.currentTimeMillis();");
                ctMethod.insertAfter("System.out.println(\"время выполнения\" + (System.currentTimeMillis() - start));");
            }
            return ct.toBytecode();
        } catch (IOException | CannotCompileException e) {
            e.printStackTrace();
        }
        return classfileBuffer;
    }
}
