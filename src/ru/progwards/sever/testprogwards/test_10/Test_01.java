package ru.progwards.sever.testprogwards.test_10;

import java.io.File;
import java.io.IOException;

public class Test_01 {
    public void doSomething(int n) throws IOException {
    }

    public void test(int n) throws IOException {
        //Throwable suppressed = null;
        try {
            doSomething(n);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            //suppressed = e;
            throw e;
        } finally {
            System.out.println("finally executed");
        }
    }
}
