package ru.progwards.java1.lessons.datetime;

import java.util.List;

public class Profiler {
    /* войти в профилировочную секцию, замерить время входа */
    public static void enterSection(String name){
        long start = System.currentTimeMillis();
        StatisticInfo startInfo = new StatisticInfo(name);
        startInfo.setStartTime(start);
    }

    /* выйти из профилировочной секции. Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах */
    public static void exitSection(String name){
        long end = System.currentTimeMillis();
        StatisticInfo endInfo = new StatisticInfo(name);
        endInfo.setEndTime(end);
    }

    /* получить профилировочную статистику, отсортировать по наименованию секции */
    public static List<StatisticInfo> getStatisticInfo(){

    }

}
