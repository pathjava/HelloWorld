// Oleg Kiselev
// 27.05.2020, 12:38

package ru.progwards.java2.lessons.gc;

import java.util.*;

public class Heap {

    private byte[] bytes;
    private TreeSet<EmptyBlock> emptyBlockSet;
    private final NavigableMap<Integer, TreeSet<EmptyBlock>> emptyBlocksTM = new TreeMap<>();
    private final Map<Integer, FilledBlock> filledBlocksHM = new HashMap<>();

    private int countAddBlocks = 0;

    public Heap(int maxHeapSize) {
        bytes = new byte[maxHeapSize];
        emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
        emptyBlockSet.add(new EmptyBlock(0, bytes.length - 1, maxHeapSize));
        emptyBlocksTM.put(maxHeapSize, emptyBlockSet);
    }

    public int malloc(int size) throws OutOfMemoryException {
        if (size < 1 || size > bytes.length)
            throw new IllegalArgumentException();
        int emptyBlockSuitableSize;
        if (!(emptyBlocksTM.ceilingKey(size) == null))
            emptyBlockSuitableSize = emptyBlocksTM.ceilingKey(size);
        else {
            compact();
            if (emptyBlocksTM.ceilingKey(size) == null)
                throw new OutOfMemoryException("Недостаточно памяти!");
            else
                emptyBlockSuitableSize = emptyBlocksTM.ceilingKey(size);
        }
        int index = emptyBlocksTM.get(emptyBlocksTM.ceilingKey(size)).iterator().next().getStartIndexEmpty();
        addBlockToHeap(index, size, emptyBlockSuitableSize);

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

        if (!(newStartIndex > bytes.length - 1)) {
            if (emptyBlocksTM.get(emptyBlockSuitableSize).size() == 1) {
                emptyBlocksTM.remove(emptyBlockSuitableSize);
                emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
            } else
                emptyBlockSet.pollFirst();
            emptyBlockSet.add(new EmptyBlock(newStartIndex, oldEndIndex, newKeyAndBlockSize));
            emptyBlocksTM.put(newKeyAndBlockSize, emptyBlockSet);
        } else {
            emptyBlocksTM.remove(emptyBlockSuitableSize);
            emptyBlockSet.clear();
        }
    }

    private void addFilledBlockToMap(int index, int size) {
        int endIndex = index + (size - 1);
        if (filledBlocksHM.containsKey(index))
            throw new IllegalArgumentException("Значение с таким индексом уже присутствует в filledBlocksHM");
        filledBlocksHM.put(index, new FilledBlock(index, endIndex, size));
    }

    public void free(int ptr) throws InvalidPointerException {
        if (ptr < 0 || ptr > bytes.length - 1)
            throw new IllegalArgumentException();
        int endIndex;
        int sizeRemoveBlock;

        if (filledBlocksHM.containsKey(ptr)) {
            endIndex = filledBlocksHM.get(ptr).getEndIndexFilled();
            sizeRemoveBlock = filledBlocksHM.get(ptr).getSizeFilledBlock();
            filledBlocksHM.remove(ptr);
            addEmptyBlockAfterRemove(ptr, endIndex, sizeRemoveBlock);

            for (int i = ptr; i <= endIndex; i++) {
                bytes[i] = 0;
            }
        } else
            throw new InvalidPointerException("Неверный указатель: " + ptr);
    }

    private void addEmptyBlockAfterRemove(int startIndex, int endIndex, int sizeEmptyBlock) {
        if (!emptyBlocksTM.containsKey(sizeEmptyBlock)) {
            emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
        }
        emptyBlockSet.add(new EmptyBlock(startIndex, endIndex, sizeEmptyBlock));
        emptyBlocksTM.put(sizeEmptyBlock, emptyBlockSet);
    }

    public void compact() {
        int emptyCellIndex = 0;
        int countIteration = 0;
        for (int i = emptyCellIndex; i < bytes.length; i++) {
            if (bytes[i] == 0) {
                emptyCellIndex = i;
                break;
            }
            countIteration++;
        }
        if (countIteration == bytes.length)
            return;

        NavigableMap<Integer, FilledBlock> filledBlocksTM = new TreeMap<>(filledBlocksHM);
        filledBlocksHM.clear();
        int filledCellIndex = filledBlocksTM.firstKey();
        boolean checkFilledIndex = false;
        while (!checkFilledIndex) {
            if (filledCellIndex > emptyCellIndex) {
                checkFilledIndex = true;
            } else {
                filledBlocksHM.put(filledBlocksTM.firstKey(),
                        new FilledBlock(filledBlocksTM.firstKey(),
                                filledBlocksTM.firstEntry().getValue().getEndIndexFilled(),
                                filledBlocksTM.firstEntry().getValue().getSizeFilledBlock()));
                filledBlocksTM.remove(filledBlocksTM.firstKey());
                filledCellIndex = filledBlocksTM.firstKey();
            }
        }

        while (!(filledBlocksTM.size() == 0)) {
            filledCellIndex = filledBlocksTM.firstKey();
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
        filledBlocksTM.putAll(filledBlocksHM);

        emptyBlocksTM.clear();
        emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
        int newKeyAndBlockSize = bytes.length - emptyCellIndex;
        emptyBlockSet.add(new EmptyBlock(emptyCellIndex, bytes.length - 1, newKeyAndBlockSize));
        emptyBlocksTM.put(newKeyAndBlockSize, emptyBlockSet);
    }

    public void defrag() {
        List<EmptyBlock> tempEmptyBlocks = new ArrayList<>();
        for (Map.Entry<Integer, TreeSet<EmptyBlock>> entry : emptyBlocksTM.entrySet()) {
            if (entry.getValue().size() == 1)
                tempEmptyBlocks.add(new EmptyBlock(entry.getValue().iterator().next().getStartIndexEmpty(),
                        entry.getValue().iterator().next().getEndIndexEmpty(),
                        entry.getValue().iterator().next().getSizeEmptyBlock()));
            else
                for (EmptyBlock block : entry.getValue()) {
                    tempEmptyBlocks.add(new EmptyBlock(block.getStartIndexEmpty(),
                            block.getEndIndexEmpty(), block.getSizeEmptyBlock()));
                }
        }
        tempEmptyBlocks.sort(Comparator.comparing(EmptyBlock::getStartIndexEmpty));

        blockMerging(tempEmptyBlocks);
        rebuildEmptyBlocksTM(tempEmptyBlocks);
    }

    private void blockMerging(List<EmptyBlock> tempEmptyBlocks) {
        for (int i = 0; i < tempEmptyBlocks.size(); i++) {
            boolean lock = false;
            int endIndex = 0;
            int startIndex = i;
            int startSearch = tempEmptyBlocks.listIterator(i).next().getStartIndexEmpty();
            int endSearch = tempEmptyBlocks.listIterator(i).next().getEndIndexEmpty();
            for (int j = i + 1; j < tempEmptyBlocks.size(); j++) {
                int currentStart = tempEmptyBlocks.listIterator(j).next().getStartIndexEmpty();
                if (endSearch + 1 == currentStart) {
                    if (!lock)
                        lock = true;
                    endSearch = tempEmptyBlocks.listIterator(j).next().getEndIndexEmpty();
                    endIndex = j;
                } else if (lock) {
                    tempEmptyBlocks.add(new EmptyBlock(startSearch,
                            endSearch, (endSearch - startSearch) + 1));
                    int countRemove = startIndex;
                    while (countRemove <= endIndex) {
                        tempEmptyBlocks.remove(startIndex);
                        countRemove++;
                    }
                    i = startIndex - 1;
                    break;
                } else {
                    i++;
                    startIndex = i;
                    startSearch = tempEmptyBlocks.listIterator(i).next().getStartIndexEmpty();
                    endSearch = tempEmptyBlocks.listIterator(i).next().getEndIndexEmpty();
                }
            }
        }
    }

    private void rebuildEmptyBlocksTM(List<EmptyBlock> tempEmptyBlocks){
        emptyBlocksTM.clear();
        for (EmptyBlock block : tempEmptyBlocks) {
            addEmptyBlockAfterRemove(block.getStartIndexEmpty(),
                    block.getEndIndexEmpty(), block.getSizeEmptyBlock());
        }
    }


    public static void main(String[] args) {
        Heap test = new Heap(100);
        try {
            test.malloc(3);
            test.malloc(5);
            test.malloc(2);
            test.malloc(2);
            test.malloc(5);
            test.malloc(2);
            test.malloc(2);
            test.malloc(2);
            test.malloc(5);
            test.malloc(7);
            test.malloc(1);
            test.malloc(8);
            test.malloc(10);
        } catch (OutOfMemoryException e) {
            e.printStackTrace();
        }
        try {
            test.free(0);
            test.free(8);
            test.free(10);
            test.free(12);
            test.free(28);
            test.free(35);
        } catch (InvalidPointerException e) {
            e.printStackTrace();
        }
        try {
            test.malloc(10);
        } catch (OutOfMemoryException e) {
            e.printStackTrace();
        }

        test.defrag();
    }

}
