package ru.progwards.java1.lessons.datetime;

public class StatisticSession {
    String sessionName;
    int sessionCount;
    long sessionDuration;
    int sessionLevel;

    public StatisticSession(String sessionName, int sessionCount, long sessionDuration, int sessionLevel) {
        this.sessionName = sessionName;
        this.sessionCount = sessionCount;
        this.sessionDuration = sessionDuration;
        this.sessionLevel = sessionLevel;
    }

    public boolean isChild(StatisticSession statisticSession){
        return false; // TODO: проверить, является ли сессия вложенной
    }

    @Override
    public String toString() {
        return "StatisticSession{" +
                "sessionName='" + sessionName + '\'' +
                ", sessionCount=" + sessionCount +
                ", sessionDuration=" + sessionDuration +
                ", sessionLevel=" + sessionLevel +
                '}';
    }
}
