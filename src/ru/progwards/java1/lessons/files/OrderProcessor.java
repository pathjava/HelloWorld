package ru.progwards.java1.lessons.files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class OrderProcessor {
    Path startPath;

    public OrderProcessor(String startPath) {
        this.startPath = Paths.get(startPath);
    }

    public int loadOrders(LocalDate start, LocalDate finish, String shopId){
        return 0;
    }
}
