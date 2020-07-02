// Oleg Kiselev
// 02.07.2020, 9:36

package ru.progwards.java2.lessons.threads;

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
                System.out.println("print " + name + pages);
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
                System.out.println("scan " + name + pages);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Thread printThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++)
                    print("doc: ", i);
            }
        });

        Thread scanThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++)
                    scan("doc: ", i);
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
