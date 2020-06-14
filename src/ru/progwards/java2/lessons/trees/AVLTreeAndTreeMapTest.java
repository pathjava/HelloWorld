// Oleg Kiselev
// 13.06.2020, 20:01

package ru.progwards.java2.lessons.trees;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
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

    public static void fillingSortedData() {
        IntStream.range(0, 1000000).forEachOrdered(sortedNumbers::add);
        IntStream.range(0, 1000000).map(i -> ThreadLocalRandom.current()
                .nextInt(10, 500000 + 1)).forEachOrdered(randomNumbers::add);
        readFile();
    }

    private static void readFile() {
        Path file = null;
        try {
            file = Paths.get
                    ("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java2\\lessons\\trees\\wiki.train.tokens");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] words;
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader
                (new FileInputStream(String.valueOf(file)), StandardCharsets.UTF_8))){
            while((line = br.readLine()) != null){
                words = line.split("\\s");
                for (String str : words) {
                    if (str.matches("[a-zA-Zа-яёА-ЯЁ]+"))
                        tokensList.add(str);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        fillingSortedData();

//        randomNumbers.forEach(System.out::println);
//        System.out.println(randomNumbers.size());
//        sortedNumbers.forEach(System.out::println);
//        System.out.println(sortedNumbers.size());


        for (String s : tokensList) {
            System.out.println(s);
        }
        System.out.println(tokensList.size());
    }
}
