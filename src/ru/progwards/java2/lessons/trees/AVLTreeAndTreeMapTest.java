// Oleg Kiselev
// 13.06.2020, 20:01

package ru.progwards.java2.lessons.trees;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class AVLTreeAndTreeMapTest {
    private static final AvlTree<Integer, Integer> avlTreeNumbers = new AvlTree<>();
    private static final Map<Integer, Integer> treeMapNumbers = new TreeMap<>();

    private static final AvlTree<Integer, String> avlTreeStrings = new AvlTree<>();
    private static final Map<Integer, String> treeMapStrings = new TreeMap<>();

    private static final List<Integer> sortedNumbers = new ArrayList<>();
    private static final List<Integer> sortedShuffleNumbers = new ArrayList<>();
    private static final List<Integer> randomNumbers = new ArrayList<>();
    private static final List<Integer> randomShuffleNumbers = new ArrayList<>();
    private static List<String> tokensList = new ArrayList<>();

    private static final String MINIMUM_TIME = "Минимальное время теста: ";
    private static final String AVERAGE_TIME = "Среднее арифметическое время теста: ";
    private static final String MAXIMUM_TIME = "Максимальное время теста: ";
    private static final String SORTED_NUM = "Sorted Num ";
    private static final String RANDOM_NUM = "Random Num ";
    private static final String WORDS = "Words ";
    private static final String ADD_TIME = "- Время добавления ";
    private static final String REMOVE_TIME = "- Время удаления ";


    public static void testing(String tokensFile) {
        fillingSortedData();
        readFile(tokensFile);
        testAddToAvlAndTreeMapTreeSortedNum();
        testAddToAvlAndTreeMapTreeRandomNum();
        testAddToAvlAndTreeMapTreeString();
    }

    private static void testAddToAvlAndTreeMapTreeSortedNum() {
        List<Long> resultAvl = testAddToAvlTreeSortedNum();
        List<Long> resultMap = testAddToTreeMapSortedNum();
        resultTests(resultAvl, resultMap, ADD_TIME, SORTED_NUM);
    }

    private static List<Long> testAddToAvlTreeSortedNum() {
        List<Long> results = new ArrayList<>();
        int count = 0;
        while (count < 5) {
            long start = System.currentTimeMillis();
            for (int num : sortedNumbers) {
                avlTreeNumbers.put(num, num);
            }
            long end = System.currentTimeMillis();
            results.add(end - start);
            avlTreeNumbers.clear();
            count++;
        }
        return results;
    }

    private static List<Long> testAddToTreeMapSortedNum() {
        List<Long> results = new ArrayList<>();
        int count = 0;
        while (count < 5) {
            long start = System.currentTimeMillis();
            for (int num : sortedNumbers) {
                treeMapNumbers.put(num, num);
            }
            long end = System.currentTimeMillis();
            results.add(end - start);
            treeMapNumbers.clear();
            count++;
        }
        return results;
    }

    private static void testAddToAvlAndTreeMapTreeRandomNum() {
        List<Long> resultAvl = testAddToAvlTreeRandomNum();
        List<Long> resultMap = testAddToTreeMapRandomNum();
        resultTests(resultAvl, resultMap, ADD_TIME, RANDOM_NUM);
    }

    private static List<Long> testAddToAvlTreeRandomNum() {
        List<Long> results = new ArrayList<>();
        int count = 0;
        while (count < 5) {
            long start = System.currentTimeMillis();
            for (int num : randomNumbers) {
                avlTreeNumbers.put(num, num);
            }
            long end = System.currentTimeMillis();
            results.add(end - start);
            avlTreeNumbers.clear();
            count++;
        }
        return results;
    }

    private static List<Long> testAddToTreeMapRandomNum() {
        List<Long> results = new ArrayList<>();
        int count = 0;
        while (count < 5) {
            long start = System.currentTimeMillis();
            for (int num : randomNumbers) {
                treeMapNumbers.put(num, num);
            }
            long end = System.currentTimeMillis();
            results.add(end - start);
            treeMapNumbers.clear();
            count++;
        }
        return results;
    }

    private static void testAddToAvlAndTreeMapTreeString() {
        List<Long> resultAvl = testAddToAvlTreeString();
        List<Long> resultMap = testAddToTreeMapString();
        resultTests(resultAvl, resultMap, ADD_TIME, WORDS);
    }

    private static List<Long> testAddToAvlTreeString() {
        List<Long> results = new ArrayList<>();
        int count = 0;
        while (count < 5) {
            int num = 0;
            long start = System.currentTimeMillis();
            for (String str : tokensList) {
                avlTreeStrings.put(num, str);
                num++;
            }
            long end = System.currentTimeMillis();
            results.add(end - start);
            avlTreeStrings.clear();
            count++;
        }
        return results;
    }

    private static List<Long> testAddToTreeMapString() {
        List<Long> results = new ArrayList<>();
        int count = 0;
        while (count < 5) {
            int num = 0;
            long start = System.currentTimeMillis();
            for (String str : tokensList) {
                treeMapStrings.put(num, str);
                num++;
            }
            long end = System.currentTimeMillis();
            results.add(end - start);
            treeMapStrings.clear();
            count++;
        }
        return results;
    }

    private static void testDeleteFromAvlTreeSortedNum() {
        List<Long> results = new ArrayList<>();
        int count = 0;
        while (count < 5) {
            for (int num : sortedNumbers) {
                avlTreeNumbers.put(num, num);
            }
            long start = System.currentTimeMillis();
            for (int num : sortedShuffleNumbers) {
                if (avlTreeNumbers.containsKey(num))
                    avlTreeNumbers.delete(num);
            }
            long end = System.currentTimeMillis();
            results.add(end - start);
            avlTreeNumbers.clear();
            count++;
        }
//        resultTests(results, REMOVE_TIME, SORTED_NUM, AVL_TREE);
    }

    private static void resultTests(List<Long> resAvl, List<Long> resMap, String operation, String type) {
        Collections.sort(resAvl);
        Collections.sort(resMap);
        System.out.printf("%-16s %-16s %-16s %-16s %n", operation, type, "AVL Tree", "TreeMap");
        System.out.printf("%-38s %-16d %d %n", MINIMUM_TIME, resAvl.get(0), resMap.get(0));
        System.out.printf("%-38s %-16.1f %.1f %n", AVERAGE_TIME, calculateAverageTestsTime(resAvl), calculateAverageTestsTime(resMap));
        System.out.printf("%-38s %-16d %d %n%n", MAXIMUM_TIME, resAvl.get(resAvl.size() - 1), resAvl.get(resMap.size() - 1));
    }

    private static double calculateAverageTestsTime(List<Long> results) {
        Double sum = 0.0;
        for (Long result : results) {
            sum += result;
        }
        return sum / results.size();
    }

    public static void fillingSortedData() {
        IntStream.range(0, 1000000).forEachOrdered(sortedNumbers::add);
        IntStream.range(0, 1000000).map(i -> ThreadLocalRandom.current()
                .nextInt(10, 500000 + 1)).forEachOrdered(randomNumbers::add);
        sortedShuffleNumbers.addAll(sortedNumbers);
        Collections.shuffle(sortedShuffleNumbers);
        randomShuffleNumbers.addAll(randomNumbers);
        Collections.shuffle(randomShuffleNumbers);
    }

    private static void readFile(String file) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)),
                StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s");
                Arrays.stream(words).filter(str -> str.matches("[a-zA-Zа-яёА-ЯЁ]+"))
                        .forEachOrdered(str -> tokensList.add(str));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        testing("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java2\\lessons\\trees\\wiki.train.tokens");

    }
}
