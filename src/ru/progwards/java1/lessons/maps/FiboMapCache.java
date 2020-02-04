package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.Map;

public class FiboMapCache {
    private Map<Integer, BigDecimal> fiboCache;

    public FiboMapCache(boolean cacheOn){

    }

    public BigDecimal fiboNumber(int n){
        return BigDecimal.ZERO;
    }

    public void clearCahe(){

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
