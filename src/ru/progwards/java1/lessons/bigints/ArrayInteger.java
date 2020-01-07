package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;

public class ArrayInteger {
    byte[] digits;
    int putVal;

    ArrayInteger(int n){
        digits = new byte[n];
        putVal = 0;
    }

    void fromInt(BigInteger value){
        if (value.compareTo(BigInteger.ZERO) == 0)
            return;
        while (value.compareTo(BigInteger.ZERO) > 0) {
            digits[putVal++] = value.mod(BigInteger.TEN).byteValueExact();
            value = value.divide(BigInteger.TEN);
        }
    }

    BigInteger toInt(){
        BigInteger result = BigInteger.ZERO;
        for (Byte b : digits) {
            result = result.multiply(BigInteger.TEN);
            result = result.add(BigInteger.valueOf(b));
        }
        return result;
    }

    boolean add(ArrayInteger num){
        return true;
    }
}
