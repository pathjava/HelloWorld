// Oleg Kiselev
// 25.07.2020, 20:22

package ru.progwards.java2.lessons.synchro;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Symposium implements Runnable {

    private final List<Fork> forks = new LinkedList<>();
    private final List<Philosopher> philosophers = new LinkedList<>();

    public Symposium(long reflectTime, long eatTime, int count) {
        for (int i = 0; i < count; i++) {
            Fork fork = new Fork();
            fork.setNumFork(i);
            forks.add(fork);
        }
        for (int i = 0; i < count; i++) {
            int fSize = forks.size();
            Philosopher philosopher = new Philosopher(reflectTime, eatTime);
            philosopher.setLeft(philosophers.size() == fSize - 1 ? forks.get(0) : forks.get(i + 1));
            philosopher.setRight(philosophers.size() == fSize - 1 ? forks.get(fSize - 1) : forks.get(i));
            philosopher.setName("Philosopher " + (i + 1));
            philosophers.add(philosopher);
        }
    }

    @Override
    public void run() {

    }

    public void start() {

    }

    public void stop() {

    }

    public void print() {

    }


    public static void main(String[] args) {
        Symposium symposium = new Symposium(500, 500, 5);
        ExecutorService executor = Executors.newFixedThreadPool(5);
    }
}
