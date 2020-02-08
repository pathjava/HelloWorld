package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class FiboMapCache {
    private Map<Integer, BigDecimal> fiboCache;
    private boolean cacheOn;

    public FiboMapCache(boolean cacheOn){
        this.cacheOn = cacheOn;
        clearCahe();
    }

    public BigDecimal fiboNumber(int n){
        if (cacheOn && fiboCache != null){
            BigDecimal cacheResult = fiboCache.get(n);
            if (cacheResult != null){
                return cacheResult;
            }
        }
        //проверить есть ли -1 и -2
        BigDecimal n0 = BigDecimal.ONE;
        BigDecimal n1 = BigDecimal.ONE;
        BigDecimal n2 = BigDecimal.ZERO;
        for(int i = 3; i <= n; i++){
            n2 = n0.add(n1);
            n0 = n1;
            n1 = n2;
            // ложить рассчитанные фибоначи в мап
        }
        if (cacheOn) {
            if (fiboCache == null) {
                fiboCache = new TreeMap<>();
            }
            fiboCache.put(n, n2);
        }

        return n2;
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
