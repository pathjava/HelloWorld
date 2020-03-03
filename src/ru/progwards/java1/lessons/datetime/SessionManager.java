package ru.progwards.java1.lessons.datetime;

import java.util.List;

public class SessionManager {
    private List<UserSession> sessions;
    private int sessionValid;

    public SessionManager(int sessionValid) {
        this.sessionValid = sessionValid;
    }

    public void add(UserSession userSession){

    }

    public UserSession find(String userName){

    }

    public UserSession get(int sessionHandle){

    }

    public void delete(int sessionHandle){

    }

    public void deleteExpired(){

    }
}
