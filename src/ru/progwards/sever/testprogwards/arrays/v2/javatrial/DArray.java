package ru.progwards.sever.testprogwards.arrays.v2.javatrial;

import java.util.Arrays;

public class DArray<T extends Object> extends AArray {
    T[] array;

    public DArray(CopyType copy) {
        super(copy);
    }

    @Override
    public void add(Object item) {
        if (array != null)
            array = copyData(array == null ? 1 : array.length + 1);
        else
            array = (T[]) new Object[1];
        array[array.length - 1] = (T) item;
    }

    T[] copyData(int newSize) {
        switch (copy) {
            case ITEM:
                Object[] newArray = new Object[newSize];
                for (int i = 0; i < array.length; i++)
                    newArray[i] = array[i];
                return (T[]) newArray;
            case ARRAYS:
                return Arrays.copyOf(array, newSize);
            case SYSTEM:
                newArray = new Object[newSize];
                System.arraycopy(array, 0, newArray, 0, array.length);
                return (T[]) newArray;
        }
        return null;
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public int size() {
        return array.length;
    }
}
