// Oleg Kiselev
// 27.05.2020, 12:38

package ru.progwards.java2.lessons.gc;

import java.util.*;

public class Heap {

    private byte[] bytes;
    private TreeSet<EmptyBlock> emptyBlockSet;
    private final NavigableMap<Integer, TreeSet<EmptyBlock>> emptyBlocksMap = new TreeMap<>();
    private final Map<Integer, FilledBlock> filledBlocksMap = new HashMap<>();

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
        filledBlocksMap.put(index, new FilledBlock(index, endIndex, size));
    }

    public void free(int ptr) {
        int endIndex;
        int sizeRemoveBlock;
        if (filledBlocksMap.containsKey(ptr)) {
            endIndex = filledBlocksMap.get(ptr).getEndIndexFilled();
            sizeRemoveBlock = filledBlocksMap.get(ptr).getSizeFilledBlock();
            filledBlocksMap.remove(ptr);
            addEmptyBlockAfterRemove(ptr, endIndex, sizeRemoveBlock);

            for (int i = ptr; i <= endIndex; i++) {
                bytes[i] = 0;
            }
            //TODO сделать проверку, если ptr нет или указывает на середину блока
        }
    }

    private void addEmptyBlockAfterRemove(int startIndex, int endIndex, int sizeEmptyBlock){
        if (emptyBlocksMap.get(sizeEmptyBlock).size() > 1) {
            emptyBlockSet.add(new EmptyBlock(startIndex, endIndex));
            emptyBlocksMap.put(sizeEmptyBlock, emptyBlockSet);

        } else {
            emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
            emptyBlockSet.add(new EmptyBlock(startIndex, endIndex));
            emptyBlocksMap.put(sizeEmptyBlock, emptyBlockSet);
        }
    }

    public void compact() {

    }


    public static void main(String[] args) {
        Heap test = new Heap(100);
        test.malloc(5);
        test.malloc(6);
        test.malloc(6);
        test.free(11);
        test.malloc(7);
        test.malloc(7);
    }

}
