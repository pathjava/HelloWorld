package ru.progwards.sever.testprogwards.test_14;

import java.util.HashMap;

public class Test_01 {
    HashMap<Integer, String> a2map(int[] akey, String[] aval) {
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < (akey.length & aval.length); i++) {
            map.put(akey[i], aval[i]);
        }
        return map;
    }
}
