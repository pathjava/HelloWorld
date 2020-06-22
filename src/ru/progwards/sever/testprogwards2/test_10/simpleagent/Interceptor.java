// Oleg Kiselev
// 22.06.2020, 9:32

package ru.progwards.sever.testprogwards2.test_10.simpleagent;

import java.lang.instrument.Instrumentation;

public class Interceptor {
    public static void premain(String agentArgument, Instrumentation instrumentation) {
        System.out.println("Interceptor.premain start");
        instrumentation.addTransformer(new SimpleTransformer());
        System.out.println("На перехвате установлен SimpleTransformer");
        System.out.println("Interceptor.premain finish");
    }

}