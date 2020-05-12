// Oleg Kiselev
// 09.05.2020, 10:08

package ru.progwards.java2.lessons.recursion;

public class HanoiTower {

    private int size;
    private int pos;

    public HanoiTower(int size, int pos) {
        this.size = size;
        this.pos = pos;
    }

    public void move(int from, int to) {
        int middle;
        if (to == 2)
            middle = 3;
        else {
            to = 3;
            middle = 2;
        }
        hanoi(size, from, to, middle);
    }

    public void hanoi(int n, int from, int to, int middle) {
        if (n == 0)
            return;

        hanoi(n - 1, from, middle, to);

        System.out.println(from + " " + to);

        hanoi(n - 1, middle, to, from);
    }

    public void print() {
        System.out.println();
    }

    public void setTrace(boolean on) {

    }


    public static void main(String[] args) {
        HanoiTower hanoiTower = new HanoiTower(3, 0);
        hanoiTower.move(1, 2);
    }
}
