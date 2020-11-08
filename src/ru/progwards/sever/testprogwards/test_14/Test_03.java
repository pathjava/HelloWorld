package ru.progwards.sever.testprogwards.test_14;

import java.util.TreeMap;

public class Test_03 {
    void checkAndAdd(TreeMap<Integer, String> map, Integer key, String value) {
        if (map.containsKey(key) || map.size() < 2) {
            return;
        }
        if (key > map.firstKey() && key < map.lastKey()) {
            map.putIfAbsent(key, value);
        }
    }
}
