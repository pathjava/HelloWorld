// Oleg Kiselev
// 27.05.2020, 20:25

package ru.progwards.java2.lessons.gc;


public class FilledBlock {

    private int startIndexFilled;
    private int endIndexFilled;
    private int sizeFilledBlock;

    public FilledBlock(int startIndexFilled, int endIndexFilled, int sizeFilledBlock) {
        this.startIndexFilled = startIndexFilled;
        this.endIndexFilled = endIndexFilled;
        this.sizeFilledBlock = sizeFilledBlock;
    }

    public int getStartIndexFilled() {
        return startIndexFilled;
    }

    public void setStartIndexFilled(int startIndexFilled) {
        this.startIndexFilled = startIndexFilled;
    }

    public int getEndIndexFilled() {
        return endIndexFilled;
    }

    public void setEndIndexFilled(int endIndexFilled) {
        this.endIndexFilled = endIndexFilled;
    }

    public int getSizeFilledBlock() {
        return sizeFilledBlock;
    }

    public void setSizeFilledBlock(int sizeFilledBlock) {
        this.sizeFilledBlock = sizeFilledBlock;
    }
}
