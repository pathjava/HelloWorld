// Oleg Kiselev
// 27.05.2020, 20:25

package ru.progwards.java2.lessons.gc;


public class FilledBlock {

    private final int endIndexFilled;
    private final int sizeFilledBlock;

    public FilledBlock(int endIndexFilled, int sizeFilledBlock) {
        this.endIndexFilled = endIndexFilled;
        this.sizeFilledBlock = sizeFilledBlock;
    }

    public int getEndIndexFilled() {
        return endIndexFilled;
    }

    public int getSizeFilledBlock() {
        return sizeFilledBlock;
    }
}
