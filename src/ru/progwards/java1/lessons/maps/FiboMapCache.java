package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.System.currentTimeMillis;

/* комментировать код ниже нет никакого желания.
 * данная задача для меня из разряда не воспринимаемых/ не понимаемых,
 * то есть, читаешь т/з и не понимаешь о чем оно, не можешь уловить сути,
 * выстроить в голове модель решения, последовательность действий и конечный результат.
 * решение ниже в большей степени было реализовано на консультации с Никитой,
 * я его доделал в рабочее согласно т/з, но даже доведя "до ума" не совсем понимаю как это получилось.
 * понимаю, что в идеале сделать проверку при запуске, есть ли уже рассчитанное значение
 * и если нет, проверять ближайшее в меньшую сторону, что можно сделать через lowerEntry,
 * но для этого необходимо полное понимание задачи, а не только отдельной части кода.
 *
 * удивительно, данная задача первая в Д/З и самая низкая по оценке, что подразумевает ее простоту,
 * но для меня было гораздо интереснее/проще решить вторую и третьи задачи,
 * у которых при прочтении т/з сразу строилась картина будущего решения.
 *
 * не знаю почему, но некоторые задачи ставяят в тупик не самой сложностью, а пониманием
 * условия/цели задачи. */

public class FiboMapCache {
    private Map<Integer, BigDecimal> fiboCache;
    private boolean cacheOn;

    public FiboMapCache(boolean cacheOn) {
        this.cacheOn = cacheOn;
        clearCahe();
    }

    public BigDecimal fiboNumber(int n) {
        if (cacheOn && fiboCache != null) {
            BigDecimal cacheResult = fiboCache.get(n);
            if (cacheResult != null) {
                return cacheResult;
            }
        }

        BigDecimal a = BigDecimal.ZERO;
        BigDecimal b = BigDecimal.ONE;
        BigDecimal c;

        int i = 1;
        while (i++ < n) {
            c = b;
            b = b.add(a);
            a = c;
        }

        if (cacheOn) {
            if (fiboCache == null) {
                fiboCache = new TreeMap<>();
            }
            fiboCache.put(n, b);
        }

        return b;
    }

    public void clearCahe() {
        fiboCache = null;
    }

    public static void test() {
        FiboMapCache fibo;

        long start = currentTimeMillis();
        fibo = new FiboMapCache(false);
        for (int i = 1; i <= 1000; i++)
            fibo.fiboNumber(i);
        System.out.println("fiboNumber cacheOn=" + false + " время выполнения " + (currentTimeMillis() - start));

        start = currentTimeMillis();
        fibo = new FiboMapCache(true);
        for (int i = 1; i <= 1000; i++)
            fibo.fiboNumber(i);
        System.out.println("fiboNumber cacheOn=" + true + " время выполнения " + (currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        FiboMapCache test = new FiboMapCache(false);
        System.out.println(test.fiboNumber(20));
        test();
    }
}
