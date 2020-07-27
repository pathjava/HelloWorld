// Oleg Kiselev
// 25.07.2020, 20:22

package ru.progwards.java2.lessons.synchro;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Symposium {

    private final List<Philosopher> philosophers = new LinkedList<>();
    private final ExecutorService executor;

    public Symposium(long reflectTime, long eatTime, int count) {
        List<Fork> forks = IntStream.range(0, count).mapToObj(i -> new Fork()).collect(Collectors.toList());
        for (int i = 0; i < count; i++) {
            int fSize = forks.size();
            Philosopher philosopher = new Philosopher(reflectTime, eatTime);
            philosopher.setLeft(philosophers.size() == fSize - 1 ? forks.get(0) : forks.get(i + 1));
            philosopher.setRight(philosophers.size() == fSize - 1 ? forks.get(fSize - 1) : forks.get(i));
            philosopher.setName("Философ " + (i + 1));
            philosophers.add(philosopher);
        }
        executor = Executors.newFixedThreadPool(count);
    }

    public void start() {
        for (Philosopher philosopher : philosophers) {
            executor.execute(philosopher::run);
        }
    }

    public void stop() {
        executor.shutdown();
    }

    public void print() {
        for (Philosopher philosopher : philosophers) {
            System.out.println(philosopher.getName() + ", ел " + philosopher.getEatSum()
                    + ", размышлял " + philosopher.getReflectSum());
        }
    }


    public static void main(String[] args) {
        Symposium symposium = new Symposium(500, 500, 5);
        symposium.start();
        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        symposium.stop();
        symposium.print();
    }
}
