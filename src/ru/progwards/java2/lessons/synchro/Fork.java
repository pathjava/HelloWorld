// Oleg Kiselev
// 25.07.2020, 20:22

package ru.progwards.java2.lessons.synchro;

import java.util.concurrent.Semaphore;

public class Fork {

    private boolean usedFork;
    private int numFork;
    private final Semaphore semaphore = new Semaphore(1);

    public Fork() {
        this.usedFork = false;
    }

    public boolean takeFork(){
        return semaphore.tryAcquire();
    }

    public void putFork(){
        semaphore.release();
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
