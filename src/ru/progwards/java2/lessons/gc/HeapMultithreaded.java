// Oleg Kiselev
// 02.08.2020, 15:08

//package ru.progwards.java2.lessons.gc;
//
//import java.util.Comparator;
//import java.util.Map;
//import java.util.concurrent.*;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class HeapMultithreaded {
//
//    private final byte[] bytes;
//    private ConcurrentSkipListSet<Block> blocksSet;
//    private final ConcurrentSkipListMap<Integer, ConcurrentSkipListSet<Block>> blocksTM = new ConcurrentSkipListMap<>();
//    public final AtomicInteger currentSizeHeap = new AtomicInteger();
//    private final int percentageOfOccupancy;
//    private ExecutorService executor;
//
//    public HeapMultithreaded(int maxHeapSize) {
//        bytes = new byte[maxHeapSize];
//        blocksSet = new ConcurrentSkipListSet<>(Comparator.comparingInt(Block::getStartIndexBlock));
//        blocksSet.add(new Block(0, bytes.length - 1, maxHeapSize));
//        blocksTM.put(maxHeapSize, blocksSet);
//        percentageOfOccupancy = (int) (maxHeapSize * (15.0f / 100.0f));
//    }
//
//    public int malloc(int size) throws OutOfMemoryException {
//        if (size < 1 || size > bytes.length) /* проверяем, чтобы значение соответствовало размерам кучи */
//            throw new IllegalArgumentException();
//        /* получаем ключ/значение равное или большее значения ключа */
//        Map.Entry<Integer, ConcurrentSkipListSet<Block>> tempBlock = blocksTM.ceilingEntry(size);
//        int index;
//        if (tempBlock != null) { /* если размер свободного блока подходящего размера найден */
//            index = getIndex(tempBlock); /* определяем индекс добавляемого блока */
//            addBlockToTree(index, size, tempBlock.getKey()); /* добавляем блок в кучу */
//        } else {
//            tempBlock = blocksTM.ceilingEntry(size);
//            if (tempBlock == null) /* если и после этого нет места, бросаем исключение */
//                throw new OutOfMemoryException("Недостаточно памяти!");
//            else {
//                index = getIndex(tempBlock);
//                addBlockToHeap(index, size, tempBlock.getKey());
//            }
//        }
//        currentSizeHeap.addAndGet(size); /* прибавляем размер добавляемого блока в счетчик размера кучи */
//        if (checkingHeapFullness()) {//TODO description
//            System.out.println("cleanerFilledBlock() - start");
//            executor = Executors.newSingleThreadExecutor();
//            cleanerFilledBlock();
//        }
//        return index;
//    }
//
//    private void addBlockToTree(int index, int size, int key) {
//        if (size == key)
//            blocksTM.get(key).iterator().next().setCondition(Block.Condition.FILLED);
//    }
//
//    private int getIndex(Map.Entry<Integer, ConcurrentSkipListSet<Block>> tempEmptyBlock) {
//        return tempEmptyBlock.getValue().iterator().next().getStartIndexBlock();
//    }
//
//    private boolean checkingHeapFullness() { //TODO description
//        return currentSizeHeap.get() > percentageOfOccupancy;
//    }
//
//}
