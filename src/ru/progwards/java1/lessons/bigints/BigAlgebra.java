package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAlgebra {
    static BigDecimal fastPow(BigDecimal num, int pow){
        return num.pow(pow);
    }

    private static FiboCache fiboCache;
    static {
        fiboCache = new FiboCache();
    }

    private static class FiboCache {
        int n = Integer.MAX_VALUE; // число, для которого рассчитываем Фибоначчи
        BigInteger f1; // предыдущее число Фибоначчи
        BigInteger f2; // число Фибоначчи
    }

    //1.2 Метод BigInteger fibonacci(int n)
    //Реализовать алгоритм вычисления n-го числа фибоначчи в BigInteger.
    // Последовательность чисел Фибоначчи, это когда каждое последующее число равно сумме двух предыдущих чисел.
    // Первые 2 числа последовательности 1, 1. Итого получаем 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144 и т.д.
    static BigInteger fibonacci(int n) {
        if (n == 0) return BigInteger.ZERO;
        if (fiboCache.n == n) return fiboCache.f2;
        boolean fromCache = fiboCache.n < n;
        BigInteger f1 = fromCache ? fiboCache.f1 : BigInteger.ZERO;
        BigInteger f2 = fromCache ? fiboCache.f2 : BigInteger.ONE;
        int i = fromCache ? fiboCache.n : 1;
        while (i++ < n) {
            BigInteger r = f2.add(f1);
            f1 = f2;
            f2 = r;
        }
        fiboCache.n = n;
        fiboCache.f1 = f1;
        fiboCache.f2 = f2;
        return f2;
    }

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1.21");
        BigDecimal b = new BigDecimal("0.5");

    }
}
