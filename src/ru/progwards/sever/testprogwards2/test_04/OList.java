// Oleg Kiselev
// 16.05.2020, 11:48

package ru.progwards.sever.testprogwards2.test_04;

import java.util.LinkedList;
import java.util.List;

public class OList<T> {

    class ListItem<T> {

        private T item;
        private ListItem<T> next;

        ListItem(T item) {
            this.item = item;
        }

        T getItem() {
            return item;
        }

        void setNext(ListItem<T> item) {
            next = item;
        }

        ListItem<T> getNext() {
            return next;
        }
    }

    private ListItem<T> head;
    private ListItem<T> tail;

    ListItem<T> getHead() {
        return head;
    }

    void add(T item) {
        ListItem<T> li = new ListItem<T>(item);
        if (head == null) {
            head = li;
            tail = li;
        } else {
            tail.setNext(li);
            tail = li;
        }
    }


    public static void main(String[] args) {
        OList<Integer> list = new OList<>();
        list.add(2);
        list.add(1);
        list.add(5);
        list.add(7);
        list.add(10);

//        LinkedList<String> list = new LinkedList<String>();
//
//        // use add() method to add elements in the list
//        list.add("Geeks");
//        list.add("for");
//        list.add("Geeks");
//        list.add("10");
//        list.add("20");
//
//        // Output the present list
//        System.out.println("The list is:" + list);
//
//        // Adding new elements at the beginning
//        list.addFirst("First");
//        list.addFirst("At");

//        OList<Integer>.ListItem<Integer> current = list.getHead();
//        while (current != null) {
//            System.out.println(current.getItem());
//            current = current.getNext();
//        }
    }
}