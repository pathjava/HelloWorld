package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FilesSelect {
    public void selectFiles(String inFolder, String outFolder, List<String> keys) {
        /* заводим временный ArrayList */
        List<Path> temporaryList = new ArrayList<>();
        /* создаем шаблон для поиска файлов с расширением .txt */
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.txt");
        try {
            Files.walkFileTree(Paths.get(inFolder), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    /* если найденный файл соответствует шаблону по расширению */
                    if (pathMatcher.matches(path)) {
                        /* добавляем найденный путь в ArrayList */
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
        /* в цикле поочередно проходим по сохраненным ранее путям */
        for (Path path : temporaryList) {
            String fileContent = null;
            try {
                /* считываем содержимое файла */
                fileContent = Files.readString(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            /* в цикле проходим по листу с ключевыми словами */
            for (String key : keys) {
                /* если переменная с содержимым из файла не пустая и
                 * текст из файла содержит ключевое слово */
                if (fileContent != null && fileContent.contains(key)) {
                    /* создаем путь директории с конечной директорией с именем ключа */
                    Path directoryOut = Paths.get(outFolder).resolve(key);
                    /* проверяем, не существует ли директория по имени ключа */
                    if (!Files.exists(directoryOut)) {
                        try {
                            /* создаем директорию */
                            Files.createDirectory(directoryOut);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        /* создаем путь к файлу в новой директории */
                        Path destination = directoryOut.resolve(path.getFileName());
                        try {
                            /* копируем файл по новому пути, а если файл существует, то перезаписываем его - хотя если директории не было, то и перезаписывать нечего */
                            Files.copy(path, destination, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        /* если директория существует, то просто копируем файл в неё, а если файл существует, то перезаписываем его */
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
        /* очищаем временный ArrayList */
        temporaryList.clear();
    }


    public static void main(String[] args) {
        FilesSelect test = new FilesSelect();
        List<String> testList = new ArrayList<>(List.of("new", "copy", "testingKey"));
        test.selectFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\files\\inFolder",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\files\\outFolder", testList);
    }
}
