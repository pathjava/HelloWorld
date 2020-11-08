package ru.progwards.sever.testprogwards.test_11;

import java.util.Iterator;

public class ArrayIterator implements Iterator<Integer> {
    private Integer[][] m;
    private int i, j;

    public ArrayIterator(Integer[][] m) {
        this.m = m;
    }

    @Override
    public boolean hasNext() {
        return i < m.length && j < m[i].length;
    }

    @Override
    public Integer next() {
        Integer r = m[i][j++];
        if (j >= m[i].length) {
            i++;
            j = 0;
        }
        return r;
    }


    public static void main(String[] args) {
        Integer[][] array = new Integer[][]{{1}, {2, 3, 4, 5,}, {6, 7}, {8, 9, 10, 11, 12,}};
        ArrayIterator i = new ArrayIterator(array);
        for (; i.hasNext(); ) {
            System.out.println(i.next());
        }
    }
}
