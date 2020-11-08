package ru.progwards.sever.testprogwards.test_16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test_02 {
    boolean replaceF(String name) {
        String oldContent;
        String newContent;
        Path path = Paths.get(name);
        try {
            oldContent = Files.readString(path);
            newContent = oldContent.replaceAll("F", "f");
            Files.writeString(path, newContent);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
