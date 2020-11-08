package ru.progwards.sever.testprogwards.test_13;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class Test_01 {
    public static ArrayDeque<Integer> array2queue(int[] a) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i : a) {
            arrayDeque.offer(i);
        }
        return arrayDeque;
    }


    static void dequeueTest() {
        ArrayDeque<Object> deque = new ArrayDeque<>();

        for (int i = 0; i <= 10; i++) {
            deque.offer(i);
            if (i % 2 == 0)
                deque.poll();
        }

        System.out.println(deque);
    }

    static void pqTest() {
        PriorityQueue<Object> pQueue = new PriorityQueue<>();
        pQueue.offer(10);
        pQueue.offer(1);
        pQueue.offer(9);
        pQueue.offer(3);
        System.out.println(pQueue);
    }


    public static void main(String[] args) {
        dequeueTest();
        pqTest();

        int[] arrayToDeque = new int[]{1, 5, 25, 17, 3, 12, 30};
        System.out.println(array2queue(arrayToDeque));
    }
}
