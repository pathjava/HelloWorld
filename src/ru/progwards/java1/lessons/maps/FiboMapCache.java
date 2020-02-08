package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.System.currentTimeMillis;

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
        BigDecimal a = BigDecimal.ZERO;
        BigDecimal b = BigDecimal.ONE;
        BigDecimal c = null;

        int i = 1;
        while (i++ < n) {
            c = b;
            b = b.add(a);
            a = c;
            // ложить рассчитанные фибоначи в мап
        }

        if (cacheOn) {
            if (fiboCache == null) {
                fiboCache = new TreeMap<>();
            }
            fiboCache.put(n, b);
        }

        return b;
    }

    public void clearCahe(){
        fiboCache = null;
    }

    public static void test(){
        FiboMapCache fibo;

        long start = currentTimeMillis();
        fibo = new FiboMapCache(false);
        for (int i = 1; i <= 1000; i++)
            fibo.fiboNumber(i);
        System.out.println("fiboNumber cacheOn=" + false + " время выполнения " + (currentTimeMillis() - start));

        start = currentTimeMillis();
        fibo = new FiboMapCache(true);
        for (int i = 1; i <= 1000; i++)
            fibo.fiboNumber(i);
        System.out.println("fiboNumber cacheOn=" + true + " время выполнения " + (currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        FiboMapCache test = new FiboMapCache(false);
        System.out.println(test.fiboNumber(20));
        test();
    }
}
