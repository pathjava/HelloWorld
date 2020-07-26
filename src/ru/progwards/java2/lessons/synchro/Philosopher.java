// Oleg Kiselev
// 25.07.2020, 20:21

package ru.progwards.java2.lessons.synchro;

public class Philosopher {

    private String name;
    private Fork right;
    private Fork left;
    private long reflectTime;
    private long eatTime;
    private long reflectSum;
    private long eatSum;

    public Philosopher(long reflectTime, long eatTime) {
        this.reflectTime = reflectTime;
        this.eatTime = eatTime;
        reflectSum += reflectTime;
        eatSum += eatTime;
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

    public void reflect() {
        System.out.println("Thinking " + name);
    }

    public void eat() {
        System.out.println("Eating " + name);
    }
}
