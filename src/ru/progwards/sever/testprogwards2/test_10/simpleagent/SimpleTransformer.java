// Oleg Kiselev
// 22.06.2020, 9:33

package ru.progwards.sever.testprogwards2.test_10.simpleagent;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class SimpleTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(
            ClassLoader loader,
            String className,
            Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain,
            byte[] classfileBuffer
    ) {
        if (className.contains("Simple"))
            System.out.println("SimpleTransformer load class: " + className);
        return null;
    }
}
