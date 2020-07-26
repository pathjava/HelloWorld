// Oleg Kiselev
// 25.07.2020, 20:22

package ru.progwards.java2.lessons.synchro;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Symposium implements Runnable {

    private long reflectTime;
    private long eatTime;
    private final LinkedList<Philosopher> philosophers = new LinkedList<>();

    public Symposium(long reflectTime, long eatTime) {
        this.reflectTime = reflectTime;
        this.eatTime = eatTime;
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
        Symposium symposium = new Symposium(500, 500);
        ExecutorService executor = Executors.newFixedThreadPool(5);
    }
}
