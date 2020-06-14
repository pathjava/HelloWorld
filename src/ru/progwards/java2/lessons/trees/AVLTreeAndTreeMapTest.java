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

    public static void testing(String tokensFile) {
//        fillingSortedData();
        readFile(tokensFile);
//        testAvlTreeSortedNum();
//        testTreeMapSortedNum();
//        testAvlTreeRandomNum();
//        testTreeMapRandomNum();
        testAvlTreeString();
        testTreeMapString();
    }

    private static void testAvlTreeSortedNum() {
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
        System.out.println("AVL сортированного списка: " + results);
    }

    private static void testTreeMapSortedNum() {
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
        System.out.println("TreeMap сортированного списка: " + results);
    }

    private static void testAvlTreeRandomNum() {
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
        System.out.println("AVL рандомного списка: " + results);
    }

    private static void testTreeMapRandomNum() {
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
        System.out.println("TreeMap рандомного списка: " + results);
    }

    private static void testAvlTreeString() {
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
        System.out.println("AVL string списка: " + results);
    }

    private static void testTreeMapString() {
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
        System.out.println("TreeMap string списка: " + results);
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

//        randomNumbers.forEach(System.out::println);
//        System.out.println(randomNumbers.size());
//        sortedNumbers.forEach(System.out::println);
//        System.out.println(sortedNumbers.size());

    }
}
