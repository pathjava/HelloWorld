// Oleg Kiselev
// 02.07.2020, 15:06

package ru.progwards.java2.lessons.threads;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Summator {

    private final int count;
    private BigInteger counter = BigInteger.ONE;
    private final Map<BigInteger, BigInteger> startAndStop = new HashMap<>();
    private final List<BigInteger> tempResults = new ArrayList<>();

    public Summator(int count) {
        this.count = count;
    }

    public BigInteger sum(BigInteger number) {
        creatorParts(number);
        creatorThreads();
        for (BigInteger result : tempResults)
            counter = counter.add(result);
        return counter;
    }

    private void creatorParts(BigInteger number) {
        BigInteger tempStart = BigInteger.ONE;
        BigInteger tempEnd = null;
        if (count > 1) {
            BigInteger remainder = number.mod(BigInteger.valueOf(count));
            for (int i = 0; i < count; i++) {
                BigInteger partOfTheNumber = number.divide(BigInteger.valueOf(count));
                BigInteger partLastOfTheNumber = null;
                if (i == count - 1) {
                    if (remainder.compareTo(BigInteger.ZERO) == 0)
                        partLastOfTheNumber = partOfTheNumber;
                    else
                        partLastOfTheNumber = partOfTheNumber.add(remainder);
                }
                if (i == 0) {
                    tempEnd = partOfTheNumber;
                    startAndStop.put(tempStart, tempEnd);
                } else if (i < count - 1) {
                    tempStart = tempEnd.add(BigInteger.ONE);
                    tempEnd = tempStart.add(partOfTheNumber.subtract(BigInteger.ONE));
                    startAndStop.put(tempStart, tempEnd);
                } else {
                    tempStart = tempEnd.add(BigInteger.ONE);
                    assert partLastOfTheNumber != null;
                    tempEnd = tempStart.add(partLastOfTheNumber.subtract(BigInteger.ONE));
                    startAndStop.put(tempStart, tempEnd);
                }
            }
        } else
            startAndStop.put(tempStart, number);
    }

    private void creatorThreads() {
        Lock lock = new ReentrantLock();
        Thread[] threads = new Thread[count];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try {
                        Map.Entry<BigInteger, BigInteger> entry = startAndStop.entrySet().iterator().next();
                        BigInteger startNumber = entry.getKey();
                        BigInteger stopNumber = entry.getValue();
                        BigInteger result = startNumber;
                        for (BigInteger i = startNumber; i.compareTo(stopNumber) <= 0; i = i.add(BigInteger.ONE)) {
                            result = result.add(i);
                            startAndStop.remove(startNumber);
                        }
                        tempResults.add(result);
                    } finally {
                        lock.unlock();
                    }
                }
            });
            threads[i].start();
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