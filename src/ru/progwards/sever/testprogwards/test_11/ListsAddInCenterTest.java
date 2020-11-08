package ru.progwards.sever.testprogwards.test_11;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ListsAddInCenterTest {
    //    static final int ELEMENT_COUNT = 250_000;
    static final int ELEMENT_COUNT = 25_000;

    /* тестирование скорости добавления элементов в середину массивов ArrayList и LinkedList*/
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        var startTime = new Date().getTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            arrayList.add(arrayList.size() / 2, i);
        }
        System.out.println("Добавление в середину ArrayList " + (new Date().getTime() - startTime));

        System.out.println();

        List<Integer> linkedList = new LinkedList<>();
        startTime = new Date().getTime();
        for (int i = 0; i < ELEMENT_COUNT; i++) {
            linkedList.add(linkedList.size() / 2, i);
        }
        System.out.println("Добавление в середину LinkedList " + (new Date().getTime() - startTime));
    }
}
