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
        searchAnchorLines();
        return fileFinalMap;
    }

    private void searchAnchorLines() {
        boolean checkInOneLine = true;
        boolean checkInThreeLines = false;
        int temp = 0;
        for (int i = 0; i < listOne.size() - 2; i++) {
            for (int j = 0; j < listTwo.size() - 2; j++) {
                if (j < temp && temp != 0) j = temp;
                if (checkInOneLine) {
                    if (checkingCoincidenceLines(i, j)) {
                        if (i + 1 < listOne.size()) i++;
                    } else {
                        if (i > 2) addLinesBeforeMatching(j);
                        checkInThreeLines = true;
                        checkInOneLine = false;
                        if (i > 2 && i + 2 < listOne.size()) i += 2;
                        if (j > 2 && j + 2 < listOne.size()) j += 2;
                        temp = j;
                    }
                }
                if (checkInThreeLines) {
                    if (checkingCoincidenceLines(i, j)) {
                        addLinesAfterMismatch(j);
                        checkInOneLine = true;
                        checkInThreeLines = false;
                        if (i + 1 < listOne.size()) i++;
                        temp = j;
                    }
                }
            }
        }
//        boolean modifiedRow = false;
//        boolean nonModifiedRow = false;
//        for (int i = 0; i < listOne.size() - 2; i++) {
//            for (int j = 0; j < listTwo.size() - 2; j++) {
//                if (j == 0 && i < listTwo.size() - 1) j = i;
//                if ((i < listOne.size() && listOne.get(i).equals(listTwo.get(j)))
//                        && (i + 1 < listOne.size() && j + 1 < listTwo.size() && listOne.get(i + 1).equals(listTwo.get(j + 1)))
//                        && (i + 2 < listOne.size() && j + 2 < listTwo.size() && listOne.get(i + 2).equals(listTwo.get(j + 2)))) {
//                    nonModifiedRow = true;
//                    if (modifiedRow)
//                        addLinesAfter(j);
//                    if (!modifiedRow) {
//                        if (i + 1 < listOne.size()) i++;
//                    } else {
//                        if (i + 2 < listOne.size()) i += 2;
//                        if (j + 1 < listTwo.size()) j += 1;
//                        modifiedRow = false;
//                    }
//                } else if (nonModifiedRow) {
//                    addLinesBefore(j);
//                    nonModifiedRow = false;
//                } else
//                    modifiedRow = true;
//            }
//        }
    }

    private boolean checkingCoincidenceLines(int i, int j) {
        return (i < listOne.size() && listOne.get(i).equals(listTwo.get(j)))
                && (i + 1 < listOne.size() && j + 1 < listTwo.size() && listOne.get(i + 1).equals(listTwo.get(j + 1)))
                && (i + 2 < listOne.size() && j + 2 < listTwo.size() && listOne.get(i + 2).equals(listTwo.get(j + 2)));
    }

    private void addLinesAfterMismatch(int jForward) {
        int i = 0;
        while (i <= 2) {
            fileFinalMap.put(jForward + i, listTwo.get(jForward + i));
            i++;
        }
    }

    private void addLinesBeforeMatching(int jForward) {
        int i = 0;
        while (i <= 2) {
            if (jForward - i + 1 < listTwo.size())
                fileFinalMap.put(jForward - i + 1, listTwo.get(jForward - i + 1));
            else
                fileFinalMap.put(jForward - i, listTwo.get(jForward - i));
            i++;
        }
    }


    public static void main(String[] args) {
        FileCompareFour test = new FileCompareFour();
        test.readFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\07.txt",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\08.txt");

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

        System.out.println("------------ Patch -------------");
        for (Map.Entry<Integer, String> entry : test.compareFiles().entrySet()) {
            System.out.format("%3d", entry.getKey());
            System.out.println(": " + entry.getValue());
        }
    }
}
