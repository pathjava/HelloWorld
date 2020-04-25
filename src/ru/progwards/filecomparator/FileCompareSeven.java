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
    private final String IS_ANCHOR = "is";

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

    // листы и метод для временного хранения номеров строк троестрочий
    private List<String> oneListNumberLine;
    private List<String> twoListNumberLine;

    // добавление во временные листы номеров строк трохстрочий для добавления в объект fileAnchors
    private void setAnchorNumberLine(int i, int j, String startStop) {
        oneListNumberLine = new ArrayList<>();
        twoListNumberLine = new ArrayList<>();
        switch (startStop) {
            case STOP_LINE:
                oneListNumberLine.add(String.valueOf(i));
                oneListNumberLine.add(String.valueOf(j));
                oneListNumberLine.add(startStop);
                break;
            case START_LINE:
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

            fileAnchors.lineIsAnchor = IS_ANCHOR;
            fileAnchors.anchorLine = listTwo.get(j + count);
            mapLinesAnchors.put(j + count, fileAnchors);
            count++;
        }
    }

    // добавление в объект fileAnchors номеров первых строк, если в первых 3-х строках есть изменения в тексте
    // поиск изменений идет по первым четырем строкам (0,1,2,3), но если анкор записан ранее, он не перезаписывается
    private void setFirstAnchorNumberLine() {
        int count = 0;
        while (count <= oneTwoThree) {
            if (!mapLinesAnchors.get(0).start.isEmpty()) // если объект содержит start, то цикл прерываем
                break;
            else if (listOne.get(count).equals(listTwo.get(count))) // если строки равные, проверяем следующие
                count++;
            else { // иначе устанавливаем start и номера строк
                addFirstOrLastAnchorLine(count, START_LINE);
                break;
            }
        }
    }

    // добавление в объект fileAnchors номеров последних строк, если в крайних 3-х строках есть изменения
    private void setLastAnchorNumberLine() {
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
                addFirstOrLastAnchorLine(count, STOP_LINE);
                break;
            }
        }
    }

    // добавление одно-двух строчных текстовых анкоров в начале и конце текста
    private void addFirstOrLastAnchorLine(int count, String startStop) {
        int index = startStop.equals(START_LINE) ? 0 : mapLinesAnchors.size() - 1;
        fileAnchors = new FileAnchors();
        switch (startStop) {
            case START_LINE:
                fileAnchors.startOneNumber = String.valueOf(1);
                fileAnchors.startTwoNumber = String.valueOf(1);
                fileAnchors.start = startStop;
                // проверяем строку только из одного листа на !isEmpty так как ранее строки прошли equals
                // пустая строка может быть анкором, только если за ней не пустая строка-анкор, иначе нет
                if (count == 1 && !listOne.get(index).isEmpty() || count == 2 && !listOne.get(index + 1).isEmpty()) {
                    fileAnchors.lineIsAnchor = IS_ANCHOR;
                    fileAnchors.anchorLine = listOne.get(index);
                }
                break;
            case STOP_LINE:
                fileAnchors.stopOneNumber = String.valueOf(listOneSize);
                fileAnchors.stopTwoNumber = String.valueOf(listTwoSize);
                fileAnchors.stop = STOP_LINE;
                if (count == 1 && !listOne.get(index).isEmpty() || count == 2 && !listOne.get(index - 1).isEmpty()) {
                    fileAnchors.lineIsAnchor = IS_ANCHOR;
                    fileAnchors.anchorLine = listOne.get(index);
                }
                break;
        }
        mapLinesAnchors.put(index, fileAnchors);

        // если count пришел == 2, проверяем и добавляем вторую строку якорь
        index = startStop.equals(START_LINE) ? 1 : mapLinesAnchors.size() - 2;
        if (count == 2 && mapLinesAnchors.get(index).anchorLine.isEmpty()) {
            fileAnchors = new FileAnchors();
            fileAnchors.lineIsAnchor = IS_ANCHOR;
            fileAnchors.anchorLine = listOne.get(index);
            mapLinesAnchors.put(index, fileAnchors);
        }
    }


    //====================== выборка текстов между трехстрочными анкорами ===========================

    private final Map<Integer, List<TextBetweenAnchors>> textBetweenAnchorsMap = new HashMap<>();

    // метод, запускающий поиск и сбор текста между трехстрочными анкорами
    public Map<Integer, List<TextBetweenAnchors>> searchTextBlockForComparison() {
        textSearchBetweenAnchors();
        searchInnerAnchorsBetweenMainAnchors();
        return textBetweenAnchorsMap;
    }

    // поиск начального и конечного индексов в листах с текстами из сравниваемых файлов
    private void textSearchBetweenAnchors() {
        int startLine = -1; //инициализировано -1, чтобы не было count == startLine, если count = 0
        int stopLine = 0;
        int countMap = 1;
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
                        textCopyToBetweenAnchors(startOne - 1, stopOne - 1,
                                listOne, startLine, stopLine, countMap);
                        countMap++;

                        // потом запускаем метод поиска по listTwo
                        int startTwo = Integer.parseInt(mapLinesAnchors.get(startLine).startTwoNumber);
                        int stopTwo = Integer.parseInt(mapLinesAnchors.get(stopLine).stopTwoNumber);
                        textCopyToBetweenAnchors(startTwo - 1, stopTwo - 1,
                                listTwo, startLine, stopLine, countMap);
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
    private void textCopyToBetweenAnchors(int start, int stop, List<String> list,
                                          int startLine, int stopLine, int countMap) {
        int index = start;
        int count = 1;
        List<Integer> indexesOfAnchorLinesList = new ArrayList<>();
        List<TextBetweenAnchors> textAnchorsList = new ArrayList<>();
        while (index <= stop) {
            TextBetweenAnchors textBetweenAnchors = new TextBetweenAnchors();
            textBetweenAnchors.lineNumber = count; // номер количества строк в блоке
            textBetweenAnchors.anchorLine = list.get(index); // добавляем строку
            textBetweenAnchors.index = index; // добавляем индекс строки

            searchIndexesOfAnchorLines(start, stop, list, startLine, stopLine, indexesOfAnchorLinesList);
            // полученные индексы в методе searchIndexesOfAnchorLines() сравниваем с текущим index
            if (comparisonOfLineAndAnchorIndexes(index, indexesOfAnchorLinesList))
                textBetweenAnchors.lineIsAnchor = IS_ANCHOR; // если index есть в листе, добавляем is в объект

            if (index == start) {
                textBetweenAnchors.startOneNumber = mapLinesAnchors.get(startLine).startOneNumber;
                textBetweenAnchors.startTwoNumber = mapLinesAnchors.get(startLine).startTwoNumber;
                textBetweenAnchors.start = START_LINE;
            } else if (index == stop) {
                textBetweenAnchors.stopOneNumber = mapLinesAnchors.get(stopLine).stopOneNumber;
                textBetweenAnchors.stopTwoNumber = mapLinesAnchors.get(stopLine).stopTwoNumber;
                textBetweenAnchors.stop = STOP_LINE;
            }
            textAnchorsList.add(textBetweenAnchors);
            index++;
            count++;
        }
        textBetweenAnchorsMap.put(countMap, textAnchorsList);
    }

    // поиск индексов строк-анкоров (is) и добавление индексов в ArrayList (indexesOfAnchorLinesList)
    private void searchIndexesOfAnchorLines(int start, int stop, List<String> list,
                                            int startLine, int stopLine, List<Integer> listIndexes) {
        int count = 0;
        // ищем индексы (is) с начала блока от индекса start - количество циклов зависит от oneTwoThree (от 1 до 3)
        while (count < oneTwoThree) {
            if (list.get(start).equals(mapLinesAnchors.get(startLine).anchorLine)
                    && !mapLinesAnchors.get(startLine).lineIsAnchor.isEmpty())
                listIndexes.add(start);
            else
                break;
            count++;
            start++;
            startLine++;
        }
        // ищем индексы с конца блока
        count = 0;
        while (count < oneTwoThree) {
            if (list.get(stop).equals(mapLinesAnchors.get(stopLine).anchorLine)
                    && !mapLinesAnchors.get(stopLine).lineIsAnchor.isEmpty())
                listIndexes.add(stop);
            else
                break;
            count++;
            stop--;
            stopLine--;
        }
    }

    // проверяем наличие index в массиве indexesOfAnchorLinesList
    private boolean comparisonOfLineAndAnchorIndexes(int index, List<Integer> listIndexes) {
        int count = 0;
        boolean isAnchor = false;
        while (count < listIndexes.size()) {
            if (index == listIndexes.get(count)) {
                isAnchor = true;
                break;
            } else
                count++;
        }
        return isAnchor;
    }

    //========== поиск анкеров в тексте между основными анкерами ===========//

    private List<TextBetweenAnchors> tempListOne;
    private List<TextBetweenAnchors> tempListTwo;

    private void searchInnerAnchorsBetweenMainAnchors() {
        int countOne = 1;
        int countTwo = 2;
        int count = 0;
        while (count < textBetweenAnchorsMap.size()) {
            tempListOne = new ArrayList<>(textBetweenAnchorsMap.get(countOne));
            tempListTwo = new ArrayList<>(textBetweenAnchorsMap.get(countTwo));
            comparisonInnerLinesBetweenMainAnchors(tempListOne, tempListTwo);
            count += 2;
            countOne += 2;
            countTwo += 2;
        }
        textBetweenAnchorsMap.put(countOne, tempListOne);
        textBetweenAnchorsMap.put(countTwo, tempListTwo);
    }

    private void comparisonInnerLinesBetweenMainAnchors(List<TextBetweenAnchors> listOne, List<TextBetweenAnchors> listTwo) {
        int iStartOne = searchStartIndex(listOne);
        int iStopOne = searchStopIndex(listOne);
        int iStartTwo = searchStartIndex(listTwo);
        int iStopTwo = searchStopIndex(listTwo);
        int minValueInnerText = Math.min(iStopOne - iStartOne, iStopTwo - iStartTwo);

        if (iStartOne >= 0 && iStopOne >= 0 && iStartTwo >= 0 && iStopTwo >= 0) {
            int lastComparison = 0;
            if (minValueInnerText > 8)
                searchPairInnerLine(iStartOne, iStopOne, listOne, iStartTwo, iStopTwo, listTwo);
//                while (iStartOne <= iStopOne) {
//                    while (iStartTwo <= iStopTwo) {
//                        if (comparisonPairInnerLine(iStartOne, iStopOne, listOne, iStartTwo, iStopTwo, listTwo)) {
//                            changeValuesInnerLine(iStartOne, listOne, iStartTwo + 1, listTwo, 2);
//                            iStartOne++;
//                            lastComparison = iStartTwo;
//                        }
//                        iStartTwo++;
//                    }
//                    iStartTwo = lastComparison;
//                    iStartOne++;
//                }
            searchSingleInnerLine(iStartOne, iStopOne, listOne, iStartTwo, iStopTwo, listTwo);
//            iStartOne = searchStartIndex(listOne);
//            iStopOne = searchStopIndex(listOne);
//            iStartTwo = searchStartIndex(listTwo);
//            iStopTwo = searchStopIndex(listTwo);
//            while (iStartOne <= iStopOne) {
//                while (iStartTwo <= iStopTwo) {
//                    if (comparisonSingleInnerLine(iStartOne, listOne, iStartTwo, listTwo)) {
//                        changeValuesInnerLine(iStartOne, listOne, iStartTwo, listTwo, 1);
//                        iStartOne++;
//                        lastComparison = iStartTwo;
//                    }
//                    iStartTwo++;
//                }
//                iStartTwo = lastComparison;
//                iStartOne++;
//            }
        }
    }

    private void searchPairInnerLine(int iStartOne, int iStopOne, List<TextBetweenAnchors> listOne,
                                     int iStartTwo, int iStopTwo, List<TextBetweenAnchors> listTwo){
        int lastComparison = 0;
        while (iStartOne <= iStopOne) {
            while (iStartTwo <= iStopTwo) {
                if (comparisonPairInnerLine(iStartOne, iStopOne, listOne, iStartTwo, iStopTwo, listTwo)) {
                    changeValuesInnerLine(iStartOne, listOne, iStartTwo + 1, listTwo, 2);
                    iStartOne++;
                    lastComparison = iStartTwo;
                }
                iStartTwo++;
            }
            iStartTwo = lastComparison;
            iStartOne++;
        }
    }

    private void searchSingleInnerLine(int iStartOne, int iStopOne, List<TextBetweenAnchors> listOne,
                                       int iStartTwo, int iStopTwo, List<TextBetweenAnchors> listTwo){
        int lastComparison = 0;
        while (iStartOne <= iStopOne) {
            while (iStartTwo <= iStopTwo) {
                if (comparisonSingleInnerLine(iStartOne, listOne, iStartTwo, listTwo)) {
                    changeValuesInnerLine(iStartOne, listOne, iStartTwo, listTwo, 1);
                    iStartOne++;
                    lastComparison = iStartTwo;
                }
                iStartTwo++;
            }
            iStartTwo = lastComparison;
            iStartOne++;
        }
    }

    private int searchStartIndex(List<TextBetweenAnchors> list) {
        int index = -1;
        int count = 0;
        while (count < list.size()) {
            if (list.get(count).lineIsAnchor.isEmpty()) {
                index = count;
                break;
            } else
                count++;
        }
        return index;
    }

    private int searchStopIndex(List<TextBetweenAnchors> list) {
        int index = -1;
        int count = list.size() - 1;
        while (count >= 0) {
            if (list.get(count).lineIsAnchor.isEmpty()) {
                index = count;
                break;
            } else
                count--;
        }
        return index;
    }

    private boolean comparisonPairInnerLine(int iStartOne, int iStopOne, List<TextBetweenAnchors> listOne,
                                            int iStartTwo, int iStopTwo, List<TextBetweenAnchors> listTwo) {
        int lastPairOne = searchLastPairInnerLine(iStartOne, iStopOne, listOne);
        int lastPairTwo = searchLastPairInnerLine(iStartTwo, iStopTwo, listTwo);

        int firstPairOne = searchFirstPairInnerLine(iStartOne, lastPairOne, listOne);
        int firstPairTwo = searchFirstPairInnerLine(iStartTwo, lastPairTwo, listTwo);

        int count = 0;
        while (count < 2) {
            if (firstPairOne + count <= lastPairOne && firstPairTwo + count <= lastPairTwo
                    && listOne.get(firstPairOne + count).anchorLine.equals(listTwo.get(firstPairTwo + count).anchorLine))
                count++;
            else
                return false;
        }
        return true;
    }

    private boolean comparisonSingleInnerLine(int iStartOne, List<TextBetweenAnchors> listOne,
                                              int iStartTwo, List<TextBetweenAnchors> listTwo) {
        return !listOne.get(iStartOne).anchorLine.isEmpty() && !listTwo.get(iStartTwo).anchorLine.isEmpty()
                && listOne.get(iStartOne).anchorLine.equals(listTwo.get(iStartTwo).anchorLine);
    }

    private int searchLastPairInnerLine(int iStart, int iStop, List<TextBetweenAnchors> list) {
        int index = iStop;
        int count = 0;
        while (count != 2) {
            if (index >= iStart && !list.get(index).anchorLine.isEmpty())
                count++;
            else
                count = 0;

            if (index > iStart) index--;
        }
        return index + 2;
    }

    private int searchFirstPairInnerLine(int iStart, int lastPair, List<TextBetweenAnchors> list) {
        int index = iStart;
        int count = 0;
        while (count != 2) {
            if (index <= lastPair && !list.get(index).anchorLine.isEmpty())
                count++;
            else
                count = 0;

            if (index + 1 <= lastPair) index++;
        }
        return index - 2;
    }

    private void changeValuesInnerLine(int indexOne, List<TextBetweenAnchors> listOne,
                                       int indexTwo, List<TextBetweenAnchors> listTwo, int loop) {
        int count = 0;
        while (count < loop) {
            listOne.get(indexOne).lineIsAnchor = IS_ANCHOR;
            listTwo.get(indexTwo).lineIsAnchor = IS_ANCHOR;
            indexOne++;
            indexTwo++;
            count++;
        }
    }


    //=============================== MAIN ============================================

    public static void main(String[] args) {
        FileCompareSeven test = new FileCompareSeven();
        test.readFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\testfile\\01.txt",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\testfile\\02.txt");

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