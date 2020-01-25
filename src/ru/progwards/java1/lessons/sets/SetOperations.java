package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {
    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2){
        HashSet<Integer> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2){
        HashSet<Integer> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2){
        HashSet<Integer> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }

    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2){
        HashSet<Integer> sumAll = new HashSet<>(set1);
        sumAll.addAll(set2);
        HashSet<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        sumAll.removeAll(intersection);
        return sumAll;
    }


    public static void main(String[] args) {
        Set<Integer> setOne = Set.of(8, 7, 15, 2, 11, 13, 14, 6, 10, 4, 12, 3, 1, 9, 5);
        Set<Integer> setTwo = Set.of(9, 6, 7, 12, 10, 5, 19, 15, 14, 11, 8, 17, 16, 20, 13);

//        Set<Integer> setOne = Set.of(1,2,3);
//        Set<Integer> setTwo = Set.of(2,3,4);

        System.out.println(union(setOne, setTwo));
        System.out.println(intersection(setOne, setTwo));
        System.out.println(difference(setOne, setTwo));
        System.out.println(symDifference(setOne, setTwo));
    }
}
