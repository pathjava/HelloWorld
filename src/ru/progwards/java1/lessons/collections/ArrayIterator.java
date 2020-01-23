package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {

    private T[] array;
    int index;

    ArrayIterator(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        if(index < array.length){
            return true;
        }
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public T next() {
        if(this.hasNext()){
            return array[index++];
        }
        // TODO Auto-generated method stub
        return null;
    }
}