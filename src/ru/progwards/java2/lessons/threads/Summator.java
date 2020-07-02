// Oleg Kiselev
// 02.07.2020, 15:06

package ru.progwards.java2.lessons.threads;

import java.math.BigInteger;

public class Summator {

    private int count;
    private BigInteger counter;

    public Summator(int count) {
        this.count = count;
        counter = BigInteger.ZERO;
    }

    public BigInteger sum(BigInteger number){
        for (BigInteger i = BigInteger.ZERO; i.compareTo(number) < 0; i = i.add(BigInteger.ONE)) {
            counter = counter.add(BigInteger.ONE);
        }
        return counter;
    }


    public static void main(String[] args) {
        Summator summator = new Summator(10);
        System.out.println(summator.sum(BigInteger.valueOf(10)));
    }
}
