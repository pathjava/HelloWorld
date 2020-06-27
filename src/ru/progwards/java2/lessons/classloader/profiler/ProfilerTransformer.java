// Oleg Kiselev
// 27.06.2020, 9:51

package ru.progwards.java2.lessons.classloader.profiler;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class ProfilerTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        ClassPool cp = ClassPool.getDefault();
        try {
            CtClass ct = cp.get("ru.progwards.java2.lessons.classloader.profiler.TestSpeed");

            CtMethod ctMethod = ct.getDeclaredMethod("bubbleSort");
            ctMethod.addLocalVariable("start", CtClass.longType);
            ctMethod.insertBefore("start = System.currentTimeMillis();");
            ctMethod.insertAfter("System.out.println(\"время выполнения\" + (System.currentTimeMillis() - start));");
            ct.toBytecode();
        } catch (IOException | CannotCompileException | NotFoundException e) {
            e.printStackTrace();
        }
        return classfileBuffer;
    }
}
