// Oleg Kiselev
// 27.05.2020, 20:24

package ru.progwards.java2.lessons.gc;


public class EmptyBlock {

    private final int startIndexEmptyBlock;
    private final int endIndexEmptyBlock;
    private final int sizeEmptyBlock;
    private boolean readyToFree;

    public EmptyBlock(int startIndexEmpty, int endIndexEmpty, int sizeEmptyBlock) {
        this.startIndexEmptyBlock = startIndexEmpty;
        this.endIndexEmptyBlock = endIndexEmpty;
        this.sizeEmptyBlock = sizeEmptyBlock;
        readyToFree = false;
    }

    public int getStartIndexEmptyBlock() {
        return startIndexEmptyBlock;
    }

    public int getEndIndexEmptyBlock() {
        return endIndexEmptyBlock;
    }

    public int getSizeEmptyBlock() {
        return sizeEmptyBlock;
    }

    public void setReadyToFree(boolean readyToFree) {
        this.readyToFree = readyToFree;
    }

    /* переопределен для отладки */
    @Override
    public String toString() {
        return "EmptyBlock{" +
                "startIndexEmpty=" + startIndexEmptyBlock +
                ", endIndexEmpty=" + endIndexEmptyBlock +
                ", sizeEmptyBlock=" + sizeEmptyBlock +
                '}';
    }
}
