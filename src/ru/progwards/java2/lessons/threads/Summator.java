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

    private final int count; /* количество потоков */
    private BigInteger counter = BigInteger.ZERO; /* результат */
    private final Map<BigInteger, BigInteger> startAndStop = new HashMap<>(); /* хранение начала и окончания частей */
    private final List<BigInteger> tempResults = new ArrayList<>(); /* результаты операций сложения в потоках */

    public Summator(int count) {
        if (count < 1)
            throw new IllegalArgumentException("Количество потоков не может быть меньше 1!");
        this.count = count;
    }

    public BigInteger sum(BigInteger number) {
        if (number == null)
            throw new NullPointerException("Значение number не может быть пустым!");
        creatorParts(number);
        creatorThreads();
        for (BigInteger result : tempResults) /* складываем результаты из потоков */
            counter = counter.add(result);
        return counter;
    }

    private void creatorParts(BigInteger number) {
        BigInteger numStart = BigInteger.ONE;
        BigInteger numEnd = null;
        if (count > 1) { /* если потоков больше 1 */
            BigInteger remainder = number.mod(BigInteger.valueOf(count)); /* получаем остаток от деления на кол-во потоков */
            for (int i = 0; i < count; i++) {
                BigInteger partOfTheNumber = number.divide(BigInteger.valueOf(count));
                BigInteger partLastOfTheNumber = null;
                if (i == count - 1) { /* добавляем к последнему результату деления остаток от деления */
                    if (remainder.compareTo(BigInteger.ZERO) == 0) /* если остаток от деления == 0 */
                        partLastOfTheNumber = partOfTheNumber;
                    else /* если остаток от деления больше 0 */
                        partLastOfTheNumber = partOfTheNumber.add(remainder);
                }
                if (i == 0) { /* если результат деления первый */
                    numEnd = partOfTheNumber;
                    startAndStop.put(numStart, numEnd);
                } else if (i < count - 1) { /* если результат деления не последний */
                    numStart = numEnd.add(BigInteger.ONE);
                    numEnd = numStart.add(partOfTheNumber.subtract(BigInteger.ONE));
                    startAndStop.put(numStart, numEnd);
                } else { /* последний результат деления */
                    numStart = numEnd.add(BigInteger.ONE);
                    assert partLastOfTheNumber != null;
                    numEnd = numStart.add(partLastOfTheNumber.subtract(BigInteger.ONE));
                    startAndStop.put(numStart, numEnd);
                }
            }
        } else
            startAndStop.put(numStart, number);
    }

    private void creatorThreads() {
        Lock lock = new ReentrantLock();
        Thread[] threads = new Thread[count]; /* массив потоков по количеству из count */
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock(); /* блокируем поток */
                    try {
                        Map.Entry<BigInteger, BigInteger> entry = startAndStop.entrySet().iterator().next();
                        BigInteger startNumber = entry.getKey(); /* стартовое значение для суммирования */
                        BigInteger stopNumber = entry.getValue(); /* конечное значение для суммирования */
                        BigInteger result = BigInteger.ZERO;
                        for (BigInteger i = startNumber; i.compareTo(stopNumber) <= 0; i = i.add(BigInteger.ONE)) {
                            result = result.add(i);
                            startAndStop.remove(startNumber);
                        }
                        tempResults.add(result); /* добавляем результат во временный ArrayList */
                    } finally {
                        lock.unlock(); /* снимаем блокировку */
                    }
                }
            });
            threads[i].start(); /* запускаем поток */
        }

        for (Thread thread : threads) {
            try {
                thread.join(); /* запускаем ожидание для всех потоков */
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* для сравнения правильности операции сложения в многопоточности */
//    public BigInteger sumTest(BigInteger number) {
//        BigInteger count = BigInteger.ZERO;
//        for (BigInteger i = BigInteger.ONE; i.compareTo(number) <= 0; i = i.add(BigInteger.ONE)) {
//            count = count.add(i);
//        }
//        return count;
//    }


    public static void main(String[] args) {
        Summator summator = new Summator(27);

        System.out.println(summator.sum(BigInteger.valueOf(1125)));

//        System.out.println();
//        System.out.println(summator.sumTest(BigInteger.valueOf(1125)));

    }
}