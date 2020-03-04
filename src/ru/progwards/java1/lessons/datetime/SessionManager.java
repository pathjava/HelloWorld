package ru.progwards.java1.lessons.datetime;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private List<UserSession> sessions;
    private int sessionValid;

    public SessionManager(int sessionValid) {
        this.sessionValid = sessionValid;
    }

    List<UserSession> listSessions = new ArrayList<>();
    public void add(UserSession userSession){

    }

    public UserSession find(String userName){
        for (UserSession session : listSessions) {
            if (session.getUserName().equals(userName)){
                ZonedDateTime currentTime = ZonedDateTime.now();
                ZonedDateTime lastAccessTime = ZonedDateTime.from(session.getLastAccess().plusSeconds(sessionValid));
                if (currentTime.isAfter(lastAccessTime) || session.getUserName() == null){
                    return null;
                } else {
                    UserSession userSession = new UserSession(session.getUserName());
                    userSession.updateLastAccess();
                }
            }
        }
    }
//
//    public UserSession get(int sessionHandle){
//
//    }

    public void delete(int sessionHandle){

    }

    public void deleteExpired(){
        ZonedDateTime currentTime = ZonedDateTime.now();
//        ZonedDateTime lastAccessTime = ZonedDateTime.from()
    }


    public static void main(String[] args) {
        SessionManager test = new SessionManager(180);

    }
}
