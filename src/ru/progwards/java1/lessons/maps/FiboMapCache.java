package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FiboMapCache {
    private Map<Integer, BigDecimal> fiboCache = null;
    private boolean cacheOn = false;

    public FiboMapCache(boolean cacheOn){
        this.cacheOn = cacheOn;
    }

    public BigDecimal fiboNumber(int n){
        if (cacheOn && fiboCache != null){
            BigDecimal cacheResult = fiboCache.get(n);
            if (cacheResult != null){
                return cacheResult;
            }
        }
        //проверить есть ли -1 и -2
        int n0 = 1;
        int n1 = 1;
        int n2 = 0;
        for(int i = 3; i <= n; i++){
            n2 = n0 + n1;
            n0 = n1;
            n1 = n2;
            // ложить рассчитанные фибоначи в мап
        }
        if (cacheOn) {
            if (fiboCache == null) {
                fiboCache = new TreeMap<>();
            }
            fiboCache.put(n, BigDecimal.valueOf(n2));
        }

        return BigDecimal.valueOf(n2);
    }

    public void clearCahe(){
        fiboCache = null;
    }

    public static void test(){
        int n0 = 1;
        int n1 = 1;
        int n2;
        for(int i = 3; i <= 1000; i++){
            n2 = n0 + n1;
            n0 = n1;
            n1 = n2;
        }
    }
}
