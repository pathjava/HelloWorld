// Oleg Kiselev
// 20.06.2020, 18:59

package ru.progwards.java2.lessons.classloader;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.SecureRandom;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class PathLoader extends ClassLoader {
    private final static String PATH_OF_TASKS = "C:/Intellij Idea/programming/HelloWorld/src/ru/progwards/java2/lessons/classloader/root/";
    private final static String DOT_CLASS = ".class";
    private static PathLoader loader = new PathLoader(PATH_OF_TASKS);
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
                return defineClass(classNameWithoutDate, b, 0, b.length);
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

    private static void updateTaskList(Map<String, Task> tasks)
            throws IOException {
        Files.walkFileTree(Paths.get(PATH_OF_TASKS), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                if (path.toString().endsWith(DOT_CLASS)) {
                    String className = makeClassName(path);
                    String classNameWithoutDate = className.substring(9);
                    Task task = tasks.get(classNameWithoutDate);
                    if (task == null || task.getModifiedTime() < attrs.lastModifiedTime().toMillis()) {
                        try {
                            if (task != null)
                                loader = new PathLoader(PATH_OF_TASKS);
                            Class<?> taskClass = loader.loadClass(className, true);
                            Task newTask = (Task) taskClass.getDeclaredConstructor().newInstance();
                            newTask.setModifiedTime(attrs.lastModifiedTime().toMillis());
                            tasks.remove(classNameWithoutDate);
                            tasks.put(classNameWithoutDate, newTask);
                            System.out.println((task == null ? "Добавлен" : "Обновлён") + " класс " + className);
                            patchLogger(className);
                        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                                NoSuchMethodException | InvocationTargetException e) {
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

    private static String makeClassName(Path path) throws IOException {
        path = path.toAbsolutePath().toRealPath();
        Path relPath = Paths.get(PATH_OF_TASKS).relativize(path);
        String className = relPath.toString().replaceAll("[\\/\\\\]", ".");
        if (className.toLowerCase().endsWith(DOT_CLASS))
            className = className.substring(0, className.length() - DOT_CLASS.length());
        return className;
    }

    private static void patchLogger(String className) {
        try (FileWriter logFile = new FileWriter(getPathLogFile(), true)) {
            logFile.write(className + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getPathLogFile() {
        String directory = System.getProperty("user.dir");
        String namePackage = PathLoader.class.getName();
        int index = namePackage.lastIndexOf(".");
        if (index > -1)
            namePackage = namePackage.substring(0, index);
        namePackage = namePackage.replace(".", "\\");
        return directory + "\\src\\" + namePackage + "\\" + "patchloader.log";
    }

    public static void main(String[] args) throws Exception {
        Map<String, Task> tasks = new LinkedHashMap<>();
        while (true) {
            System.out.println("Проверка классов и запуск задач: " +
                    String.format("%1$tI:%1$tM:%1$tS.%1$tN", new Date()));
            updateTaskList(tasks);
            SecureRandom random = new SecureRandom();
            byte[] data = new byte[1000];
            random.nextBytes(data);
            for (var task : tasks.entrySet())
                System.out.println("     " + task.getValue().process(data));
            Thread.sleep(5_000);
        }
    }
}

