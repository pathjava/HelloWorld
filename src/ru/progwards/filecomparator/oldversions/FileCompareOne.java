package ru.progwards.filecomparator.oldversions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileCompareOne {

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

    private Map<Integer, String> fileFinalMap = new TreeMap<>();

    public Map<Integer, String> compareFiles() {
        final int MAX_SIZE_ARRAY = Math.max(listOne.size(), listTwo.size());

        for (int i = 0; i < MAX_SIZE_ARRAY; i++) {
            fileFinalMap.put(i, "+");
        }

        searchAnchorForward();
        searchAnchorBack();

        return fileFinalMap;
    }

    private void searchAnchorForwardByCount() {
        int j = 0;
        int count = 0;
        for (int i = 0; i < listOne.size(); i++) {
            while (j < listTwo.size()) {
                if (!(listOne.get(i).equals(listTwo.get(j))))
                    j++;
                count++;
            }
        }
    }

    private void searchAnchorForward() {
        for (int i = 0; i < listOne.size(); i++) {
            for (int j = 0; j < listTwo.size(); j++) {
                if (i < listTwo.size() && j < i) j = i - 1;
                if (!(listOne.get(i).equals(listTwo.get(j)))) {
                    if (j + 1 < listTwo.size() && (listOne.get(i).equals(listTwo.get(j + 1))))
                        if (i + 1 < listOne.size() && j + 2 < listTwo.size() && (listOne.get(i + 1).equals(listTwo.get(j + 2))))
                            if (i + 2 < listOne.size() && j + 3 < listTwo.size() && (listOne.get(i + 2).equals(listTwo.get(j + 3)))) {
                                fileFinalMap.put(j + 1, listOne.get(i));
                                fileFinalMap.put(j + 2, listOne.get(i + 1));
                                fileFinalMap.put(j + 3, listOne.get(i + 2));
                                i++;
                                j++;
                            }
                } else {
                    if (i < listOne.size() - 1) i++;
                }
            }
        }
    }

    private void searchAnchorBack() {
        for (int i = listOne.size() - 1; i >= 0; i--) {
            for (int j = listTwo.size() - 1; j >= 0; j--) {
                if (!(listOne.get(i).equals(listTwo.get(j)))) {
                    if (j - 1 >= 0 && (listOne.get(i).equals(listTwo.get(j - 1))))
                        if (i - 1 >= 0 && j - 2 >= 0 && (listOne.get(i - 1).equals(listTwo.get(j - 2))))
                            if (i - 2 >= 0 && j - 3 >= 0 && (listOne.get(i - 2).equals(listTwo.get(j - 3)))) {
                                fileFinalMap.put(j - 1, listOne.get(i));
                                fileFinalMap.put(j - 2, listOne.get(i - 1));
                                fileFinalMap.put(j - 3, listOne.get(i - 2));
                                i--;
                                j--;
                            }
                } else {
                    if (i >= 1) i--;
                }
            }
        }
    }


    public static void main(String[] args) {
        FileCompareOne test = new FileCompareOne();
        test.readFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\03.txt",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\04.txt");

//        System.out.println("-----------One------------");
//        int countOne = 1;
//        for (String s : test.listOne) {
//            System.out.format("%3d", countOne);
//            System.out.println(": " + s);
//            countOne++;
//        }

//        System.out.println("-----------Two------------");
//        int countTwo = 1;
//        for (String s : test.listTwo) {
//            System.out.format("%3d", countTwo);
//            System.out.println(": " + s);
//            countTwo++;
//        }

        System.out.println("----------- Patch ------------");
        for (Map.Entry<Integer, String> entry : test.compareFiles().entrySet()) {
            System.out.format("%3d", entry.getKey());
            System.out.println(": " + entry.getValue());
        }
    }
}
