package ru.progwards.filecomparator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileCompare {

    List<String> listOne = new ArrayList<>();
    List<String> listTwo = new ArrayList<>();

    public void readFiles(String pathOne, String pathTwo) {
        Path pathFileOne = null;
        Path pathFileTwo = null;
        if (pathOne != null)
            pathFileOne = Paths.get(pathOne);
        else
            System.out.println("Файл 1 не выбран!");
        if (pathTwo != null)
            pathFileTwo = Paths.get(pathTwo);
        else
            System.out.println("Файл 2 не выбран!");

        try {
            assert pathFileOne != null;
            listOne = Files.readAllLines(pathFileOne);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert pathFileTwo != null;
            listTwo = Files.readAllLines(pathFileTwo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        FileCompare test = new FileCompare();
        test.readFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\01.txt",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\02.txt");
    }
}
