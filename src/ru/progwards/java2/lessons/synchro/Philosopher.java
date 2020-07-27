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

    public void run() {
        while (true) {
            reflect();
            takeLeftFork();
            takeRightFork();
            eat();
            putLeftFork();
            putRightFork();
        }
    }

    public void reflect() {
        System.out.println("Thinking " + name);
        try {
            reflectSum += reflectTime;
            TimeUnit.MILLISECONDS.sleep(reflectTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eat() {
        System.out.println("Eating " + name);
        try {
            eatSum += eatTime;
            TimeUnit.MILLISECONDS.sleep(eatTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void takeLeftFork() {
        if (!left.getUsedFork())
            left.setUsedFork(true);
    }

    private void takeRightFork() {
        if (!right.getUsedFork())
            right.setUsedFork(true);
    }

    private void putLeftFork() {
        left.setUsedFork(false);
    }

    private void putRightFork() {
        right.setUsedFork(false);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fork getRight() {
        return right;
    }

    public void setRight(Fork right) {
        this.right = right;
    }

    public Fork getLeft() {
        return left;
    }

    public void setLeft(Fork left) {
        this.left = left;
    }

    public long getReflectTime() {
        return reflectTime;
    }

    public long getEatTime() {
        return eatTime;
    }

    public long getReflectSum() {
        return reflectSum;
    }

    public long getEatSum() {
        return eatSum;
    }
}
