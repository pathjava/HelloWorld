package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T> {
    private T[][] array;
    private int indexRow;
    private int indexCol;

    MatrixIterator(T[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return indexRow < array.length && indexCol < array[indexRow].length;
    }

    @Override
    public T next() {
        T nextValue = array[indexRow][indexCol++];
        if (indexCol >= array[indexRow].length) {
            indexRow++;
            indexCol = 0;
        }
        return nextValue;
    }

    public static void main(String[] args) {
        MatrixIterator<Integer> iterator =
                new MatrixIterator<>(new Integer[][]{{1,2,3,4,5,6,7,8,9,10},{11,12,13,14,15,16,17,18,19,20}});
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
