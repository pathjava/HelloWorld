package ru.progwards.java1.lessons.bigints;

import java.math.BigInteger;

public class ArrayInteger {
    byte[] digits;
    int digitsNum = 0;
    boolean signNegative = false;

    ArrayInteger(int n){
        digits = new byte[n];
    }

    void fromInt(BigInteger value){
        signNegative = value.compareTo(BigInteger.ZERO) < 0;
        value = value.abs();
        digitsNum = 0;
        while (value.compareTo(BigInteger.ZERO) != 0) {
            digits[digitsNum++] = (value.mod(BigInteger.TEN)).byteValue();
            value = value.divide(BigInteger.TEN);
        }
    }

    BigInteger toInt(){
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < digitsNum; i++) {
            result = result.multiply(BigInteger.TEN);
            result = result.add(BigInteger.valueOf(digits[i]));
        }
        return result;
    }


    boolean add(ArrayInteger num) {
//        int res = digits[0] + num.digits[0];
//        digits[0] = (byte)(res % 10);
//        if (res > 9) {
//            if (digitsNum < 2) {
//                digitsNum = 2;
//                if (digits.length < digitsNum) {
//                    digitsNum = 0;
//                    return false;
//                }
//            }
//            digits[1] = 1;
//        }
//        return true;

        int result = 0;
        int outOfNine = 1;
        for (int i = 0, j = Math.max(digits.length, num.digits.length); i < j; i++){
            result = digits[i] + num.digits[i];
            if (result > 9)
                result = digits[i] + num.digits[i] + outOfNine;
        }
        return true;

//        result = ((num.digits.length <= i) ? 0 : num.digits[i]) + ((digits.length <= i) ? 0 : this.digits[i]) + outOfNine;
//        if (result > 9){
//            outOfNine = 1;
//            result %= 10;
//        } else {
//            outOfNine = 0;
//        }

    }

    @Override
    public String toString() {
        String result = ""; // 1 2 3
        for (int i = 0; i < digitsNum; i++) {
            result = digits[i] + result;
        }

        result = result.isEmpty() ? "0" : result;

        return signNegative ? "-" + result : result;
    }


    public static void main(String[] args) {
        ArrayInteger ai1 = new ArrayInteger(10);
        ArrayInteger ai2 = new ArrayInteger(10);

        ai1.fromInt(BigInteger.valueOf(5));
        System.out.println(ai1);

        ai2.fromInt(BigInteger.valueOf(50));

//        System.out.println(ai1.add(ai2));

        System.out.println(ai1);
    }
}
