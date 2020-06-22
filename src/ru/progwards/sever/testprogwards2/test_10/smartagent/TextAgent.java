// Oleg Kiselev
// 22.06.2020, 12:08

package ru.progwards.sever.testprogwards2.test_10.smartagent;


import java.lang.instrument.Instrumentation;

public class TextAgent {
    public static void premain(String agentArgument, Instrumentation instrumentation) {
        System.out.println("TextAgent: premain стартовал");
        instrumentation.addTransformer(new TextTransformer());
        System.out.println("TextAgent: На перехвате установлен TextTransformer");
        System.out.println("TextAgent: premain завершён");
    }
}