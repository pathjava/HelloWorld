// Oleg Kiselev
// 27.05.2020, 12:38

package ru.progwards.java2.lessons.gc;

import java.util.*;

public class Heap {

    private byte[] bytes;
    private Set<EmptyBlock> emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
    private Set<FilledBlock> filledBlockSet = new TreeSet<>(Comparator.comparingInt(FilledBlock::getStartIndexFilled));
    private NavigableMap<Integer, TreeSet<EmptyBlock>> emptyBlocksMap = new TreeMap<>();
    private NavigableMap<Integer, TreeSet<FilledBlock>> filledBlocksMap = new TreeMap<>();

    public Heap(int maxHeapSize) {
        bytes = new byte[maxHeapSize];
        emptyBlockSet.add(new EmptyBlock(0, bytes.length - 1));
        emptyBlocksMap.put(bytes.length - 1, (TreeSet<EmptyBlock>) emptyBlockSet);
    }

    public int malloc(int size) {
        if (emptyBlocksMap.ceilingKey(size) >= size) {
            for (int i = 0; i < size; i++) {
                bytes[emptyBlocksMap.get(emptyBlocksMap.ceilingKey(size)).iterator().next().getStartIndexEmpty()+i] = 1;
            }
        }
        return 0;
    }

    public void free(int ptr) {

    }


    public static void main(String[] args) {
        Heap test = new Heap(1000);
        test.malloc(100);
    }

}
