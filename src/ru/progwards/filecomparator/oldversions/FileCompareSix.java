package ru.progwards.filecomparator.oldversions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileCompareSix {
    private final List<String> listOne = new ArrayList<>();
    private final List<String> listTwo = new ArrayList<>();

    private int listOneSize;
    private int listTwoSize;
    private int realSizeListOne;
    private int realSizeListTwo;
    // свойства класса, используемые при проверке больших и маленьких файлов
    private int oneOrThree = 3;
    private int zeroOrTwo = 2;
    private int twoOrFour = 4;

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

    private final Map<Integer, String> fileFinalMap = new HashMap<>();

    public Map<Integer, String> compareFiles() {
        listOneSize = listOne.size(); // так как размеры листов часто используются, присваиваем их в переменные
        listTwoSize = listTwo.size();

        // создаем HashMap по размеру наибольшего из двух листов
        final int MAX_SIZE_ARRAY = Math.max(listOneSize, listTwoSize);
        for (int i = 0; i < MAX_SIZE_ARRAY; i++) {
            fileFinalMap.put(i, "#");
        }
        // если самый большой из листов меньше 6, используем меньшие значения (вместо 3, 2, 4)
        if (MAX_SIZE_ARRAY < 6) {
            oneOrThree = 1;
            zeroOrTwo = 0;
            twoOrFour = 2;
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
                i = searchThreeNonEmptyLines(i, listOne, realSizeListOne); // определяем ближайшие три строки подряд
                j = searchThreeNonEmptyLines(j, listTwo, realSizeListTwo);
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
        while (count != oneOrThree) {
            if (index >= 0 && !list.get(index).isEmpty())
                count++;
            else
                count = 0;

            if (index >= 0) index--;
        }
        return index + twoOrFour;
    }

    // поиск первого трехстрочия в каждом из листов
    private int searchThreeNonEmptyLines(int i, List<String> list, int realSizeList) {
        if (i > realSizeList - oneOrThree)
            return i - 1;
        int index = i;
        int count = 0;

        while (count != oneOrThree) {
            if (index < realSizeList && !list.get(index).isEmpty())
                count++;
            else
                count = 0;

            if (index + 1 <= realSizeList) index++;
        }
        return index - oneOrThree;
    }

    // проверка на равенство трехстрочий
    private boolean checkCoincidenceLines(int i, int j) {
        int count = 0;

        while (count < oneOrThree) {
            if (i + count < listOneSize && j + count < listTwoSize
                    && listOne.get(i + count).equals(listTwo.get(j + count))) {
                count++;
            } else
                return false;
        }
        if (count == oneOrThree)
            lastCoincidence = j;
        return true;
    }

    // проверка трехстрочий на окружение - строки выше и ниже по листам
    private void checkAndAddAnchors(int i, int j) {
        if ((i != 0 && j == 0) || (i == 0 && j != 0))
            addAnchors(j);
        if ((i == 0 && j == 0) || (i == 0 && j > 0) || (i > 0 && j == 0))
            if (checkNextLines(i, j))
                addAnchors(j);
        if ((i > 0 && j > 0) && (i < listOneSize - oneOrThree && j < listTwoSize - oneOrThree)) {
            if (checkPrevLines(i, j))
                addAnchors(j);
            if (checkNextLines(i, j))
                addAnchors(j);
        }
        if ((i == listOneSize - oneOrThree && j == listTwoSize - oneOrThree)
                || (i == listOneSize - oneOrThree && j < listTwoSize - oneOrThree)
                || (i < listOneSize - oneOrThree && j == listTwoSize - oneOrThree))
            if (checkPrevLines(i, j))
                addAnchors(j);
        if ((i == listOneSize - oneOrThree && j < listTwoSize - oneOrThree)
                || (i < listOneSize - oneOrThree && j == listTwoSize - oneOrThree))
            addAnchors(j);
    }

    // проверка строк в листе перед трехстрочием
    private boolean checkPrevLines(int i, int j) {
        int indexOne = i - 1;
        int indexTwo = j - 1;

        if (listOne.get(indexOne).equals(listTwo.get(indexTwo))
                && !listOne.get(indexOne).isEmpty() && !listTwo.get(indexTwo).isEmpty())
            return false;
        if (!listOne.get(indexOne).equals(listTwo.get(indexTwo)))
            return true;
        if (listOne.get(indexOne).isEmpty() && listTwo.get(indexTwo).isEmpty()) {
            int count = 0;
            if (indexOne > 0) indexOne--;
            if (indexTwo > 0) indexTwo--;
            while (count != oneOrThree) {
                if (listOne.get(indexOne).equals(listTwo.get(indexTwo))
                        && !listOne.get(indexOne).isEmpty() && !listTwo.get(indexTwo).isEmpty())
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

    // проверка строк в листе после трехстрочия
    private boolean checkNextLines(int i, int j) {
        int indexOne = i + oneOrThree;
        int indexTwo = j + oneOrThree;

        if (listOne.get(indexOne).equals(listTwo.get(indexTwo))
                && !listOne.get(indexOne).isEmpty() && !listTwo.get(indexTwo).isEmpty())
            return false;
        if (!listOne.get(indexOne).equals(listTwo.get(indexTwo)))
            return true;
        if (listOne.get(indexOne).isEmpty() && listTwo.get(indexTwo).isEmpty()) {
            int count = 0;
            if (indexOne < listOneSize - 1) indexOne++;
            if (indexTwo < listTwoSize - 1) indexTwo++;
            while (count != oneOrThree) {
                if (indexOne == listOneSize - 1 && indexTwo < listTwoSize - 1
                        || indexOne < listOneSize - 1 && indexTwo == listTwoSize - 1)
                    return true;

                if (listOne.get(indexOne).equals(listTwo.get(indexTwo))
                        && !listOne.get(indexOne).isEmpty() && !listTwo.get(indexTwo).isEmpty())
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

    // добавление трехстрочия в HashMap
    private void addAnchors(int j) {
        int count = 0;
        while (count < oneOrThree) {
            fileFinalMap.put(j + count, listTwo.get(j + count));
            count++;
        }
    }


    public static void main(String[] args) {
        FileCompareSix test = new FileCompareSix();
        test.readFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\06.txt",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\05.txt");

        System.out.println("------------ Patch -------------");
        for (Map.Entry<Integer, String> entry : test.compareFiles().entrySet()) {
            System.out.format("%3d", entry.getKey());
            System.out.println(": " + entry.getValue());
        }
    }
}
