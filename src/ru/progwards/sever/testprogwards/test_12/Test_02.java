package ru.progwards.sever.testprogwards.test_12;

import java.util.HashSet;
import java.util.Set;

public class Test_02 {

    public void unionOfSets(final Set set1, final Set set2) {
        HashSet result = new HashSet(set1);
        result.addAll(set2);
        System.out.println(result.size());
    }

    public static void main(String[] args) {
        Set<Integer> setOne = Set.of(1, 3, 5, 12, 45);
        Set<Integer> setTwo = Set.of(12, 44, 2, 1, 4);

        Test_02 test = new Test_02();

        test.unionOfSets(setOne, setTwo);
    }

}
