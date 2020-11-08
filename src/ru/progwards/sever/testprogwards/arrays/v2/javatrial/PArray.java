package ru.progwards.sever.testprogwards.arrays.v2.javatrial;

public class PArray<T extends Object> extends AArray {
    BArray<T[]> array;
    int size;
    int blockSize;

    public PArray(CopyType copy, int blockSize) {
        super(copy);
        this.blockSize = blockSize;
        array = new BArray<T[]>(copy, blockSize, blockSize);
        addPage();
    }

    @Override
    public void add(Object item) {
        if (blockSize == size)
            addPage();
        T[] page = (T[]) array.get(array.size() - 1);
        size++;
        page[size - 1] = (T) item;
    }

    void addPage() {
        array.add((T[]) new Object[blockSize]);
        size = 0;
    }

    public T get(int index) {
        int index1 = index / blockSize;
        int index2 = index % blockSize;
        return ((T[]) array.get(index1))[index2];
    }

    @Override
    public int size() {
        return (array.size() - 1) * blockSize + size;
    }
}
