package ru.progwards.java1.lessons.datetime;

public class StaticSession {
    String sessionName;
    int sessionCount;
    Long sessionDuration;

    public StaticSession(String sectionName, int sessionCount, Long sessionDuration) {
        this.sessionName = sectionName;
        this.sessionCount = sessionCount;
        this.sessionDuration = sessionDuration;
    }

    @Override
    public String toString() {
        return "StaticSession{" +
                "sectionName = " + sessionName +
                ", count = " + sessionCount +
                ", sessionDuration = " + sessionDuration +
                '}';
    }
}
