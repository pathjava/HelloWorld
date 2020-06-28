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
        if (className.endsWith("TestSpeed")) {
            try {
                final ClassPool cp = ClassPool.getDefault();
                cp.importPackage("ru.progwards.java1.lessons.datetime");
                System.out.println("name class " + className); /* for testing */
                final CtClass ct = cp.get(className.replace("/", "."));

                final CtMethod[] ctMethods = ct.getDeclaredMethods();
                for (CtMethod ctMethod : ctMethods) {
                    if (!ctMethod.getName().contains("fillArray")) {
                        System.out.println("method name start " + ctMethod.getName()); /* for testing */

                        String nameEnterSection = "Profiler.enterSection(\"" + ctMethod.getLongName() + "\");";
                        ctMethod.insertBefore(nameEnterSection);
                        String nameExitSection = "Profiler.exitSection(\"" + ctMethod.getLongName() + "\");";
                        ctMethod.insertAfter(nameExitSection);

                        System.out.println("method name end " + ctMethod.getName()); /* for testing */
                    }
                }
                classfileBuffer = ct.toBytecode();
//                ct.detach();
            } catch (IOException | CannotCompileException | NotFoundException e) {
                e.printStackTrace();
            }
        }
        return classfileBuffer;
    }
}