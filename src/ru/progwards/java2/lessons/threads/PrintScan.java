// Oleg Kiselev
// 02.07.2020, 9:36

package ru.progwards.java2.lessons.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class PrintScan {

    //-------------Synchronized
    private static final Object lockPrint = new Object();
    private static final Object lockScan = new Object();

    private static void print(String name, int pages) {
        checkArgument(name, pages);
        synchronized (lockPrint) {
            try {
                Thread.sleep(50);
                System.out.println("print " + name + " " + pages);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void scan(String name, int pages) {
        checkArgument(name, pages);
        synchronized (lockScan) {
            try {
                Thread.sleep(70);
                System.out.println("scan " + name + " " + pages);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //-------------Lock

    private static final Lock printLock = new ReentrantLock();
    private static final Lock scanLock = new ReentrantLock();

    private static void printTwo(String name, int pages) {
        checkArgument(name, pages);
        printLock.lock();
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            System.out.println("print " + name + " " + pages);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            printLock.unlock();
        }
    }

    private static void scanTwo(String name, int pages) {
        checkArgument(name, pages);
        scanLock.lock();
        try {
            TimeUnit.MILLISECONDS.sleep(70);
            System.out.println("scan " + name + " " + pages);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            scanLock.unlock();
        }
    }

    private static void checkArgument(String name, int pages) {
        if (pages < 1)
            throw new IllegalArgumentException("Количество страниц не может быть меньше 1!");
        if (name.isEmpty())
            throw new NullPointerException("Имя файла не может быть пустым!");
    }


    public static void main(String[] args) {
        List<String> cityNames = new ArrayList<>(List.of("Москва", "Санкт-Петербург",
                "Новосибирск", "Екатеринбург", "Казань", "Нижний Новгород", "Челябинск",
                "Самара", "Омск", "Ростов-на-Дону"));

        int countThread = 10;

        //-------------Synchronized

        Thread printThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < countThread; i++)
                    print(cityNames.get(i), i + 1);
            }
        });

        Thread scanThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < countThread; i++)
                    scan(cityNames.get(i), i + 1);
            }
        });

        printThread.start();
        scanThread.start();

        try {
            printThread.join();
            scanThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Multithreading with synchronization completed!");

        //-------------Lock

        Thread printLockThread = new Thread(() -> {
            IntStream.range(0, countThread).forEachOrdered(i -> printTwo(cityNames.get(i), i + 1));
        });

        Thread scanLockThread = new Thread(() -> {
            IntStream.range(0, countThread).forEachOrdered(i -> scanTwo(cityNames.get(i), i + 1));
        });

        printLockThread.start();
        scanLockThread.start();

        try {
            printLockThread.join();
            scanLockThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Multithreading with Lock interface completed!");

        System.out.println("All tasks completed successfully!");
    }
}
