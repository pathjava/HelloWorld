// Oleg Kiselev
// 02.07.2020, 9:36

package ru.progwards.java2.lessons.threads;

public class PrintScan {

    private static synchronized void print(String name, int pages) {
        try {
            Thread.sleep(50);
            System.out.println("print " + name + pages);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void scan(String name, int pages) {
        try {
            Thread.sleep(70);
            System.out.println("scan " + name + pages);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

        System.out.println("Tasks completed successfully!");
    }
}
