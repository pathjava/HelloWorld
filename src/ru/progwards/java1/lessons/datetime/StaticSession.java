package ru.progwards.java1.lessons.datetime;

public class StaticSession {
    String sessionName;
    int sessionCount;
    Long sessionDuration;

    public StaticSession(String sessionName, int sessionCount, Long sessionDuration) {
        this.sessionName = sessionName;
        this.sessionCount = sessionCount;
        this.sessionDuration = sessionDuration;
    }

    @Override
    public String toString() {
        return "StaticSession{" +
                "sessionName = " + sessionName +
                ", sessionCount = " + sessionCount +
                ", sessionDuration = " + sessionDuration +
                '}';
    }
}
