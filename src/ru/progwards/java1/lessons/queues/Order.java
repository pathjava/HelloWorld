package ru.progwards.java1.lessons.queues;

public class Order {
    private double sum;
    private int num;
    static int numGen = 1;

    public Order(double sum) {
        this.sum = sum;
        this.num = numGen++;
    }

    public double getSum() {
        return sum;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return sum + "(" + num + ")";
    }
}
