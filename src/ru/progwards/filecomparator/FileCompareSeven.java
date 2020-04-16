package ru.progwards.filecomparator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileCompareSeven {
    private final List<String> listOne = new ArrayList<>();
    private final List<String> listTwo = new ArrayList<>();

    private FileAnchors fileAnchors;

    private int listOneSize;
    private int listTwoSize;
    private int realSizeListOne;
    private int realSizeListTwo;
    // свойства класса, используемые при проверке больших и маленьких файлов
    private int oneTwoThree = 3;
    private int zeroOrTwo = 2;
    private int twoThreeFour = 4;
    private int zeroOneTwo = 2;

    // считываем построчно два файла и перегоняем в два ArrayList
    public void readFiles(String pathOne, String pathTwo) {
        try (BufferedReader readerOne = new BufferedReader(new FileReader(pathOne))) {
            String lineOne;
            while ((lineOne = readerOne.readLine()) != null) {
                listOne.add(lineOne);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не выбран файл 1!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader readerTwo = new BufferedReader(new FileReader(pathTwo))) {
            String lineTwo;
            while ((lineTwo = readerTwo.readLine()) != null) {
                listTwo.add(lineTwo);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не выбран файл 2!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final Map<Integer, FileAnchors> fileFinalMap = new HashMap<>();

    public Map<Integer, FileAnchors> compareFiles() {
        listOneSize = listOne.size(); // так как размеры листов часто используются, присваиваем их в переменные
        listTwoSize = listTwo.size();

        // создаем HashMap по размеру наибольшего из двух листов
        int maxBothListsSize = Math.max(listOneSize, listTwoSize);

        fileAnchors = new FileAnchors();
        for (int i = 0; i < maxBothListsSize; i++) {
            fileFinalMap.put(i, fileAnchors);
        }
        // если самый большой из листов меньше 6, используем меньшие значения (вместо 3, 2, 4)
        if (maxBothListsSize < 6) {
            oneTwoThree = 1;
            zeroOrTwo = 0;
            twoThreeFour = 2;
            zeroOneTwo = 0;
        } else if (maxBothListsSize < 11) {
            oneTwoThree = 2;
            zeroOrTwo = 1;
            twoThreeFour = 3;
            zeroOneTwo = 1;
        }
        // присваиваем размеры листов по последним трем идущим подряд строкам
        realSizeListOne = listSizeForTheLastThreeNonEmptyLines(listOne);
        realSizeListTwo = listSizeForTheLastThreeNonEmptyLines(listTwo);

        searchAnchorLines();
        return fileFinalMap;
    }

    private int lastCoincidence = 0; // переменная для хранения индукса последнего совпадения трех строк

    private void searchAnchorLines() {
        int i = 0;
        while (i < realSizeListOne - zeroOrTwo) {
            int j = 0;
            while (j < realSizeListTwo - zeroOrTwo) {
                i = searchFirstThreeNonEmptyLines(i, listOne, realSizeListOne); // определяем ближайшие три строки подряд
                j = searchFirstThreeNonEmptyLines(j, listTwo, realSizeListTwo);
                if (j < lastCoincidence) // чтобы избежать повторного поиска с самого начала, присваиваем индекс последнего совпадения
                    j = lastCoincidence;
                // проверяем трехстрочия на равенство
                if (checkCoincidenceLines(i, j)) {
                    checkAndAddAnchors(i, j); // если совпали, проверяем строки выше/ниже на равенство и добавляем трехстрочие
                    if (i < realSizeListOne - zeroOrTwo) i++;
                }
                if (j < realSizeListTwo - zeroOrTwo) j++;
            }
            if (i < realSizeListOne - zeroOrTwo) i++;
        }
    }

    // поиск последнего трехстрочия в каждом из листов
    private int listSizeForTheLastThreeNonEmptyLines(List<String> list) {
        int index = list.size() - 1;
        int count = 0;
        while (count != oneTwoThree) {
            if (index >= 0 && !list.get(index).isEmpty())
                count++;
            else
                count = 0;

            if (index >= 0) index--;
        }
        return index + twoThreeFour;
    }

    // поиск первого трехстрочия в каждом из листов
    private int searchFirstThreeNonEmptyLines(int i, List<String> list, int realSizeList) {
        if (i > realSizeList - oneTwoThree)
            return i - 1;
        int index = i;
        int count = 0;

        while (count != oneTwoThree) {
            if (index < realSizeList && !list.get(index).isEmpty())
                count++;
            else
                count = 0;

            if (index + 1 <= realSizeList) index++;
        }
        return index - oneTwoThree;
    }

    // проверка на равенство трехстрочий
    private boolean checkCoincidenceLines(int i, int j) {
        int count = 0;

        while (count < oneTwoThree) {
            if (i + count < listOneSize && j + count < listTwoSize
                    && listOne.get(i + count).equals(listTwo.get(j + count))) {
                count++;
            } else
                return false;
        }
        if (count == oneTwoThree)
            lastCoincidence = j;
        return true;
    }

    // проверка трехстрочий на окружение - строки выше и ниже по листам
    private void checkAndAddAnchors(int i, int j) {
        if ((i != 0 && j == 0) || (i == 0 && j != 0)) {
            setTemporaryIndex(i + oneTwoThree, j + oneTwoThree, "finish");
            addAnchors(j);
        }
        if ((i == 0 && j == 0) || (i == 0 && j > 0) || (i > 0 && j == 0))
            if (checkNextLines(i, j)) {
                setTemporaryIndex(i + 1, j + 1, "start");
                addAnchors(j);
            }
        if ((i > 0 && j > 0) && (i < listOneSize - oneTwoThree && j < listTwoSize - oneTwoThree)) {
            if (checkPrevLines(i, j)) {
                setTemporaryIndex(i + oneTwoThree, j + oneTwoThree, "finish");
                addAnchors(j);
            }
            if (checkNextLines(i, j)) {
                setTemporaryIndex(i + 1, j + 1, "start");
                addAnchors(j);
            }
        }
        if ((i == listOneSize - oneTwoThree && j == listTwoSize - oneTwoThree)
                || (i == listOneSize - oneTwoThree && j < listTwoSize - oneTwoThree)
                || (i < listOneSize - oneTwoThree && j == listTwoSize - oneTwoThree))
            if (checkPrevLines(i, j)) {
                setTemporaryIndex(i + oneTwoThree, j + oneTwoThree, "finish");
                addAnchors(j);
            }
        if ((i == listOneSize - oneTwoThree && j < listTwoSize - oneTwoThree)
                || (i < listOneSize - oneTwoThree && j == listTwoSize - oneTwoThree)) {
            setTemporaryIndex(i + 1, j + 1, "start");
            addAnchors(j);
        }
    }

    // проверка строк в листе перед трехстрочием
    private boolean checkPrevLines(int i, int j) {
        int indexOne = i - 1;
        int indexTwo = j - 1;

        if (checkPrevNextFirstLine(indexOne, indexTwo))
            return false;
        if (!listOne.get(indexOne).equals(listTwo.get(indexTwo)))
            return true;
        if (listOne.get(indexOne).isEmpty() && listTwo.get(indexTwo).isEmpty()) {
            int count = 0;
            if (indexOne > 0) indexOne--;
            if (indexTwo > 0) indexTwo--;
            while (count != oneTwoThree) {
                count = incrementCountWhenCheckingLines(indexOne, indexTwo, count);

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

    // проверка строк в листе после трехстрочия
    private boolean checkNextLines(int i, int j) {
        int indexOne = i + oneTwoThree;
        int indexTwo = j + oneTwoThree;

        if (checkPrevNextFirstLine(indexOne, indexTwo))
            return false;
        if (!listOne.get(indexOne).equals(listTwo.get(indexTwo)))
            return true;
        if (listOne.get(indexOne).isEmpty() && listTwo.get(indexTwo).isEmpty()) {
            int count = 0;
            if (indexOne < listOneSize - 1) indexOne++;
            if (indexTwo < listTwoSize - 1) indexTwo++;
            while (count != oneTwoThree) {
                if (indexOne == listOneSize - 1 && indexTwo < listTwoSize - 1
                        || indexOne < listOneSize - 1 && indexTwo == listTwoSize - 1)
                    return true;

                count = incrementCountWhenCheckingLines(indexOne, indexTwo, count);

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

    private boolean checkPrevNextFirstLine(int indexOne, int indexTwo) {
        return listOne.get(indexOne).equals(listTwo.get(indexTwo))
                && !listOne.get(indexOne).isEmpty() && !listTwo.get(indexTwo).isEmpty();
    }

    private int incrementCountWhenCheckingLines(int indexOne, int indexTwo, int count) {
        if (listOne.get(indexOne).equals(listTwo.get(indexTwo))
                && !listOne.get(indexOne).isEmpty() && !listTwo.get(indexTwo).isEmpty())
            count++;
        else
            count = 0;
        return count;
    }

    // листы и метод для временного хранения индексов троестрочий
    private List<String> oneIndex;
    private List<String> twoIndex;

    private void setTemporaryIndex(int i, int j, String startFinish) {
        oneIndex = new ArrayList<>();
        twoIndex = new ArrayList<>();
        switch (startFinish) {
            case "finish":
                oneIndex.add(String.valueOf(i));
                oneIndex.add(String.valueOf(j));
                oneIndex.add(startFinish);
                break;
            case "start":
                twoIndex.add(String.valueOf(i));
                twoIndex.add(String.valueOf(j));
                twoIndex.add(startFinish);
                break;
        }
    }

    // добавление объекта fileAnchors с трехстрочием и индексами в HashMap
    private void addAnchors(int j) {
        int count = 0;
        while (count < oneTwoThree) {
            fileAnchors = new FileAnchors();

            if (fileFinalMap.get(j + count).finish.contains("finish")) {
                fileAnchors.finish = "finish";
                fileAnchors.finishOneIndex = fileFinalMap.get(j + count).finishOneIndex;
                fileAnchors.finishTwoIndex = fileFinalMap.get(j + count).finishTwoIndex;
            } else if (fileFinalMap.get(j + count).start.contains("start")) {
                fileAnchors.start = "start";
                fileAnchors.startOneIndex = fileFinalMap.get(j + count).startOneIndex;
                fileAnchors.startTwoIndex = fileFinalMap.get(j + count).startTwoIndex;
            }

            if (!oneIndex.isEmpty() && count == zeroOneTwo) {
                fileAnchors.finishOneIndex = oneIndex.get(0);
                fileAnchors.finishTwoIndex = oneIndex.get(1);
                fileAnchors.finish = oneIndex.get(2);
            } else if (!twoIndex.isEmpty() && count == 0) {
                fileAnchors.startOneIndex = twoIndex.get(0);
                fileAnchors.startTwoIndex = twoIndex.get(1);
                fileAnchors.start = twoIndex.get(2);
            }

            fileAnchors.anchorsLines = listTwo.get(j + count);
            fileFinalMap.put(j + count, fileAnchors);
            count++;
        }
    }


    public static void main(String[] args) {
        FileCompareSeven test = new FileCompareSeven();
        test.readFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\07.txt",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\08.txt");

        System.out.println("------------ Patch -------------");
        for (Map.Entry<Integer, FileAnchors> entry : test.compareFiles().entrySet()) {
            System.out.format("%3d", entry.getKey());
            System.out.println(": " + entry.getValue());
        }
    }
}