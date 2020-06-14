// Oleg Kiselev
// 13.06.2020, 20:01

package ru.progwards.java2.lessons.trees;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class AVLTreeAndTreeMapTest {
    private static final AvlTree<Integer, Integer> avlTree = new AvlTree<>();
    private static final Map<Integer,Integer> treeMap = new TreeMap<>();

    private static final List<Integer> sortedNumbers = new ArrayList<>();
    private static final List<Integer> randomNumbers = new ArrayList<>();
    private static List<String> tokensList;
    static {
        try {
            tokensList = Files.readAllLines(Paths.get("ru\\progwards\\java2\\lessons\\trees\\wiki.train.tokens"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

    }
}
