// Oleg Kiselev
// 25.07.2020, 20:22

package ru.progwards.java2.lessons.synchro;

import java.util.concurrent.Semaphore;

public class Fork {

    private final Semaphore semaphore = new Semaphore(1); /* семафор с одним разрешением к ресурсу (вилке) */

    public boolean takeFork() { /* проверяем, можно ли взять вилку и если да, возвращаем true */
        return semaphore.tryAcquire();
    }

    public void putFork() { /* кладем вилку на стол */
        semaphore.release();
    }
}
