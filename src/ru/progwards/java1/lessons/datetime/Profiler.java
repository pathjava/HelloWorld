package ru.progwards.java1.lessons.datetime;

import java.util.ArrayList;
import java.util.List;

public class Profiler {
    public static ArrayList<StatisticInfo> listStatic = new ArrayList<>();

    /* войти в профилировочную секцию, замерить время входа */
    public static void enterSection(String name) throws InterruptedException {
        long start = System.currentTimeMillis();
        StatisticInfo statisticInfo = new StatisticInfo(name);
        statisticInfo.setStartTime(start);
        listStatic.add(statisticInfo);
        Thread.sleep(500);
        System.out.println(start);
    }

    /* выйти из профилировочной секции. Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах */
    public static void exitSection(String name){
        long end = System.currentTimeMillis();
        StatisticInfo statisticInfo = null;
        for (StatisticInfo info : listStatic) {
            if (info.sectionName.equals(name)){
                statisticInfo = info;
            }
        }
        assert statisticInfo != null;
        statisticInfo.setEndTime(end);
        /* здесь надо - Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах*/
        statisticInfo.setDuration(statisticInfo.getStartTime() - statisticInfo.getEndTime());
        System.out.println(end);
    }

    /* получить профилировочную статистику, отсортировать по наименованию секции */
    public static List<StatisticInfo> getStatisticInfo(){
        StatisticInfo listStat = new StatisticInfo("session1");
        ArrayList<StatisticInfo> list = new ArrayList<>();
        list.add(listStat);
        return list;
    }




    public static void main(String[] args) throws InterruptedException {
        enterSection("session1");
        exitSection("session1");


//        System.out.println(listTest);
        for (StatisticInfo statisticInfo : listStatic) {
            System.out.println(statisticInfo);
        }
    }

}
