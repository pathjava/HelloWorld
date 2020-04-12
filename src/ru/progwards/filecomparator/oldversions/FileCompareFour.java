package ru.progwards.filecomparator.oldversions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileCompareFour {
    private List<String> listOne = new ArrayList<>();
    private List<String> listTwo = new ArrayList<>();

    private int listOneSize;
    private int listTwoSize;

    public void readFiles(String pathOne, String pathTwo) {
        if (pathOne == null || pathOne.equals("") || pathTwo == null || pathTwo.equals("")) {
            System.out.println("Не выбран файл!");
            return;
        }

        try (BufferedReader readerOne = new BufferedReader(new FileReader(pathOne))) {
            String lineOne;
            while ((lineOne = readerOne.readLine()) != null) {
//                if (lineOne.isEmpty())
//                    listOne.add("isEmpty");
//                else
                listOne.add(lineOne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader readerTwo = new BufferedReader(new FileReader(pathTwo))) {
            String lineTwo;
            while ((lineTwo = readerTwo.readLine()) != null) {
//                if (lineTwo.isEmpty())
//                    listTwo.add("isEmpty");
//                else
                listTwo.add(lineTwo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<Integer, String> fileFinalMap = new HashMap<>();

    public Map<Integer, String> compareFiles() {
        listOneSize = listOne.size();
        listTwoSize = listTwo.size();
        final int MAX_SIZE_ARRAY = Math.max(listOneSize, listTwoSize);
        for (int i = 0; i < MAX_SIZE_ARRAY; i++) {
            fileFinalMap.put(i, "#");
        }
        searchAnchorLines();
        return fileFinalMap;
    }

    private void searchAnchorLines() {
        boolean checkInOneLine = true;
        boolean checkInThreeLines = false;
        int temp = 0;
        for (int i = 0; i < listOneSize; i++) {
            for (int j = 0; j < listTwoSize; j++) {
                if (j < temp && temp != 0) j = temp;
                if (checkInOneLine) {
//                    if (checkEmptyLines(i, j)) {
                    if (checkCoincidenceLines(i, j)) {
                        if (i + 1 < listOneSize) i++;
                    } else {
                        addLinesBeforeMatching(j);
                        checkInThreeLines = true;
                        checkInOneLine = false;
                        if (i > 2 && i + 2 < listOneSize) i += 2;
                        if (j > 2 && j + 2 < listOneSize) j += 2;
                        temp = j;
                    }
//                    }
                }
                if (checkInThreeLines) {
//                    if (checkEmptyLines(i, j)) {
                    if (checkCoincidenceLines(i, j)) {
                        addLinesAfterMismatch(j);
                        checkInOneLine = true;
                        checkInThreeLines = false;
                        if (i + 1 < listOneSize) i++;
                        temp = j;
                    }
//                    }
                }
            }
        }
    }

//    private int checkOne(int i) {
//        int one = i;
//        int count = 0;
//        while (one < listOneSize) {
//            if (listOne.get(one).isEmpty()) {
//                one++;
//                count++;
//            } else
//                break;
//        }
//        if (count > 1)
//            return one;
//        return i;
//    }
//
//    private int checkTwo(int j) {
//        int two = j;
//        int count = 0;
//        while (two < listTwoSize) {
//            if (listTwo.get(two).isEmpty()) {
//                two++;
//                count++;
//            } else
//                break;
//        }
//        if (count > 1)
//            return two;
//        return j;
//    }

    private boolean checkEmptyLines(int i, int j) {
        int count = 0;
        int countEmpty = 0;
//        while (countOne < 3) {
//            if (i + countOne < listOneSize && listOne.get(i + countOne).isEmpty()) {
//                countOne++;
//                countEmptyOne++;
//            } else
//                countOne++;
//        }
//        while (countTwo < 3) {
//            if (j + countTwo < listTwoSize && listTwo.get(j + countTwo).isEmpty()) {
//                countTwo++;
//                countEmptyTwo++;
//            } else {
//                countTwo++;
//            }
//        }
        while (count < 3) {
            if (i + count < listOneSize && j + count < listTwoSize
                    && (listOne.get(i + count).isEmpty() || listTwo.get(j + count).isEmpty())) {
                count++;
                countEmpty++;
            } else
                count++;
        }
        if (countEmpty > 1)
            return false;
        return true;
    }

    private int countMatches = 0;

    private boolean checkCoincidenceLines(int i, int j) {
        int count = 0;

        while (count < 3) {
            if (i + count < listOneSize && j + count < listTwoSize
                    && listOne.get(i + count).equals(listTwo.get(j + count))) {
                count++;
                if (countMatches >= 3)
                    countMatches = 3;
                else
                    countMatches++;
            } else
                return false;
        }
        if (i == listOneSize - 3 && j == listTwoSize - 3
                && listOne.get(listOneSize - 4).equals(listTwo.get(listTwoSize - 4)))
            countMatches = 0;
        return true;
    }

    private void addLinesAfterMismatch(int j) {
        int count = 0;
        int line = j;

        while (count < countMatches) {
            fileFinalMap.put(line, listTwo.get(line));
            count++;
            line++;
        }
        countMatches = 0;
    }

    private void addLinesBeforeMatching(int j) {
        int count = 0;
        int line = 0;
        if (countMatches == 3)
            line = (j + 1);
//        else if (countMatches == 2)
//            line = (j + 1);
//        else
//            line = j;

        if (countMatches == 3)
            while (count < countMatches) {
                if (line < listTwoSize)
                    fileFinalMap.put(line, listTwo.get(line));
                count++;
                line--;
            }
        countMatches = 0;
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
