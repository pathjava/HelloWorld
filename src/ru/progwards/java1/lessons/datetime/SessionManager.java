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

    /* добавляем новую пользовательскую сессию в ArrayList */
    public void add(UserSession userSession) {
        sessions.add(userSession);
    }

    public UserSession find(String userName) {
        /* заводим переменную типа и присваиваем ей null */
        UserSession result = null;
        /* в цикле по массиву sessions ищем имя userName и в случае обнаружения присваиваем сессию в result, после чего прерываем цикл */
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
        /* в цикле по массиву sessions ищем хэндл sessionHandle и в случае обнаружения присваиваем сессию в result, после чего прерываем цикл */
        for (UserSession session : sessions) {
            if (session.getSessionHandle() == sessionHandle) {
                result = session;
                break;
            }
        }
        return validSession(result);
    }

    public UserSession validSession(UserSession session) {
        /* проверка на существование сессии с именем userName или с хэндл sessionHandle */
        if (session == null) {
            return null;
        }
        /* проверка на валидность сессии */
        /* переменной validTime присваиваем результат сложения: время последней сессии + время валидности сессии */
        ZonedDateTime validTime = ZonedDateTime.from(session.getLastAccess().plusSeconds(sessionValid));
        /* если текущее время опережает сумму времени из validTime, значит сесссия истекла */
        if (ZonedDateTime.now().isAfter(validTime)) {
            return null;
        }
        /* обновление время доступа */
        session.updateLastAccess();
        return session;
    }

    public void delete(int sessionHandle) {
        /* через итератор ищем сессию по sessionHandle и в случае равенства удаляем сессию */
        Iterator<UserSession> iterator = sessions.iterator();
        while (iterator.hasNext()) {
            int handle = iterator.next().getSessionHandle();
            if (sessionHandle == handle) {
                iterator.remove();
            }
        }
    }

    public void deleteExpired() {
        /* через итератор ищем все сессии с истекшим сроком валидности и удаляем их */
        Iterator<UserSession> iterator = sessions.iterator();
        while (iterator.hasNext()) {
            /* переменной validTime присваиваем результат сложения: время последней сессии + время валидности сессии */
            ZonedDateTime validTime = ZonedDateTime.from(iterator.next().getLastAccess().plusSeconds(sessionValid));
            /* если текущее время опережает сумму времени из validTime, значит сесссия истекла */
            if (ZonedDateTime.now().isAfter(validTime)) {
                iterator.remove();
            }
        }
    }

    @Override
    public String toString() {
        return "SessionManager{" + sessions + '}';
    }

    public static void main(String[] args) {
        SessionManager testSession = new SessionManager(3);
        testSession.sessionValid = 3;
        String nameUser = "user-1";
        UserSession user1 = new UserSession(nameUser);
        if (testSession.find(nameUser) == null) testSession.add(user1);
        System.out.println(testSession.get(user1.getSessionHandle()));
        System.out.println(testSession.get(user1.getSessionHandle()));
        System.out.println(testSession.get(user1.getSessionHandle()));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(testSession.get(user1.getSessionHandle()));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("null=" + testSession.get(user1.getSessionHandle()));
        System.out.println();

        UserSession user2 = new UserSession("user-2");
        testSession.add(user2);
        for (UserSession session : testSession.sessions) {
            System.out.println(session);
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UserSession user3 = new UserSession("user-3");
        testSession.add(user3);
        for (UserSession session : testSession.sessions) {
            System.out.println(session);
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (UserSession session : testSession.sessions) {
            System.out.println(session);
        }

        System.out.println();
        System.out.println("запуск метода - deleteExpired()");
        testSession.deleteExpired();

        System.out.println();
        System.out.println(testSession);
        testSession.delete(user3.getSessionHandle());
        System.out.println(testSession);
    }
}
