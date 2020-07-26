// Oleg Kiselev
// 25.07.2020, 20:22

package ru.progwards.java2.lessons.synchro;

public class Fork {

    private boolean usedFork;
    private int numFork;

    public Fork() {
        this.usedFork = false;
    }

    public boolean getUsedFork() {
        return usedFork;
    }

    public void setUsedFork(boolean usedFork) {
        this.usedFork = usedFork;
    }

    public void setNumFork(int numFork) {
        this.numFork = numFork;
    }
}
