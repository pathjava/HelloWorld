// Oleg Kiselev
// 15.05.2020, 19:42

package ru.progwards.java2.lessons.basetypes;


public class BiDirList<T> {

    private int size = 0;
    private ListItem<T> head;
    private ListItem<T> tail;

    public ListItem<T> getHead() {
        return head;
    }

    class ListItem<T> {

        private T item;
        private ListItem<T> next;
        private ListItem<T> prev;

        public ListItem(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }

        public void setNext(ListItem<T> item) {
            next = item;
        }

        public ListItem<T> getNext() {
            return next;
        }

        public void setPrev(ListItem<T> prev) {
            this.prev = prev;
        }

        public ListItem<T> getPrev() {
            return prev;
        }
    }

    public void addLast(T item) {
        final ListItem<T> li = new ListItem<>(item);
        if (head == null) {
            head = li;
        } else {
            tail.setNext(li);
            head.setPrev(li);
        }
        tail = li;
        size++;
    }

    public void addFirst(T item) {
        final ListItem<T> li = new ListItem<>(item);
        if (tail == null) {
            tail = li;
        } else {
            head.setPrev(li);
        }
        head = li;
        size++;
    }

    public int size(){
        return size;
    }


    public static void main(String[] args) {
        BiDirList<Integer> list = new BiDirList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        list.addFirst(4);
        list.addFirst(5);
        list.addFirst(6);

//        BiDirList<Integer>.ListItem<Integer> current = list.getHead();
//        while (current != null) {
//            System.out.println(current.getItem());
//            current = current.getNext();
//        }


        System.out.println("Size = " + list.size());
    }

}
