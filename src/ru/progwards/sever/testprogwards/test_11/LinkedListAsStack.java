package ru.progwards.sever.testprogwards.test_11;

import java.util.LinkedList;

public class LinkedListAsStack {
    public static void main(String[] args) {
        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = 1; i <= 5; i++) {
            System.out.println("Put to stack " + i);
            stack.push(i);
        }

        Integer current = stack.poll();
        while (current != null) {
            System.out.println("Take from stack " + current);
            current = stack.poll();
        }
    }
}
