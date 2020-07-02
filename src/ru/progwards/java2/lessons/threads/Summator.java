// Oleg Kiselev
// 02.07.2020, 15:06

package ru.progwards.java2.lessons.threads;

import java.math.BigInteger;

public class Summator {

    private final int count;
    private BigInteger counter;

    public Summator(int count) {
        this.count = count;
        counter = BigInteger.ZERO;
    }

    // 100 / 3 == 1~33, 34~66, 67~100
    // 98 / 3 == 1~32, 33~64, 65~98
    public BigInteger sum(BigInteger number) {
        BigInteger tempStart = BigInteger.ONE;
        BigInteger tempEnd = null;
        if (count > 1) {
            BigInteger remainder = number.mod(BigInteger.valueOf(count));
            for (int i = 0; i < count; i++) {
                BigInteger partOfTheNumber = number.divide(BigInteger.valueOf(count));
                BigInteger partLastOfTheNumber = null;
                if (i == count - 1) {
                    if (remainder.compareTo(BigInteger.TWO) == 0)
                        partLastOfTheNumber = partOfTheNumber.add(BigInteger.ONE);
                    else if (remainder.compareTo(BigInteger.ZERO) == 0)
                        partLastOfTheNumber = partOfTheNumber.subtract(BigInteger.ONE);
                    else
                        partLastOfTheNumber = partOfTheNumber;
                }
                if (i == 0) {
                    tempEnd = partOfTheNumber;
                    creatorThreads(BigInteger.ONE, partOfTheNumber);
                } else if (i < count - 1) {
                    tempStart = tempEnd.add(BigInteger.ONE);
                    tempEnd = tempStart.add(partOfTheNumber.subtract(BigInteger.ONE));
                    creatorThreads(partOfTheNumber.add(BigInteger.ONE), partOfTheNumber.add(partOfTheNumber));
                } else {
                    tempStart = tempEnd.add(BigInteger.ONE);
                    tempEnd = tempStart.add(partLastOfTheNumber);
                    creatorThreads(partOfTheNumber.add(BigInteger.ONE), partOfTheNumber.add(partLastOfTheNumber));
                }
            }
        }
        return counter;
    }

    private void creatorThreads(BigInteger startNumber, BigInteger stopNumber) {
        Thread[] threads = new Thread[count];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (BigInteger i = startNumber; i.compareTo(stopNumber) <= 0; i = i.add(BigInteger.ONE))
                        counter = counter.add(i);
                }
            });
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Summator summator = new Summator(3);
        System.out.println(summator.sum(BigInteger.valueOf(97)));
    }
}
