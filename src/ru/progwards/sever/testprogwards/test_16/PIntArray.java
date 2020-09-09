package ru.progwards.sever.testprogwards.test_16;


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
    }

    int get(int index) {
        int index1 = index / blockSize;
        int index2 = index % blockSize;
        return array.get(index1)[index2];
    }

}
