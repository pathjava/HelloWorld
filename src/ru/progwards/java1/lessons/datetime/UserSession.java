package ru.progwards.java1.lessons.datetime;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserSession {
    private int sessionHandle;
    private String userName;
    private ZonedDateTime lastAccess;

    public UserSession(String userName) {
        this.userName = userName;
        /* присваиваем sessionHandle одно из случайных чисел, извлекаемых из ArrayList */
        sessionHandle = randomHandle();
        /* присваиваем времени прошлой сессии текущее время */
        lastAccess = ZonedDateTime.now();
    }

    /* генерируем хэндлы */
    private int randomHandle() {
        List<Integer> randomList = new ArrayList<>();
        int randomInt = 0;
        for (int i = 1000; i <= 9999; i++) {
            randomList.add(i);
        }
        /* сгенерированные циклом и добавленные в ArrayList числа перемешиваем в ArrayList в случайном порядке */
        Collections.shuffle(randomList);
        for (Integer integer : randomList) {
            randomInt = integer;
        }
        return randomInt;
    }

    /* присваиваем времени прошлой сессии текущее время - обновляем время */
    public void updateLastAccess() {
        lastAccess = ZonedDateTime.now();
    }

    public int getSessionHandle() {
        return sessionHandle;
    }

    public String getUserName() {
        return userName;
    }

    public ZonedDateTime getLastAccess() {
        return lastAccess;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "sessionHandle=" + sessionHandle +
                ", userName='" + userName + '\'' +
                ", lastAccess=" + lastAccess +
                '}';
    }
}
