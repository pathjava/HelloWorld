package ru.progwards.sever.testprogwards.arrays.v2.javatrial;

import java.util.Arrays;

public class BArray<T extends Object> extends DArray {
    int size;
    int blockSize;

    public BArray(CopyType copy, int inilialSize, int blockSize) {
        super(copy);
        this.blockSize = blockSize;
        array = (T[]) new Object[inilialSize];
    }

    @Override
    public void add(Object item) {
        if (array.length == size)
            array = copyData(array.length + blockSize);
        size++;
        array[size - 1] = (T) item;
    }

    @Override
    public int size() {
        return size;
    }
}
