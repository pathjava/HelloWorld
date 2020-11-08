package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAlgebra {
    static BigDecimal fastPow(BigDecimal num, int pow) {
        return num.pow(pow);
    }

    static BigInteger fibonacci(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 1) {
            return BigInteger.ONE;
        } else
            return fibonacci(n - 1).add(fibonacci(n - 2));
    }

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("1.25");
//        int value = 17;
        System.out.println(fastPow(bigDecimal, 17));
        System.out.println(fibonacci(20));
    }
}
