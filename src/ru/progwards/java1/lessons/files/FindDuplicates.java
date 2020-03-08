package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FindDuplicates {

    List<String> temporaryList = new ArrayList<>();

    public List<List<String>> findDuplicates(String startPath) {
        try {
            Files.walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    String tempString = null;
                    try {
                        tempString = Files.readString(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    temporaryList.add(tempString);
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
        return sameFile();
    }

    private List<List<String>> sameFile() {
        List<List<String>> outerList = new ArrayList<List<String>>();
        List<String> innerList;
        String firstLastMod = null;
        String secondLastMod = null;
        String firstContent = null;
        String secondContent = null;
        for (int i = 0; i < temporaryList.size(); i++) {
            Path firstPath = Paths.get(temporaryList.get(i));
            try {
                firstLastMod = (String) Files.getAttribute(firstPath, "basic:lastModifiedTime");
                firstContent = Files.readString(firstPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int j = i+1; j < temporaryList.size(); j++) {
                Path secondPath = Paths.get(temporaryList.get(j));
                try {
                    secondLastMod = (String) Files.getAttribute(secondPath, "basic:size");
                    secondContent = Files.readString(secondPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert firstLastMod != null;
                assert firstContent != null;
                if (firstPath.getFileName().equals(secondPath.getFileName()) && firstLastMod.equals(secondLastMod) && firstContent.equals(secondContent)){

                }
            }
        }
    }


    public static void main(String[] args) {
        FindDuplicates test = new FindDuplicates();
        test.findDuplicates("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\datetime");

        for (String s : test.temporaryList) {
            System.out.println(s);
        }
    }
}
