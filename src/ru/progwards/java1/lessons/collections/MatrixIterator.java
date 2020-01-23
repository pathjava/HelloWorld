package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MatrixIterator<T> implements Iterator<T> {
    private T[][] array;
    int indexHor;
    int indexVer;

    MatrixIterator(T[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return indexHor < array.length;
    }

    @Override
    public T next() {
        return this.hasNext() ? array[indexVer++][indexHor++] : null;
    }

    public static void main(String[] args) {
        MatrixIterator<Integer> iterator =
                new MatrixIterator<>(new Integer[][]{{1,2,3,4,5,6,7,8,9,10},{11,12,13,14,15,16,17,18,19,20}});
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
