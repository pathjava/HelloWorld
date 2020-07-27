// Oleg Kiselev
// 25.07.2020, 20:21

package ru.progwards.java2.lessons.synchro;

import java.util.concurrent.TimeUnit;

public class Philosopher {

    private String name;
    private Fork right;
    private Fork left;
    private final long reflectTime;
    private final long eatTime;
    private long reflectSum;
    private long eatSum;

    public Philosopher(long reflectTime, long eatTime) {
        this.reflectTime = reflectTime;
        this.eatTime = eatTime;
    }

    public void reflect() {
        System.out.println("Думает " + name);
        try {
            reflectSum += reflectTime;
            TimeUnit.MILLISECONDS.sleep(reflectTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eat() {
        if (left.takeFork()) {
            if (right.takeFork()) {
                System.out.println("Ест " + name);
                try {
                    eatSum += eatTime;
                    TimeUnit.MILLISECONDS.sleep(eatTime);
                    left.putFork();
                    right.putFork();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else
                left.putFork();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRight(Fork right) {
        this.right = right;
    }

    public void setLeft(Fork left) {
        this.left = left;
    }

    public long getReflectSum() {
        return reflectSum;
    }

    public long getEatSum() {
        return eatSum;
    }
}
