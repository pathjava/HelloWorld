// Oleg Kiselev
// 02.07.2020, 9:36

package ru.progwards.java2.lessons.threads;

import java.util.ArrayList;
import java.util.List;

public class PrintScan {

    private static final Object lockPrint = new Object();
    private static final Object lockScan = new Object();

    private static void print(String name, int pages) {
        if (pages < 1)
            throw new IllegalArgumentException("Количество страниц не может быть меньше 1!");
        if (name.isEmpty())
            throw new NullPointerException("Имя файла не может быть пустым!");
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
        if (pages < 1)
            throw new IllegalArgumentException("Количество страниц не может быть меньше 1!");
        if (name.isEmpty())
            throw new NullPointerException("Имя файла не может быть пустым!");
        synchronized (lockScan) {
            try {
                Thread.sleep(70);
                System.out.println("scan " + name + " " + pages);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        List<String> nameCities = new ArrayList<>(List.of("Москва", "Санкт-Петербург",
                "Новосибирск", "Екатеринбург", "Казань", "Нижний Новгород", "Челябинск",
                "Самара", "Омск", "Ростов-на-Дону"));

        Thread printThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++)
                    print(nameCities.get(i), i + 1);
            }
        });

        Thread scanThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++)
                    scan(nameCities.get(i), i + 1);
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

        System.out.println("All tasks completed successfully!");
    }
}
