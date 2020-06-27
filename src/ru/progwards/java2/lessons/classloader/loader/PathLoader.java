// Oleg Kiselev
// 26.06.2020, 19:20

// Oleg Kiselev
// 20.06.2020, 18:59

package ru.progwards.java2.lessons.classloader.loader;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class PathLoader extends ClassLoader {
    private final static String DOT_CLASS = ".class";
    private PathLoader loader;
    private final String basePath;

    public PathLoader(String basePath) {
        this(basePath, ClassLoader.getSystemClassLoader());
    }

    public PathLoader(String basePath, ClassLoader parent) {
        super(parent);
        this.basePath = basePath;
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            String classPath = className.replace(".", "/");
            Path classPathName = Paths.get(basePath + classPath + DOT_CLASS);
            String classNameWithoutDate = className.substring(9);
            if (Files.exists(classPathName)) {
                byte[] b = Files.readAllBytes(classPathName);
                Class<?> tempClass = null;
                try {
                    tempClass = defineClass(classNameWithoutDate, b, 0, b.length);
                } catch (ClassFormatError classFormatError) {
                    patchLoadFormatError(className, classFormatError);
                    classFormatError.printStackTrace();
                }
                return tempClass;
            } else
                return findSystemClass(className);
        } catch (IOException ex) {
            throw new ClassNotFoundException(className);
        }
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        Class<?> c = findClass(name);
        if (resolve)
            resolveClass(c);
        return c;
    }

    private void updateTaskList(Map<String, Task> tasks, PathLoader pathLoader)
            throws IOException {
        loader = pathLoader;
        Files.walkFileTree(Paths.get(basePath), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                if (path.toString().endsWith(DOT_CLASS)) {
                    String className = makeClassName(path);
                    String classNameWithoutDate = className.substring(9);
                    Task task = tasks.get(classNameWithoutDate);
                    if (task == null || task.getModifiedTime() < attrs.lastModifiedTime().toMillis()) {
                        try {
                            if (task != null)
                                loader = new PathLoader(basePath);
                            Class<?> taskClass = loader.loadClass(className, true);
                            Task newTask = (Task) taskClass.getDeclaredConstructor().newInstance();
                            newTask.setModifiedTime(attrs.lastModifiedTime().toMillis());
                            tasks.remove(classNameWithoutDate);
                            tasks.put(classNameWithoutDate, newTask);
                            System.out.println((task == null ? "Добавлен" : "Обновлён") + " класс " + className);
                            patchLoadedSuccessfully(className);
                        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                                NoSuchMethodException | InvocationTargetException e) {
                            patchNotLoaded(className, e);
                            e.printStackTrace();
                        }
                    }
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private String makeClassName(Path path) throws IOException {
        path = path.toAbsolutePath().toRealPath();
        Path relPath = Paths.get(basePath).relativize(path);
        String className = relPath.toString().replaceAll("[\\/\\\\]", ".");
        if (className.toLowerCase().endsWith(DOT_CLASS))
            className = className.substring(0, className.length() - DOT_CLASS.length());
        return className;
    }

    private void patchLoadedSuccessfully(String className) {
        String logString = getDateTimeLoadFile() + " " + className + " загружен из " + basePath + " успешно\n";
        logWriter(logString);
    }

    private void patchLoadFormatError(String className, ClassFormatError error) {
        String logString = getDateTimeLoadFile() + " " + className + " ошибка загрузки " + error + "\n";
        logWriter(logString);
    }

    private void patchNotLoaded(String className, Exception... exception) {
        String logString = getDateTimeLoadFile() + " " + className +
                " ошибка загрузки " + Arrays.toString(exception) + "\n";
        logWriter(logString);
    }

    private void logWriter(String logString) {
        try (FileWriter logFile = new FileWriter(getPathLogFile(), true)) {
            logFile.write(logString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getDateTimeLoadFile() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return now.format(formatter);
    }

    private String getPathLogFile() {
        String directory = System.getProperty("user.dir");
        String namePackage = PathLoader.class.getName();
        int index = namePackage.lastIndexOf(".");
        if (index > -1)
            namePackage = namePackage.substring(0, index);
        namePackage = namePackage.replace(".", "\\");
        return directory + "\\src\\" + namePackage + "\\" + "patchloader.log";
    }

    public static void main(String[] args) throws Exception {
        PathLoader pathLoader = new PathLoader(
                "C:/Intellij Idea/programming/HelloWorld/src/ru/progwards/java2/lessons/classloader/loader/root/");
        Map<String, Task> tasks = new LinkedHashMap<>();
        while (true) {
            System.out.println("Проверка классов и запуск задач: " +
                    String.format("%1$tI:%1$tM:%1$tS.%1$tN", new Date()));
            pathLoader.updateTaskList(tasks, pathLoader);
            SecureRandom random = new SecureRandom();
            byte[] data = new byte[1000];
            random.nextBytes(data);
            for (var task : tasks.entrySet())
                System.out.println("     " + task.getValue().process(data));
            Thread.sleep(5_000);
        }
    }
}

