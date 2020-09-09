package ru.progwards.java2.lessons.sort;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

public class GenData {
    static void generate() {
        try (PrintWriter file = new PrintWriter(new FileOutputStream(new File("data.txt")))) {
            for (int i = 0; i < 200_000_000; i++)
                file.println(ThreadLocalRandom.current().nextInt());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("start");
        generate();
        System.out.println("stop");
    }
}
