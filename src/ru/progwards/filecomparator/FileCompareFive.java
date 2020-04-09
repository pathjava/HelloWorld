package ru.progwards.filecomparator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileCompareFive {
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

    private int realSizeListTwo() {
        int index = listTwoSize - 1;
        while (index >= 0) {
            if (listTwo.get(index).isEmpty())
                index--;
            else
                return index;
        }
        return index + 1;
    }

    private int realSizeListOne() {
        int index = listOneSize - 1;
        while (index >= 0) {
            if (listOne.get(index).isEmpty())
                index--;
            else
                return index;
        }
        return index + 1;
    }

    private void searchAnchorLines() {
        int i = 0;
        while (i < realSizeListOne() - 3) {
            int j = 0;
            while (j < realSizeListTwo() - 3) {
                i = searchThreeNonEmptyLinesListOne(i);
                j = searchThreeNonEmptyLinesListTwo(j);

                if (checkCoincidenceLines(i, j)) {
                    checkAndAddAnchors(i, j);
                    if (i + 1 < realSizeListOne() - 2) i++;
                }
                if (j + 1 < realSizeListTwo() - 2) j++;
            }
            if (i + 1 < realSizeListOne() - 2) i++;
        }
    }

    private int searchThreeNonEmptyLinesListOne(int i) {
        int index = i;
        int count = 0;

        while (count != 3) {
            if (index < realSizeListOne() && !listOne.get(index).isEmpty())
                count++;
            else
                count = 0;

            if (index + 1 <= realSizeListOne()) index++;
        }
        return index - 3;
    }

    private int searchThreeNonEmptyLinesListTwo(int j) {
        int index = j;
        int count = 0;

        while (count != 3) {
            if (index < realSizeListTwo() && !listTwo.get(index).isEmpty())
                count++;
            else
                count = 0;

            if (index + 1 <= realSizeListTwo()) index++;
        }
        return index - 3;
    }

    private boolean checkCoincidenceLines(int i, int j) {
        int count = 0;

        while (count < 3) {
            if (i + count < listOneSize && j + count < listTwoSize
                    && listOne.get(i + count).equals(listTwo.get(j + count))) {
                count++;
            } else
                return false;
        }
        return true;
    }

    private void checkAndAddAnchors(int i, int j) {

        if ((i != 0 && j == 0) || (i == 0 && j != 0)) {
            addAnchors(i, j);
        }
        if ((i == 0 && j == 0) || (i == 0 && j > 0) || (i > 0 && j == 0)) {
            if (!listOne.get(i + 3).equals(listTwo.get(j + 3))) {
                addAnchors(i, j);
            }
        }
        if ((i > 0 && j > 0) && (i < listOneSize - 3 && j < listTwoSize - 3)) {
            if (!listOne.get(i - 1).equals(listTwo.get(j - 1))) {
                addAnchors(i, j);
            }
            if (!listOne.get(i + 3).equals(listTwo.get(j + 3))) {
                addAnchors(i, j);
            }
        }
        if ((i == listOneSize - 3 && j == listTwoSize - 3) || (i == listOneSize - 3 && j < listTwoSize - 3) || (i < listOneSize - 3 && j == listTwoSize - 3)) {
            if (!listOne.get(i - 1).equals(listTwo.get(j - 1))) {
                addAnchors(i, j);
            }
        }
        if ((i == listOneSize - 3 && j < listTwoSize - 3) || (i < listOneSize - 3 && j == listTwoSize - 3)) {
            addAnchors(i, j);
        }
    }

    private void addAnchors(int i, int j) {
        int count = 0;
        while (count < 3) {
            fileFinalMap.put(j + count, listTwo.get(j + count));
            count++;
        }
    }


    public static void main(String[] args) {
        FileCompareFive test = new FileCompareFive();
        test.readFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\08.txt",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\07.txt");

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
