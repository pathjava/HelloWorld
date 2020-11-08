package ru.progwards.sever.testprogwards.test_10;

import java.io.IOException;

public class SuppressedTryWithResources2 {
    public static void doAlwaysExceptions() throws Exception {
        try (AlwaysExceptions ae1 = new AlwaysExceptions(1);
             AlwaysExceptions ae2 = new AlwaysExceptions(2)) {
            ae1.method();
            ae2.method();
        }
    }

    public static void main(String[] args) {
        try {
            doAlwaysExceptions();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            for (Throwable t : e.getSuppressed())
                System.out.println(t.getMessage() + " (подавленные)");
        }
    }
}
