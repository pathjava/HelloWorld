// Oleg Kiselev
// 02.08.2020, 15:15

package ru.progwards.java2.lessons.gc;

public class Block {

    private final int startIndexBlock;
    private final int endIndexBlock;
    private final int sizeBlock;
    public enum Condition {EMPTY, FILLED, READY_TO_FREE}
    private Condition condition;

    public Block(int startIndexEmpty, int endIndexEmpty, int sizeEmptyBlock) {
        this.startIndexBlock = startIndexEmpty;
        this.endIndexBlock = endIndexEmpty;
        this.sizeBlock = sizeEmptyBlock;
        condition = Condition.EMPTY;
    }

    public int getStartIndexBlock() {
        return startIndexBlock;
    }

    public int getEndIndexBlock() {
        return endIndexBlock;
    }

    public int getSizeBlock() {
        return sizeBlock;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    /* переопределен для отладки */
    @Override
    public String toString() {
        return "EmptyBlock{" +
                "startIndexEmpty=" + startIndexBlock +
                ", endIndexEmpty=" + endIndexBlock +
                ", sizeEmptyBlock=" + sizeBlock +
                '}';
    }

}
