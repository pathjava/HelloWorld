package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FindDuplicates {

    List<Path> temporaryList = new ArrayList<>();

    public List<List<String>> findDuplicates(String startPath) {
        try {
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    temporaryList.add(path);
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
        return sameFile();
    }

    private List<List<String>> sameFile() {
        List<List<String>> outerList = new ArrayList<>();
        List<String> innerList;
        Object firstLastMod = null;
        Object secondLastMod = null;
        String firstContent = null;
        String secondContent = null;
        long firstSize = 0;
        long secondSize = 0;
        for (int i = 0; i < temporaryList.size(); i++) {
            innerList = new ArrayList<>();
            Path firstPath = temporaryList.get(i);
            try {
                firstLastMod = Files.getAttribute(firstPath, "basic:lastModifiedTime");
                firstContent = Files.readString(firstPath);
                firstSize = Files.size(firstPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int j = i + 1; j < temporaryList.size(); j++) {
                Path secondPath = temporaryList.get(j);
                try {
                    secondLastMod = Files.getAttribute(secondPath, "basic:lastModifiedTime");
                    secondContent = Files.readString(secondPath);
                    secondSize = Files.size(secondPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert firstLastMod != null;
                assert firstContent != null;
                if (firstPath.getFileName().equals(secondPath.getFileName()) && firstLastMod.equals(secondLastMod) && firstContent.equals(secondContent) && firstSize == secondSize) {
                     innerList.add("" + firstPath);
                    innerList.add("" + secondPath);
                }
            }
            if (!innerList.isEmpty()) outerList.add(innerList);
        }
//        temporaryList.clear();
        return outerList;
    }


    public static void main(String[] args) {
        FindDuplicates test = new FindDuplicates();
//        test.findDuplicates("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\datetime");
//
        for (List<String> duplicate : test.findDuplicates("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\sever\\testprogwards\\test_16\\rootdir")) {
            System.out.println(duplicate);
        }

        System.out.println("--------------------------");
        for (Path s : test.temporaryList) {
            System.out.println(s);
        }
    }
}
