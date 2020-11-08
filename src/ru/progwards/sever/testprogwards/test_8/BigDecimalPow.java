package ru.progwards.sever.testprogwards.test_8;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigDecimalPow {
    public static void main(String[] args) {
        BigDecimal b = new BigDecimal(1.5);
        BigInteger e = new BigInteger("325322");
        BigDecimal result = pow(b, e);
        System.out.println("Done " + result.scale());
        System.out.println(result);
    }

    /**
     * Computes d to the power of e
     *
     * @param b The value
     * @param e The exponent
     * @return The power
     */
    private static BigDecimal pow(BigDecimal b, BigInteger e) {
        BigDecimal result = BigDecimal.ONE;
        BigDecimal p = b;
        int skipped = 0;
        while (e.compareTo(BigInteger.ZERO) > 0) {
            if (e.and(BigInteger.ONE).equals(BigInteger.ONE)) {
                if (skipped > 0) {

                    if (skipped > 29) {
                        p = pow(p, BigInteger.ONE.shiftLeft(skipped));
                    } else {
                        p = p.pow(1 << skipped);
                    }
                    skipped = 0;
                }
                result = result.multiply(p);
            }
            skipped++;
            e = e.shiftRight(1);
            System.out.println(e);
        }
        return result;
    }
}
