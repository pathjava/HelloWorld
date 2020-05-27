// Oleg Kiselev
// 27.05.2020, 20:24

package ru.progwards.java2.lessons.gc;

public class EmptyBlock {

    private int startIndexEmpty;
    private int endIndexEmpty;

    public EmptyBlock(int startIndexEmpty, int endIndexEmpty) {
        this.startIndexEmpty = startIndexEmpty;
        this.endIndexEmpty = endIndexEmpty;
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
}
