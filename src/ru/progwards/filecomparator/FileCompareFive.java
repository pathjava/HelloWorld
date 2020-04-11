package ru.progwards.filecomparator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileCompareFive {
    private final List<String> listOne = new ArrayList<>();
    private final List<String> listTwo = new ArrayList<>();

    private int listOneSize;
    private int listTwoSize;
    private int realSizeListOne;
    private int realSizeListTwo;

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

    private final Map<Integer, String> fileFinalMap = new HashMap<>();

    public Map<Integer, String> compareFiles() {
        listOneSize = listOne.size();
        listTwoSize = listTwo.size();
        realSizeListOne = listSizeForTheLastThreeNonEmptyLines(listOne);
        realSizeListTwo = listSizeForTheLastThreeNonEmptyLines(listTwo);

        final int MAX_SIZE_ARRAY = Math.max(listOneSize, listTwoSize);
        for (int i = 0; i < MAX_SIZE_ARRAY; i++) {
            fileFinalMap.put(i, "#");
        }
        searchAnchorLines();
        return fileFinalMap;
    }

//    private int realSizeListTwo() {
//        int index = listTwoSize - 1;
//        int count = 0;
//        while (count != 3) {
//            if (index >= 0 && !listTwo.get(index).isEmpty())
//                count++;
//            else
//                count = 0;
//
//            if (index >= 0) index--;
//        }
//        return index + 4;
//    }
//
//    private int realSizeListOne() {
//        int index = listOneSize - 1;
//        int count = 0;
//        while (count != 3) {
//            if (index >= 0 && !listOne.get(index).isEmpty())
//                count++;
//            else
//                count = 0;
//
//            if (index >= 0) index--;
//        }
//        return index + 4;
//    }

    private void searchAnchorLines() {
        int i = 0;
        while (i < realSizeListOne - 2) {
            int j = 0;
            while (j < realSizeListTwo - 2) {
                i = searchThreeNonEmptyLinesListOne(i);
                j = searchThreeNonEmptyLinesListTwo(j);

                if (checkCoincidenceLines(i, j)) {
                    checkAndAddAnchors(i, j);
                    if (i < realSizeListOne - 2) i++;
                }
                if (j < realSizeListTwo - 2) j++;
            }
            if (i < realSizeListOne - 2) i++;
        }
    }

    private int listSizeForTheLastThreeNonEmptyLines(List<String> list) {
        int index = list.size() - 1;
        int count = 0;
        while (count != 3) {
            if (index >= 0 && !list.get(index).isEmpty())
                count++;
            else
                count = 0;

            if (index >= 0) index--;
        }
        return index + 4;
    }

    private int searchThreeNonEmptyLinesListOne(int i) {
        if (i > realSizeListOne - 3)
            return i - 1;
        int index = i;
        int count = 0;

        while (count != 3) {
            if (index < realSizeListOne && !listOne.get(index).isEmpty())
                count++;
            else
                count = 0;

            if (index + 1 <= realSizeListOne) index++;
        }
        return index - 3;
    }

    private int searchThreeNonEmptyLinesListTwo(int j) {
        if (j > realSizeListTwo - 3)
            return j - 1;
        int index = j;
        int count = 0;

        while (count != 3) {
            if (index < realSizeListTwo && !listTwo.get(index).isEmpty())
                count++;
            else
                count = 0;

            if (index + 1 <= realSizeListTwo) index++;
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
            addAnchors(j);
        }
        if ((i == 0 && j == 0) || (i == 0 && j > 0) || (i > 0 && j == 0)) {
            if (checkNextLines(i, j))
                addAnchors(j);
        }
        if ((i > 0 && j > 0) && (i < listOneSize - 3 && j < listTwoSize - 3)) {
            if (checkPrevLines(i, j))
                addAnchors(j);
            if (checkNextLines(i, j))
                addAnchors(j);
        }
        if ((i == listOneSize - 3 && j == listTwoSize - 3) || (i == listOneSize - 3 && j < listTwoSize - 3) || (i < listOneSize - 3 && j == listTwoSize - 3)) {
            if (checkPrevLines(i, j))
                addAnchors(j);
        }
        if ((i == listOneSize - 3 && j < listTwoSize - 3) || (i < listOneSize - 3 && j == listTwoSize - 3)) {
            addAnchors(j);
        }
    }

    private boolean checkPrevLines(int i, int j) {
        int indexOne = i - 1;
        int indexTwo = j - 1;

        if (listOne.get(indexOne).equals(listTwo.get(indexTwo)) && !listOne.get(indexOne).isEmpty() && !listTwo.get(indexTwo).isEmpty())
            return false;
        if (!listOne.get(indexOne).equals(listTwo.get(indexTwo)))
            return true;
        if (listOne.get(indexOne).isEmpty() && listTwo.get(indexTwo).isEmpty()) {
            int count = 0;
            if (indexOne > 0) indexOne--;
            if (indexTwo > 0) indexTwo--;
            while (count != 3) {
                if (listOne.get(indexOne).equals(listTwo.get(indexTwo)) && !listOne.get(indexOne).isEmpty() && !listTwo.get(indexTwo).isEmpty())
                    count++;
                else
                    count = 0;
                if (!listOne.get(indexOne).equals(listTwo.get(indexTwo)))
                    return true;
                else if (indexOne == 0 || indexTwo == 0)
                    return false;
                else {
                    indexOne--;
                    indexTwo--;
                }
            }
        }
        return false;
    }

    private boolean checkNextLines(int i, int j) {
        int indexOne = i + 3;
        int indexTwo = j + 3;

        if (listOne.get(indexOne).equals(listTwo.get(indexTwo)) && !listOne.get(indexOne).isEmpty() && !listTwo.get(indexTwo).isEmpty())
            return false;
        if (!listOne.get(indexOne).equals(listTwo.get(indexTwo)))
            return true;
        if (listOne.get(indexOne).isEmpty() && listTwo.get(indexTwo).isEmpty()) {
            int count = 0;
            if (indexOne < listOneSize - 1) indexOne++;
            if (indexTwo < listTwoSize - 1) indexTwo++;
            while (count != 3) {
                if (indexOne == listOneSize - 1 && indexTwo < listTwoSize - 1 || indexOne < listOneSize - 1 && indexTwo == listTwoSize - 1)
                    return true;
                if (listOne.get(indexOne).equals(listTwo.get(indexTwo)) && !listOne.get(indexOne).isEmpty() && !listTwo.get(indexTwo).isEmpty())
                    count++;
                else
                    count = 0;
                if (!listOne.get(indexOne).equals(listTwo.get(indexTwo)))
                    return true;
                else if (indexOne == listOneSize - 1 || indexTwo == listTwoSize - 1)
                    return false;
                else {
                    indexOne++;
                    indexTwo++;
                }
            }
        }
        return false;
    }

    private void addAnchors(int j) {
        int count = 0;
        while (count < 3) {
            fileFinalMap.put(j + count, listTwo.get(j + count));
            count++;
        }
    }


    public static void main(String[] args) {
        FileCompareFive test = new FileCompareFive();
        test.readFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\01.txt",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\02.txt");

        System.out.println("------------ Patch -------------");
        for (Map.Entry<Integer, String> entry : test.compareFiles().entrySet()) {
            System.out.format("%3d", entry.getKey());
            System.out.println(": " + entry.getValue());
        }
    }
}
