package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.*;

public class OrderProcessor {
    Path startPath;
    int errorFile = 0;

    public OrderProcessor(String startPath) {
        this.startPath = Paths.get(startPath);
    }

    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");
        try {
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(path) && checkTimeModified(path, start, finish, shopId)) {
                        System.out.println(path);
                    }
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
        return errorFile;
    }

    private boolean checkTimeModified(Path path, LocalDate start, LocalDate finish, String shopId) {
        boolean checkTime = false;
        String checkLengthFileName = path.getFileName().toString();
        if (!(checkLengthFileName.length() == 19)) {
            errorFile++;
            return false;
        }
        String checkShopId = path.getFileName().toString().substring(0, 3);
        FileTime fileTime = null;
        try {
            fileTime = Files.getLastModifiedTime(Paths.get(String.valueOf(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fileTime != null;
        LocalDate localDate = LocalDate.ofInstant(fileTime.toInstant(), ZoneOffset.UTC);
        if (start == null) {
            if (localDate.compareTo(finish) <= 0 && checkShopId.equals(shopId)) {
                checkTime = true;
            }
        } else if (finish == null) {
            if (localDate.compareTo(start) >= 0 && checkShopId.equals(shopId)) {
                checkTime = true;
            }
        } else if (localDate.compareTo(start) >= 0 && localDate.compareTo(finish) <= 0 && checkShopId.equals(shopId)) {
            checkTime = true;
        }
        return checkTime;
    }


    public static void main(String[] args) {
        OrderProcessor test = new OrderProcessor("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\files\\orders");

        System.out.println(test.loadOrders(LocalDate.now().minusDays(1), LocalDate.now(), "S02"));
    }
}
