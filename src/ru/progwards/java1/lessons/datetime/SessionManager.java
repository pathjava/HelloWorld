package ru.progwards.java1.lessons.datetime;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SessionManager {
    private List<UserSession> sessions;
    private int sessionValid;

    public SessionManager(int sessionValid) {
        this.sessionValid = sessionValid;
        sessions = new ArrayList<>();
    }

    public void add(UserSession userSession) {
        sessions.add(userSession);
    }

    public UserSession find(String userName) {
        for (UserSession session : sessions) {
            ZonedDateTime currentTime = ZonedDateTime.now();
            ZonedDateTime lastAccessTime = ZonedDateTime.from(session.getLastAccess().plusSeconds(sessionValid));
            if ((session.getUserName().contains(userName) && currentTime.isAfter(lastAccessTime)) || session.getUserName() == null) {
                return null;
            } else if (session.getUserName().contains(userName) && currentTime.isBefore(lastAccessTime)){
//                    UserSession userSession = new UserSession(session.getUserName());
//                    userSession.updateLastAccess();
                session.updateLastAccess();
            }
        }
    }

    public UserSession get(int sessionHandle){
        for (UserSession session : sessions) {
            ZonedDateTime currentTime = ZonedDateTime.now();
            ZonedDateTime lastAccessTime = ZonedDateTime.from(session.getLastAccess().plusSeconds(sessionValid));
            if (((session.getSessionHandle() == sessionHandle) && currentTime.isAfter(lastAccessTime)) || session.getSessionHandle() == 0){
                return null;
            } else if (session.getSessionHandle() == sessionHandle && currentTime.isBefore(lastAccessTime)){
                session.updateLastAccess();
            }
        }
    }

    public void delete(int sessionHandle) {
        for (int i = 0; i < sessions.size(); i++) {
            UserSession session = sessions.get(i);
            if (session.getSessionHandle() == sessionHandle) {
                sessions.remove(session);
            }
        }
    }

    public void deleteExpired() {
        for (UserSession session : sessions) {
            ZonedDateTime currentTime = ZonedDateTime.now();
            ZonedDateTime lastAccessTime = ZonedDateTime.from(session.getLastAccess().plusSeconds(sessionValid));
            if (currentTime.isAfter(lastAccessTime)){
                sessions.remove(session);
            }
        }
    }


    public static void main(String[] args) {
        SessionManager test = new SessionManager(180);

    }
}
