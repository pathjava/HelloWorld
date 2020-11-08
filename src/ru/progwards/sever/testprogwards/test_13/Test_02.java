package ru.progwards.sever.testprogwards.test_13;

import java.util.*;

public class Test_02 {
    public static int sumLastAndFirst(ArrayDeque<Integer> deque) {
        int result = 0;
        if (!deque.isEmpty()) {
            result = deque.peekFirst() + deque.peekLast();
        }
        return result;
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        Collections.addAll(arrayDeque, 5, 1, 11, 25, 7, 1, 12, 28, 35, 3);
        System.out.println(arrayDeque);
        System.out.println(sumLastAndFirst((ArrayDeque<Integer>) arrayDeque));
    }
}
