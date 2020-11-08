package ru.progwards.sever.testprogwards.arrays.v1.javatrial;

abstract public class AArray<T> {
    public enum CopyType {ITEM, ARRAYS, SYSTEM}

    ;

    CopyType copy;

    public AArray(CopyType copy) {
        this.copy = copy;
    }

    abstract public void add(T item);

    abstract public T get(int index);

    abstract public int size();
}
