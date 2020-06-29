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
        if (className.endsWith("TestSpeed")) {
            try {
                ClassPool cp = ClassPool.getDefault();
                cp.importPackage("ru.progwards.java1.lessons.datetime");
                cp.importPackage("ru.progwards.java2.lessons.classloader.profiler");
                System.out.println("name class " + className); /* for testing */
                CtClass ct = cp.get(className.replace("/", "."));

                CtMethod[] ctMethods = ct.getDeclaredMethods();
                for (CtMethod ctMethod : ctMethods) {
                    if (!excludedMethods(ctMethod)) {
                        System.out.println("method name start " + ctMethod.getName()); /* for testing */

                        String nameEnterSection = "Profiler.enterSection(\"" + ctMethod.getLongName() + "\");";
                        ctMethod.insertBefore(nameEnterSection);
                        String nameExitSection = "Profiler.exitSection(\"" + ctMethod.getLongName() + "\");";
                        ctMethod.insertAfter(nameExitSection);

                        System.out.println("method name end " + ctMethod.getName()); /* for testing */
                    } else if (ctMethod.getName().contains("main")) {
//                        ctMethod.insertAfter("System.out.println(\"print something\");");
                        ctMethod.insertAfter("ProfilerTransformer.printStatisticInfo();");
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

    private boolean excludedMethods(CtMethod ctMethod) {
        List<String> methods = new ArrayList<>(List.of("fillArray", "main"));
        for (String method : methods)
            if (method.equals(ctMethod.getName()))
                return true;
        return false;
    }

    public static void printStatisticInfo() {
        System.out.println("print something");
    }
}