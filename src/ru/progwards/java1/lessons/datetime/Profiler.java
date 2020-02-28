package ru.progwards.java1.lessons.datetime;

import java.util.*;

public class Profiler {
    public static List<StatisticInfo> listStatistic = new ArrayList<>();

    /* войти в профилировочную секцию, замерить время входа */
    public static void enterSection(String name) {
        long start = System.currentTimeMillis();
        StatisticInfo statisticInfo = new StatisticInfo(name);
        statisticInfo.setStartTime(start);
        listStatistic.add(statisticInfo);
    }

    /* выйти из профилировочной секции. Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах */
    public static void exitSection(String name) {
        long end = System.currentTimeMillis();
//        StatisticInfo statisticInfo = null;
//        for (StatisticInfo info : listStatic) {
//            if (info.sectionName.equals(name)) {
//                statisticInfo = info;
//            }
//        }
//        assert statisticInfo != null;
        StatisticInfo statisticInfo = new StatisticInfo(name);
        statisticInfo.setEndTime(end);
        listStatistic.add(statisticInfo);
        /* здесь надо - Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах*/
    }

    /* получить профилировочную статистику, отсортировать по наименованию секции */
    public static List<StatisticInfo> getStatisticInfo() {
        ArrayList<StatisticInfo> list = new ArrayList<>();
        for (Map.Entry<String, StatisticSession> entry : counter().entrySet()) {
            list.add(new StatisticInfo(entry.getValue().sessionName, entry.getValue().startDuration, entry.getValue().sessionCount));
        }
        return list;
    }

    private static List<StatisticInfo> findParent() {
        listStatistic.get(0).setLevel(1);

        for (int i = 1; i < listStatistic.size(); i++) {
            int idLevel = listStatistic.get(i - 1).level;
            long startPreviousTime = listStatistic.get(i - 1).startTime;
            long endPreviousTime = listStatistic.get(i - 1).endTime;
            long startCheckTime = listStatistic.get(i).startTime;
            long endCheckTime = listStatistic.get(i).endTime;

            if (startCheckTime > endPreviousTime && endPreviousTime == 0 && endCheckTime == 0) {
                listStatistic.get(i).setLevel(idLevel + 1);
            } else if (startCheckTime == 0 && endPreviousTime == 0 && endCheckTime != 0 || startCheckTime == endPreviousTime && endCheckTime == 0) {
                listStatistic.get(i).setLevel(idLevel);
            } else if (startCheckTime == 0 && startPreviousTime == 0 && endCheckTime != 0) {
                listStatistic.get(i).setLevel(idLevel - 1);
            } else if (startCheckTime == endPreviousTime && startPreviousTime == 0) {
                listStatistic.get(i).setLevel(idLevel);
            }
        }
        for (StatisticInfo statisticInfo : listStatistic) {
            if (statisticInfo.startTime == 0) {
                statisticInfo.setCount(0);
            }
        }
        return listStatistic;
    }

    private static TreeMap<String, StatisticSession> counter() {
        TreeMap<String, StatisticSession> treeList = new TreeMap<>();
        for (StatisticInfo info : findParent()) {
            String sessionName = info.getSectionName();
            int sessionLevel = info.getLevel();
            int sessionCount = info.getCount();
            long startDuration = info.getStartTime();
            long endDuration = info.getEndTime();
            if (treeList.containsKey(sessionName)) {
                sessionCount += treeList.get(sessionName).sessionCount;
                startDuration += treeList.get(sessionName).startDuration;
                endDuration += treeList.get(sessionName).endDuration;
            }
            treeList.put(sessionName, new StatisticSession(sessionName, sessionCount, startDuration, endDuration, sessionLevel));
        }
        for (Map.Entry<String, StatisticSession> entry : treeList.entrySet()) {
            StatisticSession statisticSession = entry.getValue();
            long duration = statisticSession.endDuration - statisticSession.startDuration;
            statisticSession.setSessionDuration(duration);
        }
        return treeList;
    }


    public static void main(String[] args) throws InterruptedException {
        int timer = 15;
//        for (int j = 1; j <= 2; j++) {
//            enterSection("session-1");
//            Thread.sleep(timer);
//            for (int i = 1; i <= 3; i++) {
//                enterSection("session-2");
//                Thread.sleep(timer);
//                for (int k = 1; k <= 2; k++) {
//                    enterSection("session-3");
//                    Thread.sleep(timer);
//                    exitSection("session-3");
//                    timer += 15;
//                }
//                exitSection("session-2");
//                timer += 15;
//            }
//            enterSection("session-4");
//            Thread.sleep(timer);
//            exitSection("session-4");
//            timer += 20;
//
//            exitSection("session-1");
//            timer += 25;
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
                    for (int f = 1; f <= 2; f++) {
                        enterSection("session-4");
                        Thread.sleep(timer);
                        exitSection("session-4");
                        timer += 5;
                    }
                    exitSection("session-3");
                    timer += 10;
                }
                exitSection("session-2");
                timer += 7;
            }
            enterSection("session-5");
            Thread.sleep(timer);
            exitSection("session-5");
            timer += 11;

            enterSection("session-6");
            Thread.sleep(timer);
            for (int t = 1; t <= 2; t++) {
                enterSection("session-7");
                Thread.sleep(timer);
                for (int f = 1; f <= 2; f++) {
                    enterSection("session-8");
                    Thread.sleep(timer);
                    exitSection("session-8");
                    timer += 5;
                }
                exitSection("session-7");
                timer += 5;
            }
            exitSection("session-6");
            timer += 6;

            exitSection("session-1");
            timer += 8;
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

        findParent();

        for (StatisticInfo statisticInfo : listStatistic) {
            System.out.println(statisticInfo);
        }
        System.out.println();
//
//
        for (Map.Entry<String, StatisticSession> entry : counter().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();
//
//        for (StatisticInfo info : getStatisticInfo()) {
//            System.out.println(info);
//        }
//        System.out.println();


    }

}