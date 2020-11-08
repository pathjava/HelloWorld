package ru.progwards.sever.testprogwards.arrays.v1.javatrial;

public class DArray<T extends Object> {
    T[] array;

    public void add(T item) {
        int newSize = array == null ? 1 : array.length + 1;
        Object[] newArray = new Object[newSize];
        if (array != null)
            copyData(array, newArray);
        array = (T[]) newArray;
        array[array.length - 1] = item;
    }

    void copyData(T[] src, Object[] dst) {
        for (int i = 0; i < src.length; i++)
            dst[i] = src[i];
    }

    public T get(int index) {
        return array[index];
    }
}
