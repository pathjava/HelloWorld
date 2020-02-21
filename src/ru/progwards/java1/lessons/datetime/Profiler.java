package ru.progwards.java1.lessons.datetime;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Profiler {
    public static ArrayList<StatisticInfo> listStatic = new ArrayList<>();

    /* войти в профилировочную секцию, замерить время входа */
    public static void enterSection(String name) {
        long start = System.currentTimeMillis();
        StatisticInfo statisticInfo = new StatisticInfo(name);
        statisticInfo.setStartTime(start);
        listStatic.add(statisticInfo);
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
//        statisticInfo.setDuration(statisticInfo.getEndTime() - statisticInfo.getStartTime());
    }

    /* получить профилировочную статистику, отсортировать по наименованию секции */
    public static List<StatisticInfo> getStatisticInfo(){
        StatisticInfo listStat = new StatisticInfo("session1");
        ArrayList<StatisticInfo> list = new ArrayList<>();
        list.add(listStat);
        return list;
    }

    private static TreeMap<String, StaticSession> counter(){
        TreeMap<String, StaticSession> treeList = new TreeMap<>();
        for (StatisticInfo info : listStatic) {
            String sessionName = info.getSectionName();
            int sessionCount = info.getCount();
            Long sessionDuration = info.getDuration();
            if (treeList.containsKey(sessionName)){
                sessionCount += treeList.get(sessionName).sessionCount;
                sessionDuration += treeList.get(sessionName).sessionDuration;
            }
            treeList.put(sessionName, new StaticSession(sessionName, sessionCount, sessionDuration));
        }
        return treeList;
    }



    public static void main(String[] args) throws InterruptedException {
        int timer = 50;
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i <= 5; i++) {
                enterSection("session" + i);
                Thread.sleep(timer);
                exitSection("session" + i);
                timer += 35;
            }
        }
//        for (int i = 0; i < 10; i++) {
//            enterSection("session1");
//            Thread.sleep(timer);
//            timer += 25;
//            exitSection("session1");
//            timer += 35;
//        }

        for (StatisticInfo statisticInfo : listStatic) {
            System.out.println(statisticInfo);
        }
        System.out.println();
        System.out.println(counter());
    }

}
