package ru.progwards.sever.testprogwards.test_10;

import java.io.IOException;

public class SuppressedExceptions2 {
    public static void doAlwaysExceptions() throws Exception {
        AlwaysExceptions ae = new AlwaysExceptions(1);
        try {
            ae.method();
        } finally {
            ae.close();
        }
    }

    public static void main(String[] args) {
        try {
            doAlwaysExceptions();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }
}
