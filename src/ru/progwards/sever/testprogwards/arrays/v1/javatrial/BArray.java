package ru.progwards.sever.testprogwards.arrays.v1.javatrial;

public class BArray<T extends Object> {
    T[] array;
    int size;
    int blockSize;

    public BArray(int inilialSize, int blockSize) {
        this.blockSize = blockSize;
        array = (T[]) new Object[inilialSize];
    }

    public void add(T item) {
        if (array.length == size) {
            int newSize = array == null ? 1 : array.length + blockSize;
            Object[] newArray = new Object[newSize];
            if (array != null)
                copyData(array, newArray);
            array = (T[]) newArray;
        }
        size++;
        array[size - 1] = item;
    }

    void copyData(T[] src, Object[] dst) {
        for (int i = 0; i < src.length; i++)
            dst[i] = src[i];
    }

    public T get(int index) {
        return array[index];
    }
}
