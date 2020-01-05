package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayInteger {

    private final static BigInteger BI_TEN = BigInteger.valueOf(10);
    private final static BigDecimal BD_TEN = BigDecimal.valueOf(10);
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

    public BigDecimal toInt(){
        BigDecimal result = BigDecimal.valueOf(0);
        for (Byte b : digits) {
            result = result.multiply(BD_TEN);
            result = result.add(BigDecimal.valueOf(b));
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