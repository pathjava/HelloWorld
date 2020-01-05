package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;

public class ArrayInteger {
    byte[] digits;
    int putVal;
//    int getVal;

    ArrayInteger(int n){
        digits = new byte[n];
        putVal = 0;
//        getVal = 0;
    }

    void fromInt(BigInteger value){
//        this.digits = value.toByteArray();
//        if (putVal == digits.length){
//            return;
//        }
        digits[putVal++] = value.byteValueExact();
    }

    BigInteger toInt(){
        byte[] rev = new byte[digits.length + 1];
//        if (getVal == putVal){
//            return BigInteger.ZERO;
//        }
        return new BigInteger(rev);
    }

    boolean add(ArrayInteger num){
        return true;
    }
}
