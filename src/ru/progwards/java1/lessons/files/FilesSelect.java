package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FilesSelect {
    public void selectFiles(String inFolder, String outFolder, List<String> keys) {
        List<Path> temporaryList = new ArrayList<>();
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.txt");
        try {
            Files.walkFileTree(Paths.get(inFolder), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(path)) {
                        temporaryList.add(path);
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

        for (Path path : temporaryList) {
            String fileContent = null;
            try {
                fileContent = Files.readString(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String key : keys) {
                if (fileContent != null && fileContent.contains(key)) {
                    Path directoryOut = Paths.get(outFolder).resolve(key);
                    if (!Files.exists(directoryOut)) {
                        try {
                            Files.createDirectory(directoryOut);
                            Path destination = directoryOut.resolve(path.getFileName());
                            Files.copy(path, destination, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Path destination = directoryOut.resolve(path.getFileName());
                        try {
                            Files.copy(path, destination, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        temporaryList.clear();
    }


    public static void main(String[] args) {
        FilesSelect test = new FilesSelect();
        List<String> testList = new ArrayList<>(List.of("new", "copy", "testingKey"));
        test.selectFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\files\\inFolder",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\files\\outFolder", testList);

    }
}
