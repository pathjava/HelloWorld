package ru.progwards.java1.lessons.datetime;

public class StatisticInfo {
    /* имя секции */
    public String sectionName;

    public StatisticInfo(String name) {
        this.sectionName = name;
    }

    /* полное время выполнения секции в миллисекундах */
    public int fullTime;

    /* чистое время выполнения секции в миллисекундах. Для вложенных секций, из времени выполнения внешней секции нужно вычисть времена выполнения вложенных секций */
    public int selfTime;

    /* количество вызовов. В случае, если вызовов более одного, fullTime и selfTime содержат суммарное время выполнения всех вызовов */
    public int count;


    private long startTime = 0;
    private long endTime;
    private long duration;

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
        setDuration(getEndTime() - getStartTime());
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "StatisticInfo{" +
                "sectionName='" + sectionName + '\'' +
                ", fullTime=" + fullTime +
                ", selfTime=" + selfTime +
                ", count=" + count +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                '}';
    }
}
