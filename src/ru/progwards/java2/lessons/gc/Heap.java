// Oleg Kiselev
// 27.05.2020, 12:38

package ru.progwards.java2.lessons.gc;

import java.util.*;

public class Heap {

    private byte[] bytes;
    private TreeSet<EmptyBlock> emptyBlockSet;
    private final NavigableMap<Integer, TreeSet<EmptyBlock>> emptyBlocksMap = new TreeMap<>();
    private final Map<Integer, FilledBlock> filledBlocksMap = new HashMap<>();

    private final int maxSizeHeap;
    private int currentFilledSizeHeap;
    private int countAddBlocks = 0;

    public Heap(int maxHeapSize) {
        bytes = new byte[maxHeapSize];
        emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
        emptyBlockSet.add(new EmptyBlock(0, bytes.length - 1, maxHeapSize));
        emptyBlocksMap.put(maxHeapSize, emptyBlockSet);
        maxSizeHeap = maxHeapSize;
    }

    public int malloc(int size) {
        int emptyBlockSuitableSize;
        if (!(emptyBlocksMap.ceilingKey(size) == null))
            emptyBlockSuitableSize = emptyBlocksMap.ceilingKey(size);
        else {
            compact();
            if (emptyBlocksMap.ceilingKey(size) == null)
                throw new OutOfMemoryError("Недостаточно места");
            else
                emptyBlockSuitableSize = emptyBlocksMap.ceilingKey(size);
        }

        if ((currentFilledSizeHeap + size) > maxSizeHeap) {
            compact();
            if ((currentFilledSizeHeap + size) > maxSizeHeap)
                throw new OutOfMemoryError("Недостаточно места");
        }

        //TODO проверить правильность постоянной инициализации нулем
        int index = emptyBlocksMap.get(emptyBlocksMap.ceilingKey(size)).iterator().next().getStartIndexEmpty();
        addBlockToHeap(index, size, emptyBlockSuitableSize);
        //TODO если и после уплотнения места под новый блок не будет, бросаем исключение

        return index;
    }

    private void addBlockToHeap(int index, int size, int emptyBlockSuitableSize) {
        countAddBlocks++;
        for (int i = 0; i < size; i++) {
            bytes[index + i] = (byte) countAddBlocks;
        }
        addEmptyBlockToMap(index, size, emptyBlockSuitableSize);
        addFilledBlockToMap(index, size);
    }

    private void addEmptyBlockToMap(int index, int size, int emptyBlockSuitableSize) {
        int newStartIndex = index + size;
        int oldEndIndex = emptyBlocksMap.get(emptyBlockSuitableSize).iterator().next().getEndIndexEmpty();
        int newKeyAndBlockSize = emptyBlockSuitableSize - size;

        if (emptyBlocksMap.get(emptyBlockSuitableSize).size() == 1) {
            emptyBlocksMap.remove(emptyBlockSuitableSize);
            emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
        } else
            emptyBlockSet.pollFirst();
        emptyBlockSet.add(new EmptyBlock(newStartIndex, oldEndIndex, newKeyAndBlockSize));
        emptyBlocksMap.put(newKeyAndBlockSize, emptyBlockSet);
        currentFilledSizeHeap += size;
    }

    private void addFilledBlockToMap(int index, int size) {
        int endIndex = index + (size - 1);
        filledBlocksMap.put(index, new FilledBlock(index, endIndex, size));
        //TODO проверить ситуацию уже существования в мапе индекса, хотя такого быть не должно
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
        } else
            throw new IndexOutOfBoundsException("Неверный указатель: " + ptr); //TODO ??? сделать проверку, если ptr нет или указывает на середину блока
    }

    private void addEmptyBlockAfterRemove(int startIndex, int endIndex, int sizeEmptyBlock) {
        if (!emptyBlocksMap.containsKey(sizeEmptyBlock)) {
            emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
        }
        emptyBlockSet.add(new EmptyBlock(startIndex, endIndex, sizeEmptyBlock));
        emptyBlocksMap.put(sizeEmptyBlock, emptyBlockSet);
        currentFilledSizeHeap -= sizeEmptyBlock;
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
        test.malloc(10);
        test.free(0);
        test.malloc(25);
        test.malloc(20);
        test.malloc(18);
        test.malloc(7);
    }

}
