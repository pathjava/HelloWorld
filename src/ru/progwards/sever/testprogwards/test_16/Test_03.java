package ru.progwards.sever.testprogwards.test_16;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

public class Test_03 {
    public static void main(String args[]) {
//        // задаём целевой каталог
//        File dir = new File("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\sever\\testprogwards");
//        // получаем список файлов и каталогов
//        File[] listFiles = dir.listFiles();
//        for (File f : listFiles) {
//            System.out.println(
//                    String.format("%-17s", f.getName()) +
//                            (f.isHidden() ? "скрытый " : "") +
//                            (f.isDirectory() ? "каталог" : f.length() + " байт")
//            );
//        }

        try {
            Files.walkFileTree(Paths.get("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards"), new SimpleFileVisitor<Path>() {
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


//        File folder = new File("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java1");
//        for (File file : Objects.requireNonNull(folder.listFiles())) {
//            System.out.println(file.getParent());
//            System.out.println(file.isDirectory());
//        }
    }
}
