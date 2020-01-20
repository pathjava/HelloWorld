package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T> {
    private T[] array;

    MatrixIterator(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public T next() {
        // TODO Auto-generated method stub
        return null;
    }
}
