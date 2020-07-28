// Oleg Kiselev
// 25.07.2020, 20:22

package ru.progwards.java2.lessons.synchro;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Symposium {

    private final List<Philosopher> philosophers = new LinkedList<>(); /* лист философов */
    private final ExecutorService executor;

    public Symposium(long reflectTime, long eatTime, int count) {
        List<Fork> forks = IntStream.range(0, count).mapToObj(i -> new Fork()).collect(Collectors.toList()); /* создаем вилки */
        for (int i = 0; i < count; i++) { /* создаем философов и распределяем между ними вилки */
            int fSize = forks.size();
            Philosopher philosopher = new Philosopher(reflectTime, eatTime);
            philosopher.setLeft(philosophers.size() == fSize - 1 ? forks.get(0) : forks.get(i + 1));
            philosopher.setRight(philosophers.size() == fSize - 1 ? forks.get(fSize - 1) : forks.get(i));
            philosopher.setName("Философ " + (i + 1));
            philosophers.add(philosopher);
        }
        executor = Executors.newFixedThreadPool(count); /* инициализируем пул количеством потоков */
    }

    public void start() { /* запускаем философский обед */
        for (Philosopher philosopher : philosophers) {
            executor.execute(() -> {
                while (!executor.isShutdown()) { /* выполняем цикл пока не выполнено завершение через shutdown() */
                    philosopher.eat(); /* запускаем прием пищи */
                    philosopher.reflect(); /* запускаем раздумья */
                }
            });
        }
    }

    public void stop() { /* завершаем философский обед */
        executor.shutdown();
    }

    public void print() {
        try { /* даем время для завершения всех потоков */
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long sumRef = sumReflections();
        long sumEat = sumEating();
        for (Philosopher philosopher : philosophers) { /* выводим информацию о философах - время размышлений и приема пищи */
            System.out.println(philosopher.getName() + ", ел " + philosopher.getEatSum()
                    + " (" + getPercent(philosopher.getEatSum(), sumEat) + "%)"
                    + ", размышлял " + philosopher.getReflectSum()
                    + " (" + getPercent(philosopher.getReflectSum(), sumRef) + "%)");
        }
    }

    private long sumReflections() {
        return philosophers.stream().mapToInt(philosopher -> (int) philosopher.getReflectSum()).sum();
    }

    private long sumEating() {
        return philosophers.stream().mapToInt(philosopher -> (int) philosopher.getEatSum()).sum();
    }

    private int getPercent(long sumPhilosopher, long sumTotal) {
        return (int) ((sumPhilosopher * 100) / sumTotal);
    }


    public static void main(String[] args) {
        Symposium symposium = new Symposium(500, 500, 5);
        symposium.start(); /* запускаем трапезу */
        try { /* даем время на прием пищи и размышления */
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        symposium.stop(); /* останавливаем трапезу */
        symposium.print(); /* выводим результат в консоль */
    }
}
