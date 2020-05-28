// Oleg Kiselev
// 27.05.2020, 12:38

package ru.progwards.java2.lessons.gc;

import java.util.*;

public class Heap {

    private byte[] bytes;
    private Set<EmptyBlock> emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
    private Set<FilledBlock> filledBlockSet = new TreeSet<>(Comparator.comparingInt(FilledBlock::getStartIndexFilled));
    private Map<Integer, TreeSet<EmptyBlock>> emptyBlocksMap = new TreeMap<>();
    private Map<Integer, TreeSet<FilledBlock>> filledBlocksMap = new TreeMap<>();

    public Heap(int maxHeapSize) {
        bytes = new byte[maxHeapSize];
        emptyBlockSet.add(new EmptyBlock(0, bytes.length - 1));
        emptyBlocksMap.put(0, (TreeSet<EmptyBlock>) emptyBlockSet);
    }

    public int malloc(int size) {

        return 0;
    }

    public void free(int ptr) {

    }

}
