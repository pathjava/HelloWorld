package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAlgebra {
    static BigDecimal fastPow(BigDecimal num, int pow){
        return num.pow(pow);
    }
//
//    static BigInteger fibonacci(int n){
//
//    }

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(1.5);
        BigAlgebra bigAlgebra = new BigAlgebra();
        bigAlgebra.fastPow( bigDecimal, 53426);
        System.out.println(bigAlgebra.toString());
    }
}
