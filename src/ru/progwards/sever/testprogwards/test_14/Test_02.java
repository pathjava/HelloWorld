package ru.progwards.sever.testprogwards.test_14;

import java.util.Map;

public class Test_02 {
    int fillHoles(Map<Integer, String> map, int size) {
        int result = 0;
        for (int i = 1; i <= size; i++) {
            String oldVal = map.putIfAbsent(i, "Число " + i);
            if (oldVal == null) result++;
        }
        return result;
    }
}
