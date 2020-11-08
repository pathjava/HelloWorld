package ru.progwards.sever.testprogwards.test_11;

import java.util.*;

public class Test_02 {
    public static List<Integer> filter(List<Integer> list) {
        int sum = 0;
        for (Integer integer : list) {
            sum += integer;
        }
//        int sum = 0;
//        for (int i = 0; i < list.size(); i++) {
//            sum += list.get(i);
//        }
//        System.out.println(sum);

        List<Integer> result;

        if (list instanceof ArrayList) result = new ArrayList<>();
        else if (list instanceof LinkedList) result = new LinkedList<>();
        else if (list instanceof Vector) result = new Vector<>();
        else if (list instanceof Stack) result = new Stack<>();
        else if (list instanceof List) result = new ArrayList<>();
        else throw new RuntimeException("Unknown 'list' type!");

//        int drop = sum / 100;
//        for (Integer e : list) {
//            if (e < drop) result.add(e);
//        }
        int drop = sum / 100;
        for (int i = 0; i < list.size(); i++) {
            Integer e = list.get(i);
            if (e < drop) result.add(e);
        }

        return result;
    }


    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        for (Integer e : new Integer[]{40, 300, 0, 4, 3, 1, 2}) list.add(e);
        System.out.println(filter(list));

//        List<Integer> list = new ArrayList<>();
//        for(int i=0; i<20; i++)
//            list.add(i);
//
//        list.iterator();
//
//        System.out.println(list);
//
//        Test_02 test = new Test_02();
//        test.filter(list);
//
//        System.out.println(test);
    }
}
