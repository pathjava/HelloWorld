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
        BigDecimal a = BigDecimal.ZERO;
        BigDecimal b = BigDecimal.ONE;
        BigDecimal c = null;
//        System.out.println(a);
//        System.out.println(b);

        int i = 1;
        while (i++ < n) {
            c = b;
            b = b.add(a);
            a = c;
        }
//        for(int i = 3; i <= n; i++){
//            c = a.add(b);
//            a = b;
//            b = c;
////            System.out.println(c);
//            // ложить рассчитанные фибоначи в мап
//        }

        if (cacheOn) {
            if (fiboCache == null) {
                fiboCache = new TreeMap<>();
            }
            fiboCache.put(n, c);
        }

        return b;
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

    public static void main(String[] args) {
        FiboMapCache test = new FiboMapCache(false);
        System.out.println(test.fiboNumber(20));
    }
}
