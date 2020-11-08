// Oleg Kiselev
// 09.05.2020, 10:08

package ru.progwards.java2.lessons.recursion;

/*
 * метод hanoi - это взято из сети, просто, чтобы посмотреть как все это работает.
 * стал ли я ближе после этого к пониманию рекурсивного решения Ханойских башень - нет.
 * для меня вообще загадка условия задачи и параметры методов по умолчанию.
 * так как и мои личные размышления и в статьях и в решениях в сети, всегда это 4 параметра:
 * количество дисков и три штыря.
 * не было ни одной темы, которая бы вообще не заходила никак, как рекурсия.
 *
 * */

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

    /*
     * написал выше комментарий, снова прогнал по дебагу и стал немного понимать.
     * основная суть в смене позиций возвращаемых параметров в методах,
     * именно так происходит перестановка.
     * параметр из сигнатуры метода:
     * hanoi(int n, int from, int to, int middle)
     * и из рекурсивных вызовов:
     * hanoi(n - 1, from, middle, to);
     * и
     * hanoi(n - 1, middle, to, from);
     * */

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
