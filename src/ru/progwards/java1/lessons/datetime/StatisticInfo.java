package ru.progwards.java1.lessons.datetime;

public class StatisticInfo {
    public String sectionName;
    public int fullTime;
    public int selfTime;
    public int count = 1;

    public long startTime = 0;
    public long endTime;
    public int level = 0;

    public StatisticInfo(String name) {
        this.sectionName = name;
    }

    public StatisticInfo(String sessionName, long sessionDuration, int sessionCount, long startDuration, long endDuration, int sessionLevel) {
        sectionName = sessionName;
        fullTime = (int) sessionDuration;
//        selfTime = (int) sessionDuration;
        count = sessionCount;
        startTime = startDuration;
        endTime = endDuration;
        level = sessionLevel;
    }

    public void setFullTime(int fullTime) {
        this.fullTime = fullTime;
    }

    public void setSelfTime(int selfTime) {
        this.selfTime = selfTime;
    }

    public String getSectionName() {
        return sectionName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public long getStartTime() {
        return startTime;
    }
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    public long getEndTime() {
        return endTime;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

//    @Override
//    public String toString() {
//        return "StatisticInfo{" +
//                "sectionName=" + sectionName +
//                ", fullTime=" + fullTime +
//                ", selfTime=" + selfTime +
//                ", count=" + count +
//                ", startTime=" + startTime +
//                ", endTime=" + endTime +
//                ", level=" + level +
//                '}';
//    }


    @Override
    public String toString() {
        return "StatisticInfo{" +
                "sectionName=" + sectionName +
                ", fullTime=" + fullTime +
                ", selfTime=" + selfTime +
                ", count=" + count +
//                ", startTime=" + startTime +
//                ", endTime=" + endTime +
//                ", level=" + level +
                '}';
    }
}
