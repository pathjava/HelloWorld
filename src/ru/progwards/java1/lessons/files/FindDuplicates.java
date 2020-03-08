package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FindDuplicates {

    public List<List<String>> findDuplicates(String startPath){
        List<List<String>> outerList = new ArrayList<List<String>>();
        List<String> innerList = new ArrayList<>();
        try {
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    System.out.println(path);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        FindDuplicates test = new FindDuplicates();
        test.findDuplicates("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards");
    }
}
