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

    private int countAddBlocks = 1;

    public Heap(int maxHeapSize) {
        bytes = new byte[maxHeapSize];
        emptyBlockSet.add(new EmptyBlock(0, bytes.length - 1));
        emptyBlocksMap.put(bytes.length, (TreeSet<EmptyBlock>) emptyBlockSet);
    }

    public int malloc(int size) {
        int index = 0; //TODO проверить правильность постоянной инициализации нулем
        if (emptyBlocksMap.ceilingKey(size) >= size) {
            index = emptyBlocksMap.get(emptyBlocksMap.ceilingKey(size)).iterator().next().getStartIndexEmpty();
            addBlockToHeap(index, size);
            countAddBlocks++;
        }
        else
            compact();
        return index;
    }

    private void addBlockToHeap(int index, int size) {
        for (int i = 0; i < size; i++) {
            bytes[index + i] = (byte) countAddBlocks;
        }
        changeEmptyBlocksMap(index, size);
        changeFilledBlocksMap(index, size);
    }

    private void changeEmptyBlocksMap(int index, int size){

    }

    private void changeFilledBlocksMap(int index, int size){

    }

    public void free(int ptr) {

    }

    public void compact(){

    }


    public static void main(String[] args) {
        Heap test = new Heap(1000);
        test.malloc(100);
    }

}
