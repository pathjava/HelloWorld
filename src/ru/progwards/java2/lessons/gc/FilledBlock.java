// Oleg Kiselev
// 27.05.2020, 20:25

package ru.progwards.java2.lessons.gc;


public class FilledBlock {

    private int startIndexFilled;
    private int endIndexFilled;

    public FilledBlock(int startIndexFilled, int endIndexFilled) {
        this.startIndexFilled = startIndexFilled;
        this.endIndexFilled = endIndexFilled;
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
}
