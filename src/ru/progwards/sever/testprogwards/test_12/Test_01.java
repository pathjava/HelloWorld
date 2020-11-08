package ru.progwards.sever.testprogwards.test_12;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Test_01 {

    public Set<Integer> a2set(int[] a) {
        Set<Integer> arraySet = new HashSet<>();
        for (int arr : a) {
            arraySet.add(arr);
        }
        return arraySet;
    }

    public static void iSetTest() {
        Set iSet = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                iSet.add(i + j);
            }
        }
        System.out.println(iSet.size());
        System.out.println(iSet);
    }

    public static void wSetTest() {
        String TEXT = "на дворе трава на траве дрова не руби дрова на траве двора";
        Set wordSet = new HashSet<>(Arrays.asList(TEXT.split(" ")));

        Iterator<String> iter = wordSet.iterator();
        while (iter.hasNext())
            if (iter.next().contains("ра"))
                iter.remove();

//        System.out.println(wordSet.size());
    }


    public static void cset() {
        Set fiboSet1000 =
                Set.of(0, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987);
        int sum = 0;
        for (int i = 2; i < 10; i++)
            sum += fiboSet1000.contains(i) ? 1 : 0;
        System.out.println(sum);
    }


    public static void main(String[] args) {
        iSetTest();
        cset();
        wSetTest();
    }
}
