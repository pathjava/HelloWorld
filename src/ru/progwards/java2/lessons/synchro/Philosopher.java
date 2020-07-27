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

    public void reflect() { /* философ думает */
        System.out.println("Думает " + name);
        try {
            reflectSum += reflectTime; /* инкрементируем общее время размышлений философа */
            TimeUnit.MILLISECONDS.sleep(reflectTime); /* время, которое думает философ */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eat() { /* философ ест */
        if (left.takeFork()) { /* пробуем взять левую вилку */
            if (right.takeFork()) { /* пробуем взять правую вилку */
                System.out.println("Ест " + name);
                try {
                    eatSum += eatTime; /* инкрементируем общее время приема пищи философа */
                    TimeUnit.MILLISECONDS.sleep(eatTime); /* время, которое ест философ */
                    left.putFork(); /* кладем левую вилку */
                    right.putFork(); /* кладем правую вилку */
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else /* если правую не взяли, кладем обратно левую */
                left.putFork();
        }
    }

    /* геттеры и сеттеры */
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
