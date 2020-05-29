// Oleg Kiselev
// 27.05.2020, 12:38

package ru.progwards.java2.lessons.gc;

import java.util.*;

public class Heap {

    private byte[] bytes;
    private TreeSet<EmptyBlock> emptyBlockSet;
    private final NavigableMap<Integer, TreeSet<EmptyBlock>> emptyBlocksTM = new TreeMap<>();
    private final Map<Integer, FilledBlock> filledBlocksHM = new HashMap<>();
    private final NavigableMap<Integer, FilledBlock> filledBlocksTM = new TreeMap<>();

    private final int maxSizeHeap;
    private int currentFilledSizeHeap;
    private int countAddBlocks = 0;

    public Heap(int maxHeapSize) {
        bytes = new byte[maxHeapSize];
        emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
        emptyBlockSet.add(new EmptyBlock(0, bytes.length - 1, maxHeapSize));
        emptyBlocksTM.put(maxHeapSize, emptyBlockSet);
        maxSizeHeap = maxHeapSize;
    }

    public int malloc(int size) {
        int emptyBlockSuitableSize;
        if (!(emptyBlocksTM.ceilingKey(size) == null))
            emptyBlockSuitableSize = emptyBlocksTM.ceilingKey(size);
        else {
            compact();
            if (emptyBlocksTM.ceilingKey(size) == null)
                throw new OutOfMemoryError("Недостаточно места");
            else
                emptyBlockSuitableSize = emptyBlocksTM.ceilingKey(size);
        }

        if ((currentFilledSizeHeap + size) > maxSizeHeap) {
            compact();
            if ((currentFilledSizeHeap + size) > maxSizeHeap)
                throw new OutOfMemoryError("Недостаточно места");
        }

        //TODO проверить правильность постоянной инициализации нулем
        int index = emptyBlocksTM.get(emptyBlocksTM.ceilingKey(size)).iterator().next().getStartIndexEmpty();
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
        int oldEndIndex = emptyBlocksTM.get(emptyBlockSuitableSize).iterator().next().getEndIndexEmpty();
        int newKeyAndBlockSize = emptyBlockSuitableSize - size;

        if (emptyBlocksTM.get(emptyBlockSuitableSize).size() == 1) {
            emptyBlocksTM.remove(emptyBlockSuitableSize);
            emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
        } else
            emptyBlockSet.pollFirst();
        emptyBlockSet.add(new EmptyBlock(newStartIndex, oldEndIndex, newKeyAndBlockSize));
        emptyBlocksTM.put(newKeyAndBlockSize, emptyBlockSet);
        currentFilledSizeHeap += size;
    }

    private void addFilledBlockToMap(int index, int size) {
        int endIndex = index + (size - 1);
        filledBlocksHM.put(index, new FilledBlock(index, endIndex, size));
        filledBlocksTM.put(index, new FilledBlock(index, endIndex, size));
        //TODO проверить ситуацию уже существования в мапе индекса, хотя такого быть не должно
    }

    public void free(int ptr) {
        int endIndex;
        int sizeRemoveBlock;

        if (filledBlocksHM.containsKey(ptr)) {
            endIndex = filledBlocksHM.get(ptr).getEndIndexFilled();
            sizeRemoveBlock = filledBlocksHM.get(ptr).getSizeFilledBlock();
            filledBlocksHM.remove(ptr);
            filledBlocksTM.remove(ptr);
            addEmptyBlockAfterRemove(ptr, endIndex, sizeRemoveBlock);

            for (int i = ptr; i <= endIndex; i++) {
                bytes[i] = 0;
            }
        } else
            throw new IndexOutOfBoundsException("Неверный указатель: " + ptr); //TODO ??? сделать проверку, если ptr нет или указывает на середину блока
    }

    private void addEmptyBlockAfterRemove(int startIndex, int endIndex, int sizeEmptyBlock) {
        if (!emptyBlocksTM.containsKey(sizeEmptyBlock)) {
            emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
        }
        emptyBlockSet.add(new EmptyBlock(startIndex, endIndex, sizeEmptyBlock));
        emptyBlocksTM.put(sizeEmptyBlock, emptyBlockSet);
        currentFilledSizeHeap -= sizeEmptyBlock;
    }

    public void compact() {
        int emptyCellIndex = 0;
        int filledCellIndex;
        filledBlocksHM.clear();
        for (int i = 0; i < bytes.length; i++) {
            if (!(filledBlocksTM.size() == 0))
                filledCellIndex = filledBlocksTM.firstKey();
            else
                break;

            for (int k = emptyCellIndex; k < bytes.length; k++) {
                if (bytes[k] == 0) {
                    emptyCellIndex = k;
                    break;
                }
            }

            int movableBlockSize = filledBlocksTM.firstEntry().getValue().getSizeFilledBlock();
            int count = 0;
            for (int j = emptyCellIndex; j < bytes.length; j++) {
                bytes[j] = bytes[filledCellIndex];
                bytes[filledCellIndex] = 0;
                filledCellIndex++;
                count++;
                if (count == movableBlockSize)
                    break;
            }

            int endIndex = emptyCellIndex + (movableBlockSize - 1);
            filledBlocksHM.put(emptyCellIndex, new FilledBlock(emptyCellIndex, endIndex, movableBlockSize));

            emptyCellIndex += movableBlockSize;
            filledBlocksTM.remove(filledBlocksTM.firstKey());
        }
        filledBlocksTM.clear();
        filledBlocksTM.putAll(filledBlocksHM);
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
