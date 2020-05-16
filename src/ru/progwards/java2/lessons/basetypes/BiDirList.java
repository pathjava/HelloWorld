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

    public ListItem<T> getTail() {
        return tail;
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

        public ListItem<T> getNext() {
            return next;
        }

        public ListItem<T> getPrev() {
            return prev;
        }
    }

    public void addLast(T item) {
        final ListItem<T> last = tail;
        final ListItem<T> newItem = new ListItem<>(item);
        tail = newItem;
        if (last == null)
            head = newItem;
        else {
            last.next = newItem;
            last.prev = last;
        }
        size++;
    }

    public void addFirst(T item) {
        final ListItem<T> first = head;
        final ListItem<T> newItem = new ListItem<>(item);
        head = newItem;
        if (first == null)
            tail = newItem;
        else {
            first.prev = newItem;
            first.next = first;
        }
        size++;
    }

    public void remove(T item) {
        final ListItem<T> removeItem = new ListItem<>(item);
        final ListItem<T> next = removeItem.next;
        final ListItem<T> prev = removeItem.prev;

        if (prev == null)
            head = next;
        else {
            prev.next = next;
            removeItem.prev = null;
        }

        if (next == null)
            tail = prev;
        else {
            next.prev = prev;
            removeItem.next = null;
        }
        removeItem.item = null;

        size--;
    }

    public int size() {
        return size;
    }


    public static void main(String[] args) {
        BiDirList<Integer> list = new BiDirList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        list.remove(2);


        BiDirList<Integer>.ListItem<Integer> currentHead = list.getHead();
        while (currentHead != null) {
            System.out.println(currentHead.getItem());
            currentHead = currentHead.getNext();
        }
        System.out.println("--------------------");

//        list.addFirst(4);
//        list.addFirst(5);
//        list.addFirst(6);
//
//        BiDirList<Integer>.ListItem<Integer> currentTail = list.getTail();
//        while (currentTail != null) {
//            System.out.println(currentTail.getItem());
//            currentTail = currentTail.getPrev();
//        }


        System.out.println("Size = " + list.size());
    }

}
