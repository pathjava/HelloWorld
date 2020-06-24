// Oleg Kiselev
// 27.05.2020, 12:38

package ru.progwards.java2.lessons.gc;

import java.util.*;

public class Heap {

    private byte[] bytes;
    private TreeSet<EmptyBlock> emptyBlockSet;
    private final NavigableMap<Integer, TreeSet<EmptyBlock>> emptyBlocksTM = new TreeMap<>();
    private final Map<Integer, FilledBlock> filledBlocksHM = new HashMap<>();

    public Heap(int maxHeapSize) {
        bytes = new byte[maxHeapSize];
        emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
        emptyBlockSet.add(new EmptyBlock(0, bytes.length - 1, maxHeapSize));
        emptyBlocksTM.put(maxHeapSize, emptyBlockSet);
    }

    public int malloc(int size) throws OutOfMemoryException {
        if (size < 1 || size > bytes.length) /* проверяем, чтобы значение соответствовало размерам кучи */
            throw new IllegalArgumentException();
        int emptyBlockSuitableSize;
        if (!(emptyBlocksTM.ceilingKey(size) == null)) /* если размер свободного блока не найден подходящего размера */
            emptyBlockSuitableSize = emptyBlocksTM.ceilingKey(size);
        else {
//            defrag();
            compact(); /* тогда запускаем компактизацию кучи */
            if (emptyBlocksTM.ceilingKey(size) == null) /* если и после этого нет места, бросаем исключение */
                throw new OutOfMemoryException("Недостаточно памяти!");
            else
                emptyBlockSuitableSize = emptyBlocksTM.ceilingKey(size);
        }
        /* определяем индекс добавляемого блока */
        int index = emptyBlocksTM.get(emptyBlocksTM.ceilingKey(size)).iterator().next().getStartIndexEmpty();
        addBlockToHeap(index, size, emptyBlockSuitableSize);
        return index;
    }

    private void addBlockToHeap(int index, int size, int emptyBlockSuitableSize) {
        for (int i = 0; i < size; i++) /* заполняем кучу согласно размера пришедшего блока */
            bytes[index + i] = 1;
        if (!(size == emptyBlockSuitableSize)) /* если размер добавляемого блока и найденное свободное место не равны */
            addEmptyBlockToMap(index, size, emptyBlockSuitableSize); /* делаем пометки о свободном месте в куче */
        else { /* иначе удаляем свободный блок */
            if (emptyBlocksTM.get(emptyBlockSuitableSize).size() == 1)
                emptyBlocksTM.remove(emptyBlockSuitableSize); /* если блок имеет размер 1, то удаляем его целиком */
            else
                emptyBlocksTM.get(emptyBlockSuitableSize).pollFirst(); /* иначе удаляем первый элемент из трисет */
        }
        addFilledBlockToMap(index, size);/* и о занятых блоках в куче */
    }

    private void addEmptyBlockToMap(int index, int size, int emptyBlockSuitableSize) {
        int newStartIndex = index + size; /* определяем стартовый индекс нового пустого блока */
        int oldEndIndex = emptyBlocksTM.get(emptyBlockSuitableSize).iterator().next().getEndIndexEmpty();
        int newKeyAndBlockSize = emptyBlockSuitableSize - size; /* размер нового пустого блока */
        if (newStartIndex > oldEndIndex)
            throw new IllegalArgumentException("Начальный индекс блока не может быть больше конечного индекса");

        if (!(newStartIndex > bytes.length - 1) && bytes[newStartIndex] == 0) { /* проверяем, чтобы индекс нового пустого блока не выходил за размер кучи */
            if (emptyBlocksTM.containsKey(newKeyAndBlockSize)) { /* если уже есть пустой блок такого размера */
                emptyBlockSet = emptyBlocksTM.get(newKeyAndBlockSize); /* получаем его */
                if (emptyBlocksTM.get(emptyBlockSuitableSize).size() == 1) /* если размер 1, удаляем полностью */
                    emptyBlocksTM.remove(emptyBlockSuitableSize);
                else
                    emptyBlocksTM.get(emptyBlockSuitableSize).pollFirst(); /* иначе только первый из трисет */
            } else {
                if (emptyBlocksTM.get(emptyBlockSuitableSize).size() == 1)
                    emptyBlocksTM.remove(emptyBlockSuitableSize);
                else
                    emptyBlocksTM.get(emptyBlockSuitableSize).pollFirst();
                emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
            }
            emptyBlockSet.add(new EmptyBlock(newStartIndex, oldEndIndex, newKeyAndBlockSize));
            emptyBlocksTM.put(newKeyAndBlockSize, emptyBlockSet);
        } else {
            emptyBlocksTM.remove(emptyBlockSuitableSize); /* в противном случае удаляем данные */
            emptyBlockSet.clear();
        }
    }

    private void addFilledBlockToMap(int index, int size) {
        int endIndex = index + (size - 1);
        if (filledBlocksHM.containsKey(index))
            throw new IllegalArgumentException("Значение с таким индексом уже присутствует в filledBlocksHM");
        filledBlocksHM.put(index, new FilledBlock(endIndex, size)); /* заполняем hashmap данными о заполненных блоках - ключ-индекс */
    }

    public void free(int ptr) throws InvalidPointerException {
        if (ptr < 0 || ptr > bytes.length - 1) /* проверяем корректность указателя ptr */
            throw new IllegalArgumentException();
        int endIndex;
        int sizeRemoveBlock;

        if (filledBlocksHM.containsKey(ptr)) {
            endIndex = filledBlocksHM.get(ptr).getEndIndexFilled(); /* по указателю получаем конечный индекс удаляемого блока */
            sizeRemoveBlock = filledBlocksHM.get(ptr).getSizeFilledBlock(); /* получаем размер удаляемого блока */
            filledBlocksHM.remove(ptr); /* удаляем блок из мапы, хранящей данные о заполненных блоках в куче  */
            addEmptyBlockAfterRemove(ptr, endIndex, sizeRemoveBlock); /* добавляем данные о новом пустом блоке */
            for (int i = ptr; i <= endIndex; i++)
                bytes[i] = 0; /* заменяем значения на ноли */
        } else
            throw new InvalidPointerException("Неверный указатель: " + ptr);
    }

    private void addEmptyBlockAfterRemove(int startIndex, int endIndex, int sizeEmptyBlock) {
        if (emptyBlocksTM.containsKey(sizeEmptyBlock)) /* если уже есть блока такого размера */
            emptyBlockSet = emptyBlocksTM.get(sizeEmptyBlock); /* получаем его */
        else
            emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty)); /* иначе создаем новый */
        emptyBlockSet.add(new EmptyBlock(startIndex, endIndex, sizeEmptyBlock)); /* добавляем в трисет данные о блоке */
        emptyBlocksTM.put(sizeEmptyBlock, emptyBlockSet);
    }

    public void compact() { /* компактизация кучи */
//        System.out.println("start"); /* для отладки */
        int emptyCellIndex = 0;
        int countIteration = 0;
        for (int i = emptyCellIndex; i < bytes.length; i++) { /* ищем первую свободную ячейку в куче */
            if (bytes[i] == 0) {
                emptyCellIndex = i;
                break;
            }
            countIteration++;
        }
        if (countIteration == bytes.length) /* если каунт равен размеру кучи, значи свободных ячеек нет - выходим из метода */
            return;
        /* для поиска заполненных блоков отсортированных по индексу, перегоняем из hashmap в treemap */
        NavigableMap<Integer, FilledBlock> tempFilledBlocksTM = new TreeMap<>(filledBlocksHM);
        int filledCellIndex = tempFilledBlocksTM.firstKey();
        boolean checkFilledIndex = false;
        while (!checkFilledIndex) { /* ищем индекс первого заполненного блока, который > индекса свободной ячейки в куче */
            if (filledCellIndex > emptyCellIndex) /* если индекс блока больше индекса ячейки, выходим */
                checkFilledIndex = true;
            else {
                tempFilledBlocksTM.remove(tempFilledBlocksTM.firstKey()); /* иначе удаляем первый элемент тримэп и проверяем снова */
                filledCellIndex = tempFilledBlocksTM.firstKey();
            }
        }
        blockMovingFromOldToNewPlace(tempFilledBlocksTM, emptyCellIndex); /* вызываем методо перемещения блока в куче */
    }

    private void blockMovingFromOldToNewPlace(NavigableMap<Integer, FilledBlock> tempFilledBlocksTM, int emptyCellIndex) {
        while (!(tempFilledBlocksTM.size() == 0)) {
            int filledCellIndex = tempFilledBlocksTM.firstKey();
            int tempIndex = filledCellIndex;
            int movableBlockSize = tempFilledBlocksTM.firstEntry().getValue().getSizeFilledBlock();
            int count = 0;
            for (int j = emptyCellIndex; j < bytes.length; j++) { /* перемещаем значения из старого индекса в новый */
                bytes[j] = bytes[filledCellIndex];
                bytes[filledCellIndex] = 0; /* старые затираем в ноль */
                filledCellIndex++;
                count++;
                if (count == movableBlockSize)
                    break;
            }
            int endIndex = emptyCellIndex + (movableBlockSize - 1); /* конечный индекс перенесенного блока */

            filledBlocksHM.remove(tempIndex); /* удаляем старые данные о перенесенном блоке */
            /* добавляем новую информацию о перенесенном блоке */
            filledBlocksHM.put(emptyCellIndex, new FilledBlock(endIndex, movableBlockSize));

            emptyCellIndex += movableBlockSize; /* новый индекс первой пустой ячейки в куче */
            tempFilledBlocksTM.remove(tempFilledBlocksTM.firstKey()); /* удаляем перенесенный блок из временной тримап */
        }
        rebuildEmptyBlocksTM(emptyCellIndex); /* определяем новый пустой блок вконце кучи */
    }

    private void rebuildEmptyBlocksTM(int emptyCellIndex) {
        emptyBlocksTM.clear(); /* после компактизации создаем единый пустой блок в конце кучи */
        emptyBlockSet = new TreeSet<>(Comparator.comparingInt(EmptyBlock::getStartIndexEmpty));
        int newKeyAndBlockSize = bytes.length - emptyCellIndex;
        emptyBlockSet.add(new EmptyBlock(emptyCellIndex, bytes.length - 1, newKeyAndBlockSize));
        emptyBlocksTM.put(newKeyAndBlockSize, emptyBlockSet);
    }

    public void defrag() {
//        System.out.println("start"); /* для отладки */
        List<EmptyBlock> tempEmptyBlocks = new ArrayList<>();
        /* перегоняем все пустые блоки в ArrayList */
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
        tempEmptyBlocks.sort(Comparator.comparing(EmptyBlock::getStartIndexEmpty)); /* сортируем блоки по индексу */

        blockMerging(tempEmptyBlocks); /* метод слияния блоков */
        rebuildEmptyBlocksTM(tempEmptyBlocks); /* перестроение пустых объединенных блоков */
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
                if (endSearch + 1 == currentStart) { /* если произошло первое совпадение индексов */
                    if (!lock)
                        lock = true;
                    endSearch = tempEmptyBlocks.listIterator(j).next().getEndIndexEmpty();
                    endIndex = j;
                } else if (lock) { /* проверяем дальше совпадение последних и начальных индексов */
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
                    startIndex = i; /* если блоки не соседние, сдвигаемся на один вперед */
                    startSearch = tempEmptyBlocks.listIterator(i).next().getStartIndexEmpty();
                    endSearch = tempEmptyBlocks.listIterator(i).next().getEndIndexEmpty();
                }
            }
        }
    }

    private void rebuildEmptyBlocksTM(List<EmptyBlock> tempEmptyBlocks) {
        emptyBlocksTM.clear(); /* очищаем старую мапу */
        for (EmptyBlock block : tempEmptyBlocks) { /* перегоняем слитые блоки в новую мапу */
            addEmptyBlockAfterRemove(block.getStartIndexEmpty(),
                    block.getEndIndexEmpty(), block.getSizeEmptyBlock());
        }
    }


    public static void main(String[] args) {
        Heap test = new Heap(100);
        try {
//            test.malloc(3);
//            test.malloc(5);
//            test.malloc(2);
//            test.malloc(2);
//            test.malloc(5);
//            test.malloc(2);
//            test.malloc(2);
//            test.malloc(2);
//            test.malloc(5);
//            test.malloc(7);
//            test.malloc(1);
//            test.malloc(8);
//            test.malloc(10);
//            test.free(0);
//            test.free(8);
//            test.free(10);
//            test.free(12);
//            test.free(28);
//            test.free(35);
//            test.malloc(10);

//            test.malloc(5);
//            test.malloc(3);
//            test.malloc(5);
//            test.malloc(3);
//            test.malloc(1);
//            test.malloc(5);
//            test.free(5);
//            test.free(13);
//            test.free(16);
//            test.malloc(2);
//            test.malloc(5);
//            test.malloc(5);


//            test.malloc(4);
//            test.malloc(5);
//            test.malloc(4);
//            test.malloc(4);
//            test.malloc(5);
//            test.free(5);
//            test.free(14);
//            test.free(18);
//            test.malloc(6);
//            test.malloc(7);
//            test.malloc(2);
//            test.free(0);
//            test.malloc(20);

            test.malloc(5);
            test.malloc(6);
            test.malloc(6);
            test.free(11);
            test.malloc(7);
            test.malloc(10);
            test.free(17);
            test.malloc(25);
            test.malloc(20);
            test.malloc(18);
            test.malloc(14);
            test.malloc(2);

        } catch (OutOfMemoryException | InvalidPointerException e) {
            e.printStackTrace();
        }

        test.defrag();
    }

}
