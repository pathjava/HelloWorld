package ru.progwards.java1.lessons.files;

import java.nio.file.Path;
import java.nio.file.Paths;

public class OrderProcessor {
    Path startPath;

    public OrderProcessor(String startPath) {
        this.startPath = Paths.get(startPath);
    }
}
