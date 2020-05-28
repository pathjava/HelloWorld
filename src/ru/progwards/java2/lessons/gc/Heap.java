// Oleg Kiselev
// 27.05.2020, 12:38

package ru.progwards.java2.lessons.gc;

import java.util.*;

public class Heap {

    private byte[] bytes;
    private TreeSet<EmptyBlock> emptyBlockSet;
    private TreeSet<FilledBlock> filledBlockSet;
    private final NavigableMap<Integer, TreeSet<EmptyBlock>> emptyBlocksMap = new TreeMap<>();
    private final NavigableMap<Integer, TreeSet<FilledBlock>> filledBlocksMap = new TreeMap<>();

    private int countAddBlocks = 0;

    public Heap(int maxHeapSize) {
        bytes = new byte[maxHeapSize];
        emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
        emptyBlockSet.add(new EmptyBlock(0, bytes.length - 1));
        emptyBlocksMap.put(bytes.length, emptyBlockSet);
    }

    public int malloc(int size) {
        int index = 0; //TODO проверить правильность постоянной инициализации нулем
        int currentKeyAndSizeEmptyBlock = emptyBlocksMap.ceilingKey(size);
        if (currentKeyAndSizeEmptyBlock >= size) {
            index = emptyBlocksMap.get(emptyBlocksMap.ceilingKey(size)).iterator().next().getStartIndexEmpty();
            addBlockToHeap(index, size, currentKeyAndSizeEmptyBlock);
        } else
            compact(); //TODO если и после уплотнения места под новый блок не будет, бросаем исключение
        return index;
    }

    private void addBlockToHeap(int index, int size, int currentKeyAndSizeEmptyBlock) {
        countAddBlocks++;
        for (int i = 0; i < size; i++) {
            bytes[index + i] = (byte) countAddBlocks;
        }
        addEmptyBlockToMap(index, size, currentKeyAndSizeEmptyBlock);
        addFilledBlockToMap(index, size);
    }

    private void addEmptyBlockToMap(int index, int size, int currentKeyAndSizeEmptyBlock) {
        int newStartIndex = index + size;
        int oldEndIndex = emptyBlocksMap.get(emptyBlocksMap.ceilingKey(size)).iterator().next().getEndIndexEmpty();
        int newKey = currentKeyAndSizeEmptyBlock - size;

        if (emptyBlocksMap.get(currentKeyAndSizeEmptyBlock).size() == 1) {
            emptyBlocksMap.remove(currentKeyAndSizeEmptyBlock);
            emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
        } else
            emptyBlockSet.pollFirst();
        emptyBlockSet.add(new EmptyBlock(newStartIndex, oldEndIndex));
        emptyBlocksMap.put(newKey, emptyBlockSet);
    }

    private void addFilledBlockToMap(int index, int size) {
        int endIndex = index + (size - 1);
        if (!filledBlocksMap.containsKey(size))
            filledBlockSet = new TreeSet<>(Comparator.comparingInt(FilledBlock::getStartIndexFilled));
        filledBlockSet.add(new FilledBlock(index, endIndex));
        filledBlocksMap.put(size, filledBlockSet);
    }

    public void free(int ptr) {
        int endIndex = 0; //TODO проверить правильность постоянной инициализации нулем
        for (Map.Entry<Integer, TreeSet<FilledBlock>> entry : filledBlocksMap.entrySet()) {
            if (entry.getValue().iterator().next().getStartIndexFilled() == ptr)
                endIndex = entry.getValue().iterator().next().getEndIndexFilled();
            //TODO сделать проверку, если ptr нет или указывает на середину блока
        }
        int sizeRemoveBlock = endIndex - ptr;


    }

    private void removeFilledBlockFromMap(){

    }

    public void compact() {

    }


    public static void main(String[] args) {
        Heap test = new Heap(100);
        test.malloc(5);
        test.malloc(6);
        test.malloc(6);
        test.malloc(7);
        test.malloc(7);
    }

}
