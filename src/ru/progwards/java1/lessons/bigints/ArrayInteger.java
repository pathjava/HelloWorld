package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;

public class ArrayInteger {
    byte[] digits;

    ArrayInteger(int n){
        digits = new byte[n];
    }

    void fromInt(BigInteger value){
//        this.digits = value.toByteArray();
//        if (putVal == digits.length){
//            return;
//        }
        digits = value.toByteArray();
    }

    BigInteger toInt(){
        byte[] rev = new byte[digits.length + 1];
        return new BigInteger(rev);
    }

    boolean add(ArrayInteger num){
        return true;
    }
}
