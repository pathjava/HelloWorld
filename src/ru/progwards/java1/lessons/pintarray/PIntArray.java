package ru.progwards.java1.lessons.pintarray;

class PIntArray {
    BArray<int[]> array;
    int blockSize;
    int size;

    PIntArray(int blockSize) {
        this.blockSize = blockSize;
        array = new BArray<>(10, 10);
        array.add(new int[blockSize]);
        size = 0;
    }

    public void add(int item) {
        if (blockSize == size) {
            array.add(new int[blockSize]);
            size = 0;
        }
        array.get(item)[size++] = item;
    }

    void copyData(BArray<int[]> src, Object[] dst) {
        for (int i = 0; i < src.blockSize; i++)
            dst[i] = src.get(i)[i];
    }

    int get(int index) {
        int index1 = index / blockSize;
        int index2 = index % blockSize;
        return array.get(index1)[index2];
    }

}
