// Oleg Kiselev
// 27.05.2020, 20:24

package ru.progwards.java2.lessons.gc;


public class EmptyBlock {

    private final int startIndexEmptyBlock;
    private final int endIndexEmptyBlock;
    private final int sizeEmptyBlock;

    public EmptyBlock(int startIndexEmpty, int endIndexEmpty, int sizeEmptyBlock) {
        this.startIndexEmptyBlock = startIndexEmpty;
        this.endIndexEmptyBlock = endIndexEmpty;
        this.sizeEmptyBlock = sizeEmptyBlock;
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
