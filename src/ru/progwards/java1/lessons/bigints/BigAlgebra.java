package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAlgebra {
    static BigDecimal fastPow(BigDecimal num, int pow){
        return num.pow(pow);
    }

    static BigInteger fibonacci(int n){
        if (n == 0 || n == 1) {
            return BigInteger.ONE;
        }
        return fibonacci(n - 1).add(fibonacci(n - 2));
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(20));
    }
}
