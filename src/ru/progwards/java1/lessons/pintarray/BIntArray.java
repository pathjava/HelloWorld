package ru.progwards.java1.lessons.pintarray;

public class BIntArray {
    int[] array;
    int blockSize;
    int size;

    BIntArray(int blockSize) {
        this.blockSize = blockSize;
        array = new int[blockSize];
        size = 0;
    }

    public void add(int item) {
        if (size == array.length) {
            int[] newArray = new int[array.length + blockSize];
            copyData(array, newArray);
            array = newArray;
            size = 0;
        }
        array[size++] = item;
    }

    void copyData(int[] src, int[] dst) {
        for (int i = 0; i < src.length; i++)
            dst[i] = src[i];
    }

    public int get(int index) {
        return array[index];
    }
}