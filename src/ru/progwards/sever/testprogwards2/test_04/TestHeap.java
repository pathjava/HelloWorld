package ru.progwards.sever.testprogwards2.test_04;

import java.util.Arrays;

public class TestHeap {
    public static void test1() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        for (int i = 0; i < 10; i++) {
            heap.add(i);
            System.out.println(heap);
        }
        while (heap.size() > 0) {
            System.out.println(heap.poll());
        }
    }

    public static void test2() {
        Integer[] array = new Integer[10];
        for (int i = 0; i < 10; i++) {
            array[i] = i;
        }
        BinaryHeap.sort(BinaryHeap.Type.MIN_HEAP, array);
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        test2();
    }
}
