package ru.progwards.sever.testprogwards.test_10;

public class SuppressedExceptions1 {
    public static void main(String[] args) {
        try {
            AlwaysExceptions ae = new AlwaysExceptions(1);
            try {
                ae.method();
            } catch (Throwable t) {
                System.out.println(t.getMessage());
            } finally {
                ae.close();
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }
}
