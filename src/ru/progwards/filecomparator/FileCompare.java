package ru.progwards.filecomparator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileCompare {

    private List<String> listOne = new ArrayList<>();
    private List<String> listTwo = new ArrayList<>();

    public void readFiles(String pathOne, String pathTwo) {
        if (pathOne == null || pathOne.equals("") || pathTwo == null || pathTwo.equals("")) {
            System.out.println("Не выбран файл!");
            return;
        }

        try (BufferedReader readerOne = new BufferedReader(new FileReader(pathOne))) {
            String lineOne;
            while ((lineOne = readerOne.readLine()) != null) {
                listOne.add(lineOne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader readerTwo = new BufferedReader(new FileReader(pathTwo))) {
            String lineTwo;
            while ((lineTwo = readerTwo.readLine()) != null) {
                listTwo.add(lineTwo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, String> compareFiles() {
        Map<Integer, String> fileMap = new TreeMap<>();

        int count = 0;
        for (int i = 0; i < listOne.size(); i++) {
            for (int j = 0; j < listTwo.size(); j++) {
                if (!(listOne.get(i).equals(listTwo.get(j)))) {
                    if (listTwo.get(j).isEmpty())
                        fileMap.put(j, "+");
                    else
                        fileMap.put(j, "+" + listTwo.get(j));
                }
            }
        }
        return fileMap;
    }


    public static void main(String[] args) {
        FileCompare test = new FileCompare();
        test.readFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\01.txt",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\02.txt");

        System.out.println("-----------One------------");
        int countOne = 1;
        for (String s : test.listOne) {
            System.out.format("%3d", countOne);
            System.out.println(": " + s);
            countOne++;
        }

        System.out.println("-----------Two------------");
        int countTwo = 1;
        for (String s : test.listTwo) {
            System.out.format("%3d", countTwo);
            System.out.println(": " + s);
            countTwo++;
        }

        System.out.println("-----------Patch------------");
        for (Map.Entry<Integer, String> entry : test.compareFiles().entrySet()) {
            System.out.format("%3d", entry.getKey());
            System.out.println(": " + entry.getValue());
        }
    }
}
