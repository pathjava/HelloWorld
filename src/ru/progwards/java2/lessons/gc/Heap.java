// Oleg Kiselev
// 27.05.2020, 12:38

package ru.progwards.java2.lessons.gc;

import java.util.HashMap;
import java.util.Map;

public class Heap {

    private byte[] bytes;
    private Map<Integer, EmptyBlock> emptyBlocks;
    private Map<Integer, FilledBlock> filledBlocks;

    public Heap(int maxHeapSize) {
        bytes = new byte[maxHeapSize];
        filledBlocks = new HashMap<>(maxHeapSize);
        emptyBlocks = new HashMap<>(maxHeapSize);
    }

    public int malloc(int size){

        return 0;
    }

    public void free(int ptr){

    }

}
