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
    private final String FINISH_LINE = "finish";

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
        setFirstAnchorNumberLine();
        setLastAnchorNumberLine();
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
            setAnchorNumberLine(i + oneTwoThree, j + oneTwoThree, FINISH_LINE);
            addAnchorsToHashMap(j);
        }
        if ((i == 0 && j == 0) || (i == 0 && j > 0) || (i > 0 && j == 0))
            if (checkNextLines(i, j)) {
                setAnchorNumberLine(i + 1, j + 1, START_LINE);
                addAnchorsToHashMap(j);
            }
        if ((i > 0 && j > 0) && (i < listOneSize - oneTwoThree && j < listTwoSize - oneTwoThree)) {
            if (checkPrevLines(i, j)) {
                setAnchorNumberLine(i + oneTwoThree, j + oneTwoThree, FINISH_LINE);
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
                setAnchorNumberLine(i + oneTwoThree, j + oneTwoThree, FINISH_LINE);
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

    // проверка равенства предыдущей (не пустой) строки трехстрочий
    private boolean checkPrevNextFirstLine(int indexOne, int indexTwo) {
        return listOne.get(indexOne).equals(listTwo.get(indexTwo))
                && !listOne.get(indexOne).isEmpty() && !listTwo.get(indexTwo).isEmpty();
    }

    // инкремент count при обнаружении совпадения строк идущих подряд
    private int incrementCountWhenCheckingLines(int indexOne, int indexTwo, int count) {
        if (listOne.get(indexOne).equals(listTwo.get(indexTwo))
                && !listOne.get(indexOne).isEmpty() && !listTwo.get(indexTwo).isEmpty())
            count++;
        else
            count = 0;
        return count;
    }

    // листы и метод для временного хранения индексов троестрочий
    private List<String> oneListNumberLine;
    private List<String> twoListNumberLine;

    // добавление во временные листы номеров строк трохстрочий для добавления в объект fileAnchors
    private void setAnchorNumberLine(int i, int j, String startFinish) {
        oneListNumberLine = new ArrayList<>();
        twoListNumberLine = new ArrayList<>();
        switch (startFinish) {
            case "finish":
                oneListNumberLine.add(String.valueOf(i));
                oneListNumberLine.add(String.valueOf(j));
                oneListNumberLine.add(startFinish);
                break;
            case "start":
                twoListNumberLine.add(String.valueOf(i));
                twoListNumberLine.add(String.valueOf(j));
                twoListNumberLine.add(startFinish);
                break;
        }
    }

    // добавление объекта fileAnchors с трехстрочием и индексами в HashMap
    private void addAnchorsToHashMap(int j) {
        int count = 0;
        while (count < oneTwoThree) {
            fileAnchors = new FileAnchors();

            if (fileFinalMap.get(j + count).finish.contains(FINISH_LINE)) {
                fileAnchors.finish = FINISH_LINE;
                fileAnchors.finishOneNumber = fileFinalMap.get(j + count).finishOneNumber;
                fileAnchors.finishTwoNumber = fileFinalMap.get(j + count).finishTwoNumber;
            } else if (fileFinalMap.get(j + count).start.contains(START_LINE)) {
                fileAnchors.start = START_LINE;
                fileAnchors.startOneNumber = fileFinalMap.get(j + count).startOneNumber;
                fileAnchors.startTwoNumber = fileFinalMap.get(j + count).startTwoNumber;
            }

            if (!oneListNumberLine.isEmpty() && count == zeroOneTwo) {
                fileAnchors.finishOneNumber = oneListNumberLine.get(0);
                fileAnchors.finishTwoNumber = oneListNumberLine.get(1);
                fileAnchors.finish = oneListNumberLine.get(2);
            } else if (!twoListNumberLine.isEmpty() && count == 0) {
                fileAnchors.startOneNumber = twoListNumberLine.get(0);
                fileAnchors.startTwoNumber = twoListNumberLine.get(1);
                fileAnchors.start = twoListNumberLine.get(2);
            }

            fileAnchors.anchorsLines = listTwo.get(j + count);
            fileFinalMap.put(j + count, fileAnchors);
            count++;
        }
    }

    // добавление в объект fileAnchors номеров первых строк, если в начале текста есть изменения
    private void setFirstAnchorNumberLine() {
        fileAnchors = new FileAnchors();
        int count = 0;
        while (count < 3) {
            if (listOne.get(count).equals(listTwo.get(count)))
                count++;
            else {
                fileAnchors.startOneNumber = String.valueOf(1);
                fileAnchors.startTwoNumber = String.valueOf(1);
                fileAnchors.start = START_LINE;
                break;
            }
        }
        fileFinalMap.put(0, fileAnchors);
    }

    // добавление в объект fileAnchors номеров последних строк, если в конце текста есть изменения
    private void setLastAnchorNumberLine() {
        fileAnchors = new FileAnchors();
        int indexOne = listOneSize - 1;
        int indexTwo = listTwoSize - 1;
        int count = 0;
        while (count < 3) {
            if (listOne.get(indexOne).equals(listTwo.get(indexTwo))) {
                indexOne--;
                indexTwo--;
                count++;
            } else {
                fileAnchors.finishOneNumber = String.valueOf(listOneSize);
                fileAnchors.finishTwoNumber = String.valueOf(listTwoSize);
                fileAnchors.finish = FINISH_LINE;
                break;
            }
        }
        fileFinalMap.put(fileFinalMap.size() - 1, fileAnchors);
    }


    public static void main(String[] args) {
        FileCompareSeven test = new FileCompareSeven();
        test.readFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\testfile\\05.txt",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\testfile\\06.txt");

        System.out.println("------------ Patch -------------");
        for (Map.Entry<Integer, FileAnchors> entry : test.compareFiles().entrySet()) {
            System.out.format("%3d", entry.getKey());
            System.out.println(": " + entry.getValue());
        }
    }
}