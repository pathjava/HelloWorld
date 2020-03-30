package ru.progwards.filecomparator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileCompareFour {
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
                if (lineOne.isEmpty())
                    listOne.add("emptyString");
                else listOne.add(lineOne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader readerTwo = new BufferedReader(new FileReader(pathTwo))) {
            String lineTwo;
            while ((lineTwo = readerTwo.readLine()) != null) {
                if (lineTwo.isEmpty())
                    listTwo.add("emptyString");
                else listTwo.add(lineTwo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<Integer, String> fileFinalMap = new HashMap<>();

    public Map<Integer, String> compareFiles() {
        final int MAX_SIZE_ARRAY = Math.max(listOne.size(), listTwo.size());
        for (int i = 0; i < MAX_SIZE_ARRAY; i++) {
            fileFinalMap.put(i, "+");
        }
        searchAnchorForward();
//        searchAnchorBack();
        return fileFinalMap;
    }

    private void searchAnchorForward() {
        for (int i = 0; i < listOne.size(); i++) {
            int countBefore = 0;
            int countAfter = 0;
            for (int j = 0; j < listTwo.size(); j++) {
                if (j == 0) j = i;
                if ((i < listOne.size() && listOne.get(i).equals(listTwo.get(j)))
                        && (i + 1 < listOne.size() && j + 1 < listTwo.size() && listOne.get(i + 1).equals(listTwo.get(j + 1)))
                        && (i + 2 < listOne.size() && j + 2 < listTwo.size() && listOne.get(i + 2).equals(listTwo.get(j + 2)))) {
                    countAfter++;
                    if (countBefore != 0)
                        addLinesForward(j);
                    if (countBefore == 0)
                        i++;
                    else {
                        i += countBefore;
                        j += countBefore - 1;
                        countBefore = 0;
                    }
                } else if (countAfter != 0) {
                    addLinesBack(j);
                    countAfter = 0;
                } else
                    countBefore++;
            }
        }
    }

    private void addLinesForward(int jForward) {
        int i = 0;
        while (i <= 2) {
            fileFinalMap.put(jForward + i, listTwo.get(jForward + i));
            i++;
        }
    }

    private void addLinesBack(int jForward) {
        int i = 0;
        while (i <= 2) {
            fileFinalMap.put(jForward - i+1, listTwo.get(jForward - i+1));
            i++;
        }
    }


    public static void main(String[] args) {
        FileCompareFour test = new FileCompareFour();
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
