// Oleg Kiselev
// 27.05.2020, 12:44

package ru.progwards.sever.testprogwards2.test_06;

public class FiboWithRecursion {

    int prev;
    int current;

    public FiboWithRecursion(int prev, int current) {
        this.prev = prev;
        this.current = current;
    }

    FiboWithRecursion next() {
        return new FiboWithRecursion(current, prev + current);
    }

    @Override
    public String toString() {
        return String.valueOf(current);
    }

    public static FiboWithRecursion getFibo(int num) {
        switch (num) { // условие выхода
            case 1:
                return new FiboWithRecursion(0, 1);
            case 2:
                return new FiboWithRecursion(1, 1);
        }
        // рекурсия
        FiboWithRecursion preFibo = getFibo(num - 1);
        return preFibo.next();
    }

    public static void main(String[] args) {
        FiboWithRecursion fwr = FiboWithRecursion.getFibo(5);
        System.out.println(fwr);
    }
}
