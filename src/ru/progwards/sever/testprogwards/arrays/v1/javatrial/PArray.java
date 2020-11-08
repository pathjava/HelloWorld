package ru.progwards.sever.testprogwards.arrays.v1.javatrial;

public class PArray<T extends Object> {
    BArray<T[]> array;
    int size;
    int blockSize;
    int pages;

    public PArray(int blockSize) {
        this.blockSize = blockSize;
        array = new BArray<T[]>(blockSize, blockSize);
        addPage();
    }

    public void add(T item) {
        if (blockSize == size)
            addPage();
        T[] page = array.get(pages - 1);
        size++;
        page[size - 1] = item;
    }

    void addPage() {
        array.add((T[]) new Object[blockSize]);
        pages++;
        size = 0;
    }

    public T get(int index) {
        int index1 = index / blockSize;
        int index2 = index % blockSize;
        return array.get(index1)[index2];
    }
}
