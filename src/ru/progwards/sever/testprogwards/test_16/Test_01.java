package ru.progwards.sever.testprogwards.test_16;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test_01 {

    String createFolder(String name) {
        File filePath = new File(name);
        filePath.mkdir();
        Path path = Paths.get("..");
        return path.toAbsolutePath().normalize().toString();
    }

//    public static String createFolder(String name){
//        File filePath = new File(name);
//        filePath.mkdir();
//        Path path = Paths.get("..");
//        return path.toAbsolutePath().normalize().toString();
//    }
//
//
//    public static void main(String[] args) {
//        System.out.println(createFolder("testFile"));
//    }
}
