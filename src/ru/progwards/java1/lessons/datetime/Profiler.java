package ru.progwards.java1.lessons.datetime;

import java.util.*;

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
    public static void exitSection(String name) {
        long end = System.currentTimeMillis();
        StatisticInfo statisticInfo = null;
        for (StatisticInfo info : listStatic) {
            if (info.sectionName.equals(name)) {
                statisticInfo = info;
            }
        }
        assert statisticInfo != null;
        statisticInfo.setEndTime(end);
        /* здесь надо - Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах*/
    }

    /* получить профилировочную статистику, отсортировать по наименованию секции */
    public static List<StatisticInfo> getStatisticInfo() {
        ArrayList<StatisticInfo> list = new ArrayList<>();
        for (Map.Entry<String, StatisticSession> entry : counter().entrySet()) {
            list.add(new StatisticInfo(entry.getValue().sessionName, entry.getValue().sessionDuration, entry.getValue().sessionCount));
        }
        return list;
    }

    private static ArrayList<StatisticInfo> findParent() {
        ArrayList<StatisticInfo> levelList = new ArrayList<>();
        listStatic.get(0).setLevel(1);

        for (int i = 1; i < listStatic.size()-1; i++) {
            int idLevel = listStatic.get(i-1).level;
            long endPrevTime = listStatic.get(i-1).endTime;
            long startCurrentTime = listStatic.get(i).startTime;
            long startNextTime = listStatic.get(i+1).startTime;
            String previousName = listStatic.get(i-1).sectionName;
            String currentName = listStatic.get(i).sectionName;

            if (!(previousName.equals(currentName)) && startCurrentTime < endPrevTime){
                listStatic.get(i).setLevel(idLevel+1);
            } else if (previousName.equals(currentName) && startCurrentTime == endPrevTime){
                listStatic.get(i).setLevel(idLevel);
            } else if (!(previousName.equals(currentName)) && startCurrentTime == endPrevTime && startCurrentTime < startNextTime && listStatic.get(i).level == 0){
                listStatic.get(i).setLevel(idLevel);
            }
        }
//        System.out.println(listStatic);
        return levelList;
    }

    private static TreeMap<String, StatisticSession> counter() {
        TreeMap<String, StatisticSession> treeList = new TreeMap<>();
        for (StatisticInfo info : findParent()) {
            String sessionName = info.getSectionName();
            int sessionCount = info.getCount();
            long sessionDuration = info.getDuration();
            if (treeList.containsKey(sessionName)) {
                sessionCount += treeList.get(sessionName).sessionCount;
                sessionDuration += treeList.get(sessionName).sessionDuration;
            }
            treeList.put(sessionName, new StatisticSession(sessionName, sessionCount, sessionDuration));
        }
        return treeList;
    }


    public static void main(String[] args) throws InterruptedException {
        int timer = 30;
//        for (int k = 0; k < 2; k++) {
//            for (int j = 1; j <= 2; j++) {
//                enterSection("session-1");
//                Thread.sleep(timer);
//                for (int i = j + 1; i <= 3; i++) {
//                    enterSection("session-2");
//                    Thread.sleep(timer);
//                    for (int b = 1; b <= 2; b++) {
//                        enterSection("session-3");
//                        Thread.sleep(timer);
//                        exitSection("session-3");
//                        timer += 15;
//                    }
//                    exitSection("session-2");
//                    timer += 35;
//                }
//                enterSection("session-4");
//                Thread.sleep(timer);
//                exitSection("session-4");
//                timer += 25;
//
//                exitSection("session-1");
//                timer += 20;
//            }
//        }

        for (int j = 1; j <= 2; j++) {
            enterSection("session-1");
            Thread.sleep(timer);
            for (int i = 1; i <= 3; i++) {
                enterSection("session-2");
                Thread.sleep(timer);
                for (int k = 1; k <= 2; k++) {
                    enterSection("session-3");
                    Thread.sleep(timer);
                    exitSection("session-3");
                    timer += 15;
                }
                exitSection("session-2");
                timer += 15;
            }
            enterSection("session-4");
            Thread.sleep(timer);
            exitSection("session-4");
            timer += 20;

            exitSection("session-1");
            timer += 25;
        }

//        for (int j = 1; j <= 2; j++) {
//            enterSection("session-1");
//            Thread.sleep(timer);
//            for (int i = 1; i <= 3; i++) {
//                enterSection("session-2");
//                Thread.sleep(timer);
//                exitSection("session-2");
//                timer += 15;
//            }
//            enterSection("session-3");
//            Thread.sleep(timer);
//            exitSection("session-3");
//            timer += 20;
//
//            exitSection("session-1");
//            timer += 25;
//        }

//        for (int i = 0; i < 10; i++) {
//            enterSection("session1");
//            Thread.sleep(timer);
//            timer += 25;
//            exitSection("session1");
//            timer += 35;
//        }

//        for (StatisticInfo statisticInfo : listStatic) {
//            System.out.println(statisticInfo);
//        }
//        System.out.println();
//
//
//        for (Map.Entry<String, StatisticSession> entry : counter().entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }
//        System.out.println();
//
//        for (StatisticInfo info : getStatisticInfo()) {
//            System.out.println(info);
//        }
//        System.out.println();

//        findParent();
    }

}
