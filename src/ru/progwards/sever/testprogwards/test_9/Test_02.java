package ru.progwards.sever.testprogwards.test_9;

import java.io.IOException;

public class Test_02 {
    public String test(String filename) throws IOException {
        if (filename == null)
            throw new IOException("File not found");
        else
            return "File processing";
    }
}
