// Oleg Kiselev
// 27.05.2020, 20:24

package ru.progwards.java2.lessons.gc;


public class EmptyBlock {

    private int startIndexEmpty;
    private int endIndexEmpty;
    private int sizeEmptyBlock;

    public EmptyBlock(int startIndexEmpty, int endIndexEmpty, int sizeEmptyBlock) {
        this.startIndexEmpty = startIndexEmpty;
        this.endIndexEmpty = endIndexEmpty;
        this.sizeEmptyBlock = sizeEmptyBlock;
    }

    public int getStartIndexEmpty() {
        return startIndexEmpty;
    }

    public void setStartIndexEmpty(int startIndexEmpty) {
        this.startIndexEmpty = startIndexEmpty;
    }

    public int getEndIndexEmpty() {
        return endIndexEmpty;
    }

    public void setEndIndexEmpty(int endIndexEmpty) {
        this.endIndexEmpty = endIndexEmpty;
    }

    public int getSizeEmptyBlock() {
        return sizeEmptyBlock;
    }

    public void setSizeEmptyBlock(int sizeEmptyBlock) {
        this.sizeEmptyBlock = sizeEmptyBlock;
    }

    @Override
    public String toString() {
        return "EmptyBlock{" +
                "startIndexEmpty=" + startIndexEmpty +
                ", endIndexEmpty=" + endIndexEmpty +
                ", sizeEmptyBlock=" + sizeEmptyBlock +
                '}';
    }
}
