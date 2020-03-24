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
        final int MAX_SIZE_ARRAY = Math.max(listOne.size(), listTwo.size());
        Map<Integer, String> fileFinalMap = new TreeMap<>();
        for (int i = 0; i < MAX_SIZE_ARRAY; i++) {
            fileFinalMap.put(i, "null");
        }

        int temp = 0;
        for (int i = 0; i < listOne.size(); i++) {
            if (temp < i) temp = i;
            for (int j = temp; j < listTwo.size(); j++) {
                if (!(listOne.get(i).equals(listTwo.get(j)))) {
                } else {
                    if (listOne.get(i).equals(listTwo.get(j)))
                        if (i + 1 < listOne.size() && j + 1 < listTwo.size() && (listOne.get(i + 1).equals(listTwo.get(j + 1))))
                            if (i + 2 < listOne.size() && j + 2 < listTwo.size() && (listOne.get(i + 2).equals(listTwo.get(j + 2)))) {
                                fileFinalMap.put(j, listOne.get(i));
                                fileFinalMap.put(j + 1, listOne.get(i + 1));
                                fileFinalMap.put(j + 2, listOne.get(i + 2));
                                i++;
                                temp = j;
                            }
                }
            }
        }
        return fileFinalMap;
    }


    public static void main(String[] args) {
        FileCompare test = new FileCompare();
        test.readFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\01.txt",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\02.txt");

//        System.out.println("-----------One------------");
//        int countOne = 1;
//        for (String s : test.listOne) {
//            System.out.format("%3d", countOne);
//            System.out.println(": " + s);
//            countOne++;
//        }
//
//        System.out.println("-----------Two------------");
//        int countTwo = 1;
//        for (String s : test.listTwo) {
//            System.out.format("%3d", countTwo);
//            System.out.println(": " + s);
//            countTwo++;
//        }

        System.out.println("-----------Patch------------");
        for (Map.Entry<Integer, String> entry : test.compareFiles().entrySet()) {
            System.out.format("%3d", entry.getKey());
            System.out.println(": " + entry.getValue());
        }
    }
}
