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
        sessionHandle = randomHandle();
        lastAccess = ZonedDateTime.now();
    }

    private int randomHandle() {
        List<Integer> randomList = new ArrayList<>();
        int randomInt = 0;
        for (int i = 1000; i <= 9999; i++) {
            randomList.add(i);
        }
        Collections.shuffle(randomList);
        for (Integer integer : randomList) {
            randomInt = integer;
        }
        return randomInt;
    }

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
}
