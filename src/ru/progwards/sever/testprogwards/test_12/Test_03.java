package ru.progwards.sever.testprogwards.test_12;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Test_03 {

    public static void doTreeSet() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(19);
        treeSet.add(12);
        treeSet.add(15);
        treeSet.add(10);

        String s = "";
        Iterator<Integer> iterator = treeSet.iterator();
        while (iterator.hasNext())
            s += iterator.next();
        System.out.println(s);
    }

//    public static void doTreeSet() {
//        TreeSet<Integer> treeSet = new TreeSet<>();
//        treeSet.add(9);
//        treeSet.add(1);
//        treeSet.add(5);
//        treeSet.add(2);
//        treeSet.add(4);
//        treeSet.add(8);
//
//        String s = "";
//        Iterator<Integer> iterator = treeSet.descendingIterator();
//        while (iterator.hasNext())
//            s += iterator.next();
//        System.out.println(s);
//    }

    public static void main(String[] args) {
        doTreeSet();
//        TreeSet<Integer> treeSet = new TreeSet<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return Integer.compare(Math.abs(o1), Math.abs(o2));
//            }
//        });
//        treeSet.addAll(List.of(7,-1,5,4,-2,-8,0,3,-3,-7,6,-6,1));
//
//        System.out.println(treeSet);
    }
}
