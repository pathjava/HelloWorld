// Oleg Kiselev
// 22.06.2020, 12:10

package ru.progwards.sever.testprogwards2.test_10.smartagent;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class TextTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(
            ClassLoader loader,
            String className,
            Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain,
            byte[] classfileBuffer
    ) {
        if (className.endsWith("SimpleApplication")) {
            System.out.println("TextTransformer: замена текста в " + className);
            byte[] text = "Progwards передаёт привет!  ".getBytes();
            System.arraycopy(text, 0, classfileBuffer, 0x12D, text.length);
            return classfileBuffer;
        }
        return null;
    }
}
