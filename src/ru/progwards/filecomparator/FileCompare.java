package ru.progwards.filecomparator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileCompare {

    private List<String> listMapOne = new ArrayList<>();
    private List<String> listMapTwo = new ArrayList<>();

    public void readFiles(String pathOne, String pathTwo) {
        if (pathOne == null || pathOne.equals("") || pathTwo == null || pathTwo.equals("")) {
            System.out.println("Не выбран файл!");
            return;
        }

        try (BufferedReader readerOne = new BufferedReader(new FileReader(pathOne))) {
            String lineOne;
            while ((lineOne = readerOne.readLine()) != null) {
                listMapOne.add(lineOne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader readerTwo = new BufferedReader(new FileReader(pathTwo))) {
            String lineTwo;
            while ((lineTwo = readerTwo.readLine()) != null) {
                listMapTwo.add(lineTwo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> compareFiles() {
        List<String> patchList = new ArrayList<>();

        for (int i = 0; i < listMapOne.size(); i++) {
            for (int j = 0; j < listMapTwo.size(); j++) {
                if (!(listMapOne.get(i).equals(listMapTwo.get(j)))) {
                    patchList.add("+");
                } else  {
                    patchList.add(listMapOne.get(i));
                    i++;
                }
            }
        }
        return patchList;
    }


    public static void main(String[] args) {
        FileCompare test = new FileCompare();
        test.readFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\01.txt",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\02.txt");

        System.out.println("-----------One------------");
        int countOne = 1;
        for (String s : test.listMapOne) {
            System.out.format("%3d", countOne);
            System.out.println(": " + s);
            countOne++;
        }

        System.out.println("-----------Two------------");
        int countTwo = 1;
        for (String s : test.listMapTwo) {
            System.out.format("%3d", countTwo);
            System.out.println(": " + s);
            countTwo++;
        }

        System.out.println("-----------Patch------------");
        for (String patch : test.compareFiles()) {
            System.out.println(patch);
        }
    }
}
