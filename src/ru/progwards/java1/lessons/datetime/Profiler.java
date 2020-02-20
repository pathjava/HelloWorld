package ru.progwards.java1.lessons.datetime;

import java.util.*;

public class Profiler {
    public static TreeMap<String, StatisticInfo> treeStatic = new TreeMap<>();

    /* войти в профилировочную секцию, замерить время входа */
    public static void enterSection(String name) {
        long start = System.currentTimeMillis();
        StatisticInfo statisticInfo = new StatisticInfo(name);
        statisticInfo.setStartTime(start);
        countSession(name, statisticInfo);
        System.out.println(statisticInfo.getStartTime());
        treeStatic.put(name, statisticInfo);
    }

    /* выйти из профилировочной секции. Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах */
    public static void exitSection(String name){
        long end = System.currentTimeMillis();
        StatisticInfo statisticInfo = null;

        for (Map.Entry<String, StatisticInfo> entry : treeStatic.entrySet()) {
            if (entry.getKey().equals(name)){
                statisticInfo = entry.getValue();
                treeStatic.put(name, statisticInfo);
            }
        }
        assert statisticInfo != null;
        statisticInfo.setEndTime(end);
        System.out.println(statisticInfo.getEndTime());
        for (Map.Entry<String, StatisticInfo> entry : treeStatic.entrySet()) {
            if (entry.getKey().equals(name)){
                countDuration(name, statisticInfo);
            }
        }
//        countDuration(name, statisticInfo);

        /* здесь надо - Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах*/
//        statisticInfo.setDuration(statisticInfo.getEndTime() - statisticInfo.getStartTime());
    }

    /* получить профилировочную статистику, отсортировать по наименованию секции */
    public static ArrayList<StatisticInfo> listStatic = new ArrayList<>(); // временный ArrayList
    public static List<StatisticInfo> getStatisticInfo(){
        return new ArrayList<>(listStatic);
    }

    public static void countSession(String string, StatisticInfo statisticInfo){
        for (Map.Entry<String, StatisticInfo> infoEntry : treeStatic.entrySet()) {
            if (infoEntry.getKey().contains(string)){
                statisticInfo.setCount(infoEntry.getValue().count += 1);
            }
        }
    }

    public static void countDuration(String string, StatisticInfo statisticInfo){
        for (Map.Entry<String, StatisticInfo> infoEntry : treeStatic.entrySet()) {
            if (infoEntry.getKey().contains(string)){
                long tempLong = infoEntry.getValue().duration;
                long tempLongTwo = statisticInfo.getDuration();
                statisticInfo.setFullTime((int) (tempLong + tempLongTwo));
            }
        }
    }



    public static void main(String[] args) throws InterruptedException {
        int timer = 50;
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i <= 2; i++) {
                enterSection("session" + i);
                Thread.sleep(timer);
                timer += 25;
                exitSection("session" + i);
                timer += 50;
            }
        }

//        for (StatisticInfo statisticInfo : listStatic) {
//            System.out.println(statisticInfo);
//        }

//        System.out.println();
//        System.out.println(getStatisticInfo());
        for (Map.Entry<String, StatisticInfo> infoEntry : treeStatic.entrySet()) {
            System.out.println(infoEntry.getKey() + " : " + infoEntry.getValue());
        }

//        System.out.println(treeStatic);
    }

}
