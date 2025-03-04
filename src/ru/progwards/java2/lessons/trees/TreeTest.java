// Oleg Kiselev
// 06.06.2020, 17:41

package ru.progwards.java2.lessons.trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class TreeTest {
    static final int ITERATIONS = 1000;

    public static void main(String[] args) throws TreeException {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        BinaryTree<Integer, String> tree = new BinaryTree<>();

        for (int i = 0; i < ITERATIONS; i++) {
            int key = ThreadLocalRandom.current().nextInt();
            if (!map.containsKey(key)) {
                map.put(key, key);
                tree.add(key, "key=" + key);
            }
        }
        System.out.println("add passed OK");
        //tree.process(System.out::println);
//        ArrayList<BinaryTree<?, ?>.TreeLeaf<?, ?>> sorted = new ArrayList<>();
//        tree.process(sorted::add);
//        for (BinaryTree<?, ?>.TreeLeaf<?, ?> leaf : sorted) {
//            System.out.println(leaf.toString());
//        }
//
//        for (Integer i : map.keySet()) {
//            tree.find(i);
//            tree.delete(i);
//        }
//        System.out.println("find & delete passed OK");


        /* вызов итератора TreeIterator через цикл for без вызова метода getIterator() */
//        for (TreeIterator it = new TreeIterator(tree.getRoot()); it.hasNext();) {
//            System.out.println(it.next());
//        }

        /* вызов итератора через вызов метода getIterator() в BinaryTree */
        tree.getIterator();
    }
}
