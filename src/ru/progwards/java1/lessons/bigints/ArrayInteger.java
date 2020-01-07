package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayInteger {
//    byte[] digits;
//    int putVal;
//
//    ArrayInteger(int n){
//        digits = new byte[n];
//        putVal = 0;
//    }
//
//    void fromInt(BigInteger value){
//        if (value.compareTo(BigInteger.ZERO) == 0)
//            return;
//        while (value.compareTo(BigInteger.ZERO) > 0) {
//            digits[putVal++] = value.mod(BigInteger.TEN).byteValueExact();
//            value = value.divide(BigInteger.TEN);
//        }
//    }
//
//    BigInteger toInt(){
//        BigInteger result = BigInteger.ZERO;
//        for (Byte b : digits) {
//            result = result.multiply(BigInteger.TEN);
//            result = result.add(BigInteger.valueOf(b));
//        }
//        return result;
//    }
//
//    boolean add(ArrayInteger num){
//        return true;
//    }

    private final static BigInteger BI_TEN = BigInteger.valueOf(10);
    private Byte[] digits;

    public ArrayInteger(final int n){
        digits = new Byte[n];
    }

    public void fromInt(BigInteger value) {
        final List<Byte> list = new ArrayList<>();
        while (!value.equals(BigInteger.ZERO)) {
            list.add(value.mod(BI_TEN).byteValueExact());
            value = value.divide(BI_TEN);
        }
        Collections.reverse(list);
        digits = list.toArray(new Byte[list.size()]);
    }

    public BigInteger toInt(){
        BigInteger result = BigInteger.valueOf(0);
        for (Byte b : digits) {
            result = result.multiply(BI_TEN);
            result = result.add(BigInteger.valueOf(b));
        }
        return result;
    }

    public boolean add(ArrayInteger num){
        int ten = 0;
        final List<Byte> list = new ArrayList<>();
        for (int i = Math.max(num.digits.length, this.digits.length)-1; i >=0 ; i--) {
            int result = ((num.digits.length<=i) ? 0 : num.digits[i]) + ((this.digits.length<=i) ? 0 : this.digits[i]) + ten;
            ten = result>9 ? 1 : 0;
            list.add((byte)(result%10));
        }
        Collections.reverse(list);
        digits = list.toArray(new Byte[list.size()]);
        return true;
    }

    @Override
    public String toString() {
        return digits==null ? null : Arrays.toString(digits);
    }
}
