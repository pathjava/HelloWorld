package ru.progwards.sever.testprogwards.test_10;

import java.io.IOException;

public class AlwaysExceptions implements AutoCloseable {
    int id = 0;

    public AlwaysExceptions(int id) throws IOException {
        this.id = id;
    }

    public void method() throws IOException {
        throw new IOException(this + ": exception from method");
    }

    @Override
    public void close() throws Exception {
        throw new IOException(this + ": exception from close");
    }

    @Override
    public String toString() {
        return "AlwaysExceptions (" + id + ")";
    }
}
