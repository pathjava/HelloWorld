// Oleg Kiselev
// 27.05.2020, 20:24

package ru.progwards.java2.lessons.gc;


public class EmptyBlock {

    private final int startIndexEmpty;
    private final int endIndexEmpty;
    private final int sizeEmptyBlock;

    public EmptyBlock(int startIndexEmpty, int endIndexEmpty, int sizeEmptyBlock) {
        this.startIndexEmpty = startIndexEmpty;
        this.endIndexEmpty = endIndexEmpty;
        this.sizeEmptyBlock = sizeEmptyBlock;
    }

    public int getStartIndexEmpty() {
        return startIndexEmpty;
    }

    public int getEndIndexEmpty() {
        return endIndexEmpty;
    }

    public int getSizeEmptyBlock() {
        return sizeEmptyBlock;
    }

    /* переопределен для отладки */
    @Override
    public String toString() {
        return "EmptyBlock{" +
                "startIndexEmpty=" + startIndexEmpty +
                ", endIndexEmpty=" + endIndexEmpty +
                ", sizeEmptyBlock=" + sizeEmptyBlock +
                '}';
    }
}
