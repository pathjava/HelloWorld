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
    private static final List<Integer> randomNumbers = new ArrayList<>();
    private static List<String> tokensList = new ArrayList<>();

    private static final String MINIMUM_TIME = "Минимальное время теста: ";
    private static final String AVERAGE_TIME = "Среднее арифметическое время теста: ";
    private static final String MAXIMUM_TIME = "Максимальное время теста: ";

    public static void testing(String tokensFile) {
//        fillingSortedData();
        readFile(tokensFile);
//        testAddToAvlTreeSortedNum();
//        testAddToTreeMapSortedNum();
//        testAddToAvlTreeRandomNum();
//        testAddToTreeMapRandomNum();
        testAddToAvlTreeString();
        testAddToTreeMapString();
    }

    private static void testAddToAvlTreeSortedNum() {
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
        resultTest(results);
    }

    private static void testAddToTreeMapSortedNum() {
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
        resultTest(results);
    }

    private static void testAddToAvlTreeRandomNum() {
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
        resultTest(results);
    }

    private static void testAddToTreeMapRandomNum() {
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
        resultTest(results);
    }

    private static void testAddToAvlTreeString() {
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
        resultTest(results);
    }

    private static void testAddToTreeMapString() {
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
        resultTest(results);
    }

    private static void resultTest(List<Long> results){
        Collections.sort(results);
        System.out.println(MINIMUM_TIME + results.get(0));
        System.out.println(AVERAGE_TIME + calculateAverageTestsTime(results));
        System.out.println(MAXIMUM_TIME + results.get(results.size() - 1));
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
