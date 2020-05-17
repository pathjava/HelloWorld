// Oleg Kiselev
// 15.05.2020, 19:42

package ru.progwards.java2.lessons.basetypes;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class BiDirList<T> {

    private int size = 0;
    private ItemContainer<T> head;
    private ItemContainer<T> tail;

    public BiDirList() {
        tail = new ItemContainer<>(null, null, head);
        head = new ItemContainer<>(null, tail, null);
    }

    class ItemContainer<T> {
        private T currentItem;
        private ItemContainer<T> nextItem;
        private ItemContainer<T> prevItem;

        public ItemContainer(T currentItem, ItemContainer<T> nextItem, ItemContainer<T> prevItem) {
            this.currentItem = currentItem;
            this.nextItem = nextItem;
            this.prevItem = prevItem;
        }

        public ItemContainer<T> getPrevItem() {
            return prevItem;
        }

        public void setPrevItem(ItemContainer<T> prevItem) {
            this.prevItem = prevItem;
        }

        public T getCurrentItem() {
            return currentItem;
        }

        public void setCurrentItem(T currentItem) {
            this.currentItem = currentItem;
        }

        public ItemContainer<T> getNextItem() {
            return nextItem;
        }

        public void setNextItem(ItemContainer<T> nextItem) {
            this.nextItem = nextItem;
        }

        @Override
        public String toString() {
            return "item by index = " + currentItem;
        }

    }

    public void addLast(T item) {
        ItemContainer<T> prev = tail;
        prev.setCurrentItem(item);
        tail = new ItemContainer<>(null, null, prev);
        prev.setNextItem(tail);
        size++;
    }

    public void addFirst(T item) {
        ItemContainer<T> next = head;
        next.setCurrentItem(item);
        head = new ItemContainer<>(null, next, null);
        next.setPrevItem(head);
        size++;
    }

    public void remove(T item) {
        if (item == null)
            throw new NullPointerException();

        ItemContainer<T> removeItem = findRemoveItem(item);
        if (removeItem == null)
            throw new NoSuchElementException();

        ItemContainer<T> prev = removeItem.prevItem;
        ItemContainer<T> next = removeItem.nextItem;

        if (prev.getPrevItem() == null) {
            next.setPrevItem(null);
            head.setNextItem(next);
        } else if (next.getNextItem() == null) {
            prev.setNextItem(null);
            tail.setPrevItem(prev);
        } else {
            prev.setNextItem(next);
            next.setPrevItem(prev);
        }
        size--;
    }

    private ItemContainer<T> findRemoveItem(T item) {
        ItemContainer<T> tempItem = head.getNextItem();
        for (int j = 0; j < size; j++) {
            if (tempItem.getCurrentItem().equals(item))
                return tempItem;
            else
                tempItem = tempItem.getNextItem();
        }
        return null;
    }

    public ItemContainer<T> at(int i) {
        ItemContainer<T> tempItem = head.getNextItem();
        for (int j = 0; j < i; j++) {
            tempItem = tempItem.getNextItem();
        }
        return tempItem;
    }

    public int size() {
        return size;
    }

    public Iterator<T> getIterator() {
        return new Iterator<T>() {
            private ItemContainer<T> current = head.getNextItem();

            @Override
            public boolean hasNext() {
                return current.getNextItem() != null;
            }

            @Override
            public T next() throws IndexOutOfBoundsException {
                T result = current.currentItem;
                if (!hasNext()) throw new IndexOutOfBoundsException("End of list.");
                current = current.nextItem;
                return result;
            }
        };
    }


    public static void main(String[] args) {
        BiDirList<String> list = new BiDirList<>();
//        list.addLast("1");
//        list.addLast("2");
//        list.addLast("3");
//        list.addLast("4");
//        list.addLast("5");

        list.addFirst("6");
        list.addFirst("5");
        list.addFirst("4");
        list.addFirst("3");
        list.addFirst("2");
        list.addFirst("1");

        System.out.println(list.at(2));

        System.out.println("List size = " + list.size());

        list.remove("3");

        System.out.println("List size = " + list.size());

        for (Iterator<String> it = list.getIterator(); it.hasNext(); ) {
            String s = it.next();
            System.out.println(s);
        }

    }
}
