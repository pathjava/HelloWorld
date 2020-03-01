package ru.progwards.java1.lessons.datetime;

public class StatisticSession {
    public String sessionName;
    public int sessionCount;
    public long startDuration;
    public long endDuration;
    public int sessionLevel;
    public long sessionDuration;

    public StatisticSession(String sessionName, int sessionCount, long startDuration, long endDuration, int sessionLevel) {
        this.sessionName = sessionName;
        this.sessionCount = sessionCount;
        this.startDuration = startDuration;
        this.endDuration = endDuration;
        this.sessionLevel = sessionLevel;
    }

    public void setSessionDuration(long sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    @Override
    public String toString() {
        return "StatisticSession{" +
                "sessionName=" + sessionName +
                ", sessionCount=" + sessionCount +
                ", startDuration=" + startDuration +
                ", endDuration=" + endDuration +
                ", sessionLevel=" + sessionLevel +
                ", sessionDuration=" + sessionDuration +
                '}';
    }
}
