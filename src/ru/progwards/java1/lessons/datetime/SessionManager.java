package ru.progwards.java1.lessons.datetime;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
        UserSession result = null;
        for (UserSession session : sessions) {
            if (session.getUserName().equals(userName)) {
                result = session;
                break;
            }
        }
        return validSession(result);
    }

    public UserSession get(int sessionHandle) {
        UserSession result = null;
        for (UserSession session : sessions) {
            if (session.getSessionHandle() == sessionHandle) {
                result = session;
                break;
            }
        }
        return validSession(result);
    }

    public UserSession validSession(UserSession session){
        /* проверка на существование сессии с именем userName */
        if (session == null) {
            return null;
        }
        /* проверка на валидность сессии */
        ZonedDateTime currentTime = ZonedDateTime.now();
        ZonedDateTime validTime = ZonedDateTime.from(session.getLastAccess().plusSeconds(sessionValid));
        if (currentTime.isAfter(validTime)){
            return null;
        }
        /* обновление даты доступа */
        session.updateLastAccess();
        return session;
    }

    public void delete(int sessionHandle) {
//        for (int i = 0; i < sessions.size(); i++) {
//            UserSession session = sessions.get(i);
//            if (session.getSessionHandle() == sessionHandle) {
//                sessions.remove(session);
//            }
//        }
        Iterator<UserSession> iterator = sessions.iterator();
        while (iterator.hasNext()){
            int handle = iterator.next().getSessionHandle();
            if (sessionHandle == handle){
                iterator.remove();
            }
        }
    }

    public void deleteExpired() {
//        for (UserSession session : sessions) {
//            ZonedDateTime currentTime = ZonedDateTime.now();
//            ZonedDateTime lastAccessTime = ZonedDateTime.from(session.getLastAccess().plusSeconds(sessionValid));
//            if (currentTime.isAfter(lastAccessTime)) {
//                sessions.remove(session);
//            }
//        }

        Iterator<UserSession> iterator = sessions.iterator();
        while(iterator.hasNext()){
            ZonedDateTime currentTime = ZonedDateTime.now();
            ZonedDateTime lastAccessTime = ZonedDateTime.from(iterator.next().getLastAccess().plusSeconds(sessionValid));
            if (currentTime.isAfter(lastAccessTime)) {
                iterator.remove();
            }
        }
    }


    public static void main(String[] args) {
        SessionManager test = new SessionManager(180);

    }
}
