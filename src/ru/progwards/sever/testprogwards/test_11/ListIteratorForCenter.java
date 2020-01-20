package ru.progwards.sever.testprogwards.test_11;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorForCenter {
    static final int ELEMENT_COUNT = 250_000;

    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<>();
        ListIterator<Integer> listIterator = linkedList.listIterator();

        long start = System.currentTimeMillis();

        for (int i = 0; linkedList.size() < ELEMENT_COUNT; i++) {
            if (listIterator.previousIndex() >= linkedList.size() / 2)
                listIterator.previous();
            listIterator.add(i);
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
