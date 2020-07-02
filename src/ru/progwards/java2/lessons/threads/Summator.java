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

    // 100 / 3 == 0~33, 34~66, 67~100
    public BigInteger sum(BigInteger number) {
        BigInteger tempStart = BigInteger.ONE;
        BigInteger tempEnd = null;
        if (count > 1) {
            BigInteger remainder = number.mod(BigInteger.valueOf(count));
            for (int i = 0; i < count; i++) {
                BigInteger partOfTheNumber = number.divide(BigInteger.valueOf(count));
                if (i == 0) {
                    tempEnd = partOfTheNumber;
                    creatorThreads(BigInteger.ONE, partOfTheNumber);
                } else if (i < count - 1) {
                    tempStart = tempEnd.add(BigInteger.ONE);
                    tempEnd = tempStart.add(partOfTheNumber.subtract(BigInteger.ONE));
                    creatorThreads(partOfTheNumber.add(BigInteger.ONE), partOfTheNumber.add(partOfTheNumber));
                } else {
                    tempStart = tempEnd.add(BigInteger.ONE);
                    tempEnd = tempStart.add(partOfTheNumber);
                    creatorThreads(partOfTheNumber.add(BigInteger.ONE), partOfTheNumber.add(partOfTheNumber));
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
        System.out.println(summator.sum(BigInteger.valueOf(100)));
    }
}
