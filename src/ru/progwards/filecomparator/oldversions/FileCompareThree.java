package ru.progwards.filecomparator.oldversions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileCompareThree {
    private List<String> listOne = new ArrayList<>();
    private List<String> listTwo = new ArrayList<>();
    public FileCompareAnchors fileDifferentDate;

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

    private Map<Integer, FileCompareAnchors> fileFinalMap = new HashMap<>();

    public Map<Integer, FileCompareAnchors> compareFiles() {
        final int MAX_SIZE_ARRAY = Math.max(listOne.size(), listTwo.size());
        fileDifferentDate = new FileCompareAnchors();
        for (int i = 1; i < MAX_SIZE_ARRAY; i++) {
            fileDifferentDate.lineFromFile = "+";
            fileFinalMap.put(i, fileDifferentDate);
        }
        searchAnchorForward();
        searchAnchorBack();
        return fileFinalMap;
    }

    private void searchAnchorForward() {
        for (int i = 0; i < listOne.size(); i++)
            for (int j = 0; j < listTwo.size(); j++) {
                if (i < listTwo.size() && j < i)
                    j = i - 1;
                if (!(listOne.get(i).equals(listTwo.get(j)))) {
                    if (equalsThreeLinesForward(i, j)) {
                        addLinesForward(i, j);
                        i++;
                        j++;
                    }
                } else {
                    if (i < listOne.size() - 1) i++;
                }
            }
    }

    private boolean equalsThreeLinesForward(int one, int two) {
        int countOne = 0;
        int countTwo = 1;
        int i = 0;
        while (i <= 2) {
            if ((one + countOne) < listOne.size() && (two + countTwo) < listTwo.size())
                if (listOne.get(one + countOne).equals(listTwo.get(two + countTwo))) {
                    countOne++;
                    countTwo++;
                } else
                    return false;
            else
                return false;
            i++;
        }
        return true;

//        StringBuilder stringOne = new StringBuilder();
//        StringBuilder stringTwo = new StringBuilder();
//        int countOne = 0;
//        int countTwo = 1;
//        int i = 0;
//        while (i <= 2) {
//            if ((one + countOne) < listOne.size() && (two + countTwo) < listTwo.size()) {
//                stringOne.append(listOne.get(one + countOne));
//                stringTwo.append(listTwo.get(two + countTwo));
//                countOne++;
//                countTwo++;
//            }
//            else
//                return false;
//            i++;
//        }
//        return stringOne.toString().equals(stringTwo.toString());
    }

    private void addLinesForward(int iForward, int jForward) {
        int countOne = 0;
        int countTwo = 1;
        int i = 0;
        while (i <= 2) {
            fileDifferentDate = new FileCompareAnchors();
            fileDifferentDate.lineFromFile = listOne.get(iForward + countOne);
            fileDifferentDate.startLineBefore = iForward + countOne + 1;
            fileFinalMap.put(jForward + countTwo + 1, fileDifferentDate);
            countOne++;
            countTwo++;
            i++;
        }
    }

    private void searchAnchorBack() {
        for (int i = listOne.size() - 1; i >= 0; i--)
            for (int j = listTwo.size() - 1; j >= 0; j--)
                if (!(listOne.get(i).equals(listTwo.get(j)))) {
                    if (equalsThreeLinesBack(i, j)) {
                        addLinesBack(i, j);
                        i--;
                        j--;
                    }
                } else {
                    if (i >= 1) i--;
                }
    }

    private boolean equalsThreeLinesBack(int one, int two) {
        int countOne = 0;
        int countTwo = 1;
        int i = 0;
        while (i <= 2) {
            if ((one - countOne) >= 0 && (two - countTwo) >= 0)
                if (listOne.get(one - countOne).equals(listTwo.get(two - countTwo))) {
                    countOne++;
                    countTwo++;
                } else
                    return false;
            else
                return false;
            i++;
        }
        return true;

//        StringBuilder stringOne = new StringBuilder();
//        StringBuilder stringTwo = new StringBuilder();
//        int countOne = 0;
//        int countTwo = 1;
//        int i = 0;
//        while (i <= 2) {
//            if ((one - countOne) >= 0 && (two - countTwo) >= 0) {
//                stringOne.append(listOne.get(one - countOne));
//                stringTwo.append(listTwo.get(two - countTwo));
//                countOne++;
//                countTwo++;
//            } else
//                return false;
//            i++;
//        }
//        return stringOne.toString().equals(stringTwo.toString());
    }

    private void addLinesBack(int iBack, int jBack) {
        int countOne = 0;
        int countTwo = 1;
        int i = 0;
        while (i <= 2) {
            fileDifferentDate = new FileCompareAnchors();
            fileDifferentDate.lineFromFile = listOne.get(iBack - countOne);
            fileDifferentDate.finishLineBefore = iBack - countOne + 1;
            fileFinalMap.put(jBack - countTwo + 1, fileDifferentDate);
            countOne++;
            countTwo++;
            i++;
        }
    }


    public static void main(String[] args) {
        FileCompareThree test = new FileCompareThree();
        test.readFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\05.txt",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\06.txt");

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
        for (Map.Entry<Integer, FileCompareAnchors> entry : test.compareFiles().entrySet()) {
            System.out.format("%3d", entry.getKey());
            System.out.println(": " + entry.getValue());
        }
    }
}
