package ru.progwards.java1.lessons.datetime;

public class StatisticInfo {
    /* имя секции */
    public String sectionName;

    public StatisticInfo(String name) {
        this.sectionName = name;
        long startTime;
    }

    /* полное время выполнения секции в миллисекундах */
    public int fullTime;

    /* чистое время выполнения секции в миллисекундах. Для вложенных секций, из времени выполнения внешней секции нужно вычисть времена выполнения вложенных секций */
    public int selfTime;

    /* количество вызовов. В случае, если вызовов более одного, fullTime и selfTime содержат суммарное время выполнения всех вызовов */
    public int count;


    private long startTime;
    private long endTime;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
