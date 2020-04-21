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

    private final String START_LINE = "start";
    private final String STOP_LINE = "stop";

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

    //========================= поиск трехстрочных анкоров =====================

    private final Map<Integer, FileAnchors> mapLinesAnchors = new HashMap<>();

    public Map<Integer, FileAnchors> compareFiles() {
        listOneSize = listOne.size(); // так как размеры листов часто используются, присваиваем их в переменные
        listTwoSize = listTwo.size();

        // создаем HashMap по размеру наибольшего из двух листов
        int maxBothListsSize = Math.max(listOneSize, listTwoSize);

        fileAnchors = new FileAnchors();
        for (int i = 0; i < maxBothListsSize; i++) {
            mapLinesAnchors.put(i, fileAnchors);
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
        setFirstAnchorNumberLine();
        setLastAnchorNumberLine();
        return mapLinesAnchors;
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
            setAnchorNumberLine(i + oneTwoThree, j + oneTwoThree, STOP_LINE);
            addAnchorsToHashMap(j);
        }
        if ((i == 0 && j == 0) || (i == 0 && j > 0) || (i > 0 && j == 0))
            if (checkNextLines(i, j)) {
                setAnchorNumberLine(i + 1, j + 1, START_LINE);
                addAnchorsToHashMap(j);
            }
        if ((i > 0 && j > 0) && (i < listOneSize - oneTwoThree && j < listTwoSize - oneTwoThree)) {
            if (checkPrevLines(i, j)) {
                setAnchorNumberLine(i + oneTwoThree, j + oneTwoThree, STOP_LINE);
                addAnchorsToHashMap(j);
            }
            if (checkNextLines(i, j)) {
                setAnchorNumberLine(i + 1, j + 1, START_LINE);
                addAnchorsToHashMap(j);
            }
        }
        if ((i == listOneSize - oneTwoThree && j == listTwoSize - oneTwoThree)
                || (i == listOneSize - oneTwoThree && j < listTwoSize - oneTwoThree)
                || (i < listOneSize - oneTwoThree && j == listTwoSize - oneTwoThree))
            if (checkPrevLines(i, j)) {
                setAnchorNumberLine(i + oneTwoThree, j + oneTwoThree, STOP_LINE);
                addAnchorsToHashMap(j);
            }
        if ((i == listOneSize - oneTwoThree && j < listTwoSize - oneTwoThree)
                || (i < listOneSize - oneTwoThree && j == listTwoSize - oneTwoThree)) {
            setAnchorNumberLine(i + 1, j + 1, START_LINE);
            addAnchorsToHashMap(j);
        }
    }

    // проверка строк в листе перед трехстрочием
    private boolean checkPrevLines(int i, int j) {
        int indexOne = i - 1;
        int indexTwo = j - 1;

        if (checkPrevNextFirstLine(indexOne, indexTwo)) // если строки -1 равны, возвращаем false
            return false;
        if (!listOne.get(indexOne).equals(listTwo.get(indexTwo))) // если строки -1 равны, возвращаем true
            return true;
        // если строки -1 пустые, ищем не пустые и сравниваем их
        if (listOne.get(indexOne).isEmpty() && listTwo.get(indexTwo).isEmpty()) {
            int count = 0;
            if (indexOne > 0) indexOne--;
            if (indexTwo > 0) indexTwo--;
            while (count != oneTwoThree) {
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

    // проверка равенства предыдущей (не пустой) строки трехстрочий
    private boolean checkPrevNextFirstLine(int indexOne, int indexTwo) {
        return listOne.get(indexOne).equals(listTwo.get(indexTwo))
                && !listOne.get(indexOne).isEmpty() && !listTwo.get(indexTwo).isEmpty();
    }

    // инкремент count при обнаружении совпадения строк идущих подряд
//    private int incrementCountWhenCheckingLines(int indexOne, int indexTwo, int count) {
//        if (listOne.get(indexOne).equals(listTwo.get(indexTwo))
//                && !listOne.get(indexOne).isEmpty() && !listTwo.get(indexTwo).isEmpty())
//            count++;
//        else
//            count = 0;
//        return count;
//    }

    // листы и метод для временного хранения индексов троестрочий
    private List<String> oneListNumberLine;
    private List<String> twoListNumberLine;

    // добавление во временные листы номеров строк трохстрочий для добавления в объект fileAnchors
    private void setAnchorNumberLine(int i, int j, String startStop) {
        oneListNumberLine = new ArrayList<>();
        twoListNumberLine = new ArrayList<>();
        switch (startStop) {
            case "stop":
                oneListNumberLine.add(String.valueOf(i));
                oneListNumberLine.add(String.valueOf(j));
                oneListNumberLine.add(startStop);
                break;
            case "start":
                twoListNumberLine.add(String.valueOf(i));
                twoListNumberLine.add(String.valueOf(j));
                twoListNumberLine.add(startStop);
                break;
        }
    }

    // добавление объекта fileAnchors с трехстрочием и индексами в HashMap
    private void addAnchorsToHashMap(int j) {
        int count = 0;
        while (count < oneTwoThree) {
            fileAnchors = new FileAnchors();
            // защита от удаления данных при перезаписи строк анкоров в объект
            // так как анкор записывается при нахождении изменений перед ним, а потом после него
            if (mapLinesAnchors.get(j + count).stop.contains(STOP_LINE)) {
                fileAnchors.stop = STOP_LINE;
                fileAnchors.stopOneNumber = mapLinesAnchors.get(j + count).stopOneNumber;
                fileAnchors.stopTwoNumber = mapLinesAnchors.get(j + count).stopTwoNumber;
            } else if (mapLinesAnchors.get(j + count).start.contains(START_LINE)) {
                fileAnchors.start = START_LINE;
                fileAnchors.startOneNumber = mapLinesAnchors.get(j + count).startOneNumber;
                fileAnchors.startTwoNumber = mapLinesAnchors.get(j + count).startTwoNumber;
            }
            // запись start и stop, а также номеров строк анкоров из двух листов
            if (!oneListNumberLine.isEmpty() && count == zeroOneTwo) {
                fileAnchors.stopOneNumber = oneListNumberLine.get(0);
                fileAnchors.stopTwoNumber = oneListNumberLine.get(1);
                fileAnchors.stop = oneListNumberLine.get(2);
            } else if (!twoListNumberLine.isEmpty() && count == 0) {
                fileAnchors.startOneNumber = twoListNumberLine.get(0);
                fileAnchors.startTwoNumber = twoListNumberLine.get(1);
                fileAnchors.start = twoListNumberLine.get(2);
            }

            fileAnchors.lineIsAnchor = "is";
            fileAnchors.anchorsLines = listTwo.get(j + count);
            mapLinesAnchors.put(j + count, fileAnchors);
            count++;
        }
    }

    // добавление в объект fileAnchors номеров первых строк, если в первых 3-х строках есть изменения
    // поиск изменений идет по первым четырем строкам (0,1,2,3), но если анкор записан ранее, он не перезаписывается
    private void setFirstAnchorNumberLine() {
        fileAnchors = new FileAnchors();
        int count = 0;
        while (count <= oneTwoThree) {
            if (!mapLinesAnchors.get(0).start.isEmpty()) // если объект содержит start, то цикл прерываем
                break;
            else if (listOne.get(count).equals(listTwo.get(count))) // если строки равные, проверяем следующие
                count++;
            else { // иначе устанавливаем start и номера строк
                fileAnchors.startOneNumber = String.valueOf(1);
                fileAnchors.startTwoNumber = String.valueOf(1);
                fileAnchors.start = START_LINE;
                mapLinesAnchors.put(0, fileAnchors);
                break;
            }
        }
    }

    // добавление в объект fileAnchors номеров последних строк, если в крайних 3-х строках есть изменения
    private void setLastAnchorNumberLine() {
        fileAnchors = new FileAnchors();
        int indexOne = listOneSize - 1;
        int indexTwo = listTwoSize - 1;
        int count = 0;
        while (count <= oneTwoThree) {
            if (!mapLinesAnchors.get(mapLinesAnchors.size() - 1).stop.isEmpty()) // если объект содержит stop, то цикл прерываем
                break;
            else if (listOne.get(indexOne).equals(listTwo.get(indexTwo))) {
                indexOne--;
                indexTwo--;
                count++;
            } else {
                fileAnchors.stopOneNumber = String.valueOf(listOneSize);
                fileAnchors.stopTwoNumber = String.valueOf(listTwoSize);
                fileAnchors.stop = STOP_LINE;
                mapLinesAnchors.put(mapLinesAnchors.size() - 1, fileAnchors);
                break;
            }
        }
    }


    //====================== выборка текстов между трехстрочными анкорами ===========================

    private final Map<Integer, List<TextBetweenAnchors>> textBetweenAnchorsMap = new HashMap<>();

    // метод, запускающий поиск и сбор текста между трехстрочными анкорами
    public Map<Integer, List<TextBetweenAnchors>> searchTextBlockForComparison() {
        textSearchBetweenAnchors();
        return textBetweenAnchorsMap;
    }

    private int startLine = -1; //инициализировано -1, чтобы не было count == startLine, если count = 0
    private int stopLine;
    private int countMap = 1;

    // поиск начального и конечного индексов в листах с текстами из сравниваемых файлов
    private void textSearchBetweenAnchors() {
        int count = 0;
        while (count < mapLinesAnchors.size()) {
            if (count == startLine) // чтобы не считывать start повторно, определяем count как +1 если count == start
                count++;
            if (mapLinesAnchors.get(count).start.contains(START_LINE)) {
                startLine = count;
                while (count < mapLinesAnchors.size()) {
                    if (count <= stopLine && count != 0) // чтобы не считывать stop повторно, определяем count как +1 от прошлого stop
                        count = stopLine + 1;
                    if (mapLinesAnchors.get(count).stop.contains(STOP_LINE)) {
                        stopLine = count;

                        // сначала запускаем метод для поиска текста в listOne
                        int startOne = Integer.parseInt(mapLinesAnchors.get(startLine).startOneNumber);
                        int stopOne = Integer.parseInt(mapLinesAnchors.get(stopLine).stopOneNumber);
                        textCopyBetweenAnchors(startOne - 1, stopOne - 1, listOne);
                        countMap++;

                        // потом запускаем метод поиска по listTwo
                        int startTwo = Integer.parseInt(mapLinesAnchors.get(startLine).startTwoNumber);
                        int stopTwo = Integer.parseInt(mapLinesAnchors.get(stopLine).stopTwoNumber);
                        textCopyBetweenAnchors(startTwo - 1, stopTwo - 1, listTwo);
                        countMap++;
                        // строка якорь может быть и stop, и start, поэтому делаем несколько шагов назад
                        count -= count - zeroOneTwo >= 0 ? zeroOneTwo : zeroOneTwo + 1;
                        break;
                    } else
                        count++;
                }
            } else
                count++;
        }
    }

    // ищем текст между трехстрочиями и вместе с трехстрочиями добавляем поочередно в map
    private void textCopyBetweenAnchors(int start, int stop, List<String> list) {
        int index = start;
        int count = 1;

        List<TextBetweenAnchors> textAnchorsList = new ArrayList<>();
        while (index <= stop) {
            TextBetweenAnchors textBetweenAnchors = new TextBetweenAnchors();
            textBetweenAnchors.lineNumber = count;
            textBetweenAnchors.anchorsLines = list.get(index);
            textBetweenAnchors.index = index;
            if (index == start) {
                textBetweenAnchors.startOneNumber = mapLinesAnchors.get(startLine).startOneNumber;
                textBetweenAnchors.startTwoNumber = mapLinesAnchors.get(startLine).startTwoNumber;
                textBetweenAnchors.start = mapLinesAnchors.get(startLine).start;
            } else if (index == stop) {
                textBetweenAnchors.stopOneNumber = mapLinesAnchors.get(stopLine).stopOneNumber;
                textBetweenAnchors.stopTwoNumber = mapLinesAnchors.get(stopLine).stopTwoNumber;
                textBetweenAnchors.stop = mapLinesAnchors.get(stopLine).stop;
            }
            textAnchorsList.add(textBetweenAnchors);
            index++;
            count++;
        }
        textBetweenAnchorsMap.put(countMap, textAnchorsList);
    }


    //=============================== MAIN ============================================

    public static void main(String[] args) {
        FileCompareSeven test = new FileCompareSeven();
        test.readFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\testfile\\03.txt",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\testfile\\04.txt");

        System.out.println("------------ Patch -------------");
        for (Map.Entry<Integer, FileAnchors> entry : test.compareFiles().entrySet()) {
            System.out.format("%3d", entry.getKey());
            System.out.println(": " + entry.getValue());
        }

        System.out.println("\n====================================");

        int count = 0;
        for (Map.Entry<Integer, List<TextBetweenAnchors>> entry : test.searchTextBlockForComparison().entrySet()) {
            System.out.println("--- " + entry.getKey() + " ---");
            while (count < entry.getValue().size()) {
                System.out.println(entry.getValue().get(count));
                count++;
            }
            count = 0;
        }
    }
}