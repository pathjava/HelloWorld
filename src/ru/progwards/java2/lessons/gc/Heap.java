// Oleg Kiselev
// 27.05.2020, 12:38

package ru.progwards.java2.lessons.gc;

import java.util.*;

public class Heap {

    private byte[] bytes;
    private TreeSet<EmptyBlock> emptyBlockSet;
    private final TreeSet<FilledBlock> filledBlockSet = new TreeSet<>(Comparator.comparingInt(FilledBlock::getStartIndexFilled));
    private final NavigableMap<Integer, TreeSet<EmptyBlock>> emptyBlocksMap = new TreeMap<>();
    private final NavigableMap<Integer, TreeSet<FilledBlock>> filledBlocksMap = new TreeMap<>();

    private int countAddBlocks = 1;

    public Heap(int maxHeapSize) {
        bytes = new byte[maxHeapSize];
        emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
        emptyBlockSet.add(new EmptyBlock(0, bytes.length - 1));
        emptyBlocksMap.put(bytes.length, emptyBlockSet);
    }

    public int malloc(int size) {
        int index = 0; //TODO проверить правильность постоянной инициализации нулем
        int currentKey = emptyBlocksMap.ceilingKey(size);
        if (currentKey >= size) {
            index = emptyBlocksMap.get(emptyBlocksMap.ceilingKey(size)).iterator().next().getStartIndexEmpty();
            addBlockToHeap(index, size, currentKey);
            countAddBlocks++;
        } else
            compact();
        return index;
    }

    private void addBlockToHeap(int index, int size, int currentKey) {
        for (int i = 0; i < size; i++) {
            bytes[index + i] = (byte) countAddBlocks;
        }
        changeEmptyBlocksMap(index, size, currentKey);
        changeFilledBlocksMap(index, size);
    }

    private void changeEmptyBlocksMap(int index, int size, int currentKey) {
        int newStartIndex = index + size;
        int oldEndIndex = emptyBlocksMap.get(emptyBlocksMap.ceilingKey(size)).iterator().next().getEndIndexEmpty();
        int newKey = currentKey - size;

        if (emptyBlocksMap.get(currentKey).size() == 1) {
            emptyBlocksMap.remove(currentKey);
            emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
        } else
            emptyBlockSet.pollFirst();
        emptyBlockSet.add(new EmptyBlock(newStartIndex, oldEndIndex));
        emptyBlocksMap.put(newKey, emptyBlockSet);
    }

    private void changeFilledBlocksMap(int index, int size) {

    }

    public void free(int ptr) {

    }

    public void compact() {

    }


    public static void main(String[] args) {
        Heap test = new Heap(100);
        test.malloc(5);
        test.malloc(15);
    }

}
