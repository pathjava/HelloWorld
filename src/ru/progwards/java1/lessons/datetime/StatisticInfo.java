package ru.progwards.java1.lessons.datetime;

public class StatisticInfo {
    public String sectionName;

    public StatisticInfo(String name) {
        this.sectionName = name;
    }

    public StatisticInfo(String sessionName, long sessionDuration, int sessionCount) {
        sectionName = sessionName;
        fullTime = (int) sessionDuration;
        selfTime = (int) sessionDuration;
        count = sessionCount;
    }

    public String getSectionName() {
        return sectionName;
    }

    public int fullTime;
    public int selfTime;
    public int count = 1;

    public int getCount() {
        return count;
    }

    public long startTime = 0;
    public long endTime;
    public long duration;

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
        setDuration(getEndTime() - getStartTime());
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "StatisticInfo{" +
                "sectionName=" + sectionName +
                ", fullTime=" + fullTime +
                ", selfTime=" + selfTime +
                ", count=" + count +
//                ", startTime=" + startTime +
//                ", endTime=" + endTime +
//                ", duration=" + duration +
                '}';
    }
}
