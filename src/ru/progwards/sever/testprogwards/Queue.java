// Oleg Kiselev
// 06.05.2020, 14:50

package ru.progwards.sever.testprogwards;

public class Queue {
    char[] q;
    int putLoc;
    int getLoc;

    public Queue(int size) {
        q = new char[size];
        putLoc = 0;
        getLoc = 0;
    }

    void put(char ch) {
        if (putLoc == q.length) {
            System.out.println(" - очередь заполнена");
            return;
        }
        q[putLoc++] = ch;
    }

    char get() {
        if (getLoc == putLoc) {
            System.out.println(" - очередь пустая");
            return (char) 0;
        }
        return q[getLoc++];
    }

    public static void main(String[] args) {
        Queue bigQ = new Queue(100);
        Queue smallQ = new Queue(4);
        char ch;
        int i;

        System.out.println("использование очереди bigQ для сохраненя алфавита");
        for (i = 0; i < 26; i++) {
            bigQ.put((char) ('A' + i));
        }
        System.out.print("содержимое очереди bigQ: ");
        for (i = 0; i < 26; i++) {
            ch = bigQ.get();
            if (ch != (char) 0) System.out.print(ch);
        }
        System.out.println("\n");

        System.out.println("использование очереди smallQ для генерации ошибок");
        for (i = 0; i < 5; i++) {
            System.out.print("попытка сохранения " + (char) ('Z' - i));
            smallQ.put((char) ('Z' - i));
            System.out.println();
        }
        System.out.println();

        System.out.print("содержимое очереди smallQ: ");
        for (i = 0; i < 5; i++) {
            ch = smallQ.get();

            if (ch != (char) 0) System.out.print(ch);
        }
    }
}
