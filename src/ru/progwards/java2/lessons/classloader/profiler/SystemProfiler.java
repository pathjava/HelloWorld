// Oleg Kiselev
// 26.06.2020, 19:47

package ru.progwards.java2.lessons.classloader.profiler;

import java.lang.instrument.Instrumentation;

public class SystemProfiler {
    public static void premain(String agentArgument, Instrumentation instrumentation) {
//        System.out.println("SystemProfiler: premain стартовал");
        instrumentation.addTransformer(new ProfilerTransformer());
//        System.out.println("SystemProfiler: premain завершён");
    }
}
