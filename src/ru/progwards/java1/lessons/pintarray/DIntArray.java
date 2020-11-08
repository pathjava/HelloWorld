package ru.progwards.java1.lessons.pintarray;

public class DIntArray {
    int[] array;

    public void add(int item) {
        int newSize = array == null ? 1 : array.length + 1;
        int[] newArray = new int[newSize];
        if (array != null)
            copyData(array, newArray);
        array = newArray;
        array[array.length - 1] = item;
    }

    void copyData(int[] src, int[] dst) {
        for (int i = 0; i < src.length; i++)
            dst[i] = src[i];
    }

    public int get(int index) {
        return array[index];
    }
}
