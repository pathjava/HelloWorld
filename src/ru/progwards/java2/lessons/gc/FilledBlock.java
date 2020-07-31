// Oleg Kiselev
// 27.05.2020, 20:25

package ru.progwards.java2.lessons.gc;


public class FilledBlock {

    private final int endIndexFilled;
    private final int sizeFilledBlock;
    private boolean readyToFree;

    public FilledBlock(int endIndexFilled, int sizeFilledBlock) {
        this.endIndexFilled = endIndexFilled;
        this.sizeFilledBlock = sizeFilledBlock;
        readyToFree = false;
    }

    public int getEndIndexFilled() {
        return endIndexFilled;
    }

    public int getSizeFilledBlock() {
        return sizeFilledBlock;
    }

    public void setReadyToFree(boolean readyToFree) {
        this.readyToFree = readyToFree;
    }

    /* переопределен для отладки */
    @Override
    public String toString() {
        return "FilledBlock{" +
                "endIndexFilled=" + endIndexFilled +
                ", sizeFilledBlock=" + sizeFilledBlock +
                '}';
    }
}
