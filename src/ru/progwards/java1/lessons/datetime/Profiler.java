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
        StatisticInfo statisticInfo = new StatisticInfo(name);
        statisticInfo.setEndTime(end);
        listStatistic.add(statisticInfo);
    }

    /* получить профилировочную статистику, отсортировать по наименованию секции */
    public static List<StatisticInfo> getStatisticInfo() {
        ArrayList<StatisticInfo> list = new ArrayList<>();
        for (Map.Entry<String, StatisticSession> entry : counter().entrySet()) {
            list.add(new StatisticInfo(entry.getValue().sessionName, entry.getValue().sessionDuration, entry.getValue().sessionCount,
                    entry.getValue().startDuration, entry.getValue().endDuration, entry.getValue().sessionLevel));
        }

        for (StatisticInfo statisticInfo : list) {
            statisticInfo.setSelfTime(statisticInfo.fullTime);
        }

        for (int i = 1; i < list.size(); i++) {
            long checkStart = (list.get(i).getStartTime() / list.get(i).count);
            long previousStart = (list.get(i - 1).getStartTime() / list.get(i - 1).count);
            long previousEnd = (list.get(i - 1).getEndTime() / list.get(i - 1).count);

            if (list.get(i).getLevel() > 1 && ((checkStart > previousStart && checkStart < previousEnd)
                    || (checkStart < previousStart && checkStart < previousEnd && list.get(i - 1).getLevel() == list.get(i).getLevel() - 1)
                    || (checkStart < previousStart && checkStart == previousEnd && list.get(i - 1).getLevel() == list.get(i).getLevel() - 1)
                    || (checkStart > previousStart && checkStart > previousEnd && list.get(i - 1).getLevel() == list.get(i).getLevel() - 1))) {
                list.get(i - 1).setSelfTime(list.get(i - 1).fullTime - list.get(i).fullTime);
            } else if (list.get(i).getLevel() > 1 && checkStart > previousStart && checkStart > previousEnd || checkStart == previousEnd) {
                boolean stop = true;
                for (int j = i - 1; j >= 0 && stop; j--) {
                    if (checkStart < (list.get(j).getEndTime() / list.get(j).count)) {
                        list.get(j).setSelfTime(list.get(j).selfTime - list.get(i).fullTime);
                        stop = false;
                    }
                }
            }
        }
        return list;
    }

    private static List<StatisticInfo> findLevel() {
        listStatistic.get(0).setLevel(1);

        for (int i = 1; i < listStatistic.size(); i++) {
            int idLevel = listStatistic.get(i - 1).getLevel();
            long startPreviousTime = listStatistic.get(i - 1).getStartTime();
            long endPreviousTime = listStatistic.get(i - 1).getEndTime();
            long startCheckTime = listStatistic.get(i).getStartTime();
            long endCheckTime = listStatistic.get(i).getEndTime();

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
            if (statisticInfo.getStartTime() == 0) {
                statisticInfo.setCount(0);
            }
        }
        return listStatistic;
    }

    private static TreeMap<String, StatisticSession> counter() {
        TreeMap<String, StatisticSession> treeList = new TreeMap<>();
        for (StatisticInfo info : findLevel()) {
            String sessionName = info.sectionName;
            int sessionLevel = info.getLevel();
            int sessionCount = info.count;
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
            long fullDuration = statisticSession.endDuration - statisticSession.startDuration;
            statisticSession.setSessionDuration(fullDuration);
        }
        return treeList;
    }


    public static void main(String[] args) throws InterruptedException {
        int timer = 10;
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

//        for (int j = 1; j <= 2; j++) {
//            enterSection("session-1");
//            Thread.sleep(timer);
//            for (int i = 1; i <= 3; i++) {
//                enterSection("session-2");
//                Thread.sleep(timer);
//                for (int k = 1; k <= 2; k++) {
//                    enterSection("session-3");
//                    Thread.sleep(timer);
//                    for (int f = 1; f <= 2; f++) {
//                        enterSection("session-4");
//                        Thread.sleep(timer);
//                        exitSection("session-4");
//                        timer += 5;
//                    }
//                    exitSection("session-3");
//                    timer += 10;
//                }
//                exitSection("session-2");
//                timer += 7;
//            }
//            enterSection("session-5");
//            Thread.sleep(timer);
//            exitSection("session-5");
//            timer += 11;
//
//            enterSection("session-6");
//            Thread.sleep(timer);
//            for (int t = 1; t <= 2; t++) {
//                enterSection("session-7");
//                Thread.sleep(timer);
//                for (int f = 1; f <= 2; f++) {
//                    enterSection("session-8");
//                    Thread.sleep(timer);
//                    exitSection("session-8");
//                    timer += 5;
//                }
//                exitSection("session-7");
//                timer += 5;
//            }
//            exitSection("session-6");
//            timer += 6;
//
//            exitSection("session-1");
//            timer += 8;
//        }

        for (int j = 1; j <= 3; j++) {
            enterSection("session-1");
            Thread.sleep(100);
            if (j == 1) {
                for (int i = 1; i <= 2; i++) {
                    enterSection("session-2");
                    Thread.sleep(200);
                    for (int k = 1; k <= 1; k++) {
                        enterSection("session-3");
                        Thread.sleep(100);
                        exitSection("session-3");
                    }
                    exitSection("session-2");
                }
            }
            exitSection("session-1");
        }

//        for (int j = 1; j <= 4; j++) {
//            enterSection("session-1");
//            Thread.sleep(timer);
//            if (j == 4) {
//                for (int i = 1; i <= 2; i++) {
//                    enterSection("session-2");
//                    Thread.sleep(timer);
//                    for (int k = 1; k <= 1; k++) {
//                        enterSection("session-3");
//                        Thread.sleep(timer);
//                        exitSection("session-3");
//                        timer += 20;
//                    }
//                    exitSection("session-2");
//                    timer += 15;
//
//                }
//            }
//            exitSection("session-1");
//            timer += 25;
//        }

//        for (int j = 1; j <= 3; j++) {
//            enterSection("session-1");
//            Thread.sleep(timer);
//            if (j == 3) {
//                for (int i = 1; i <= 2; i++) {
//                    enterSection("session-2");
//                    Thread.sleep(timer);
//                    for (int k = 1; k <= 1; k++) {
//                        enterSection("session-3");
//                        Thread.sleep(timer);
//                        exitSection("session-3");
//                        timer += 20;
//                    }
//                    exitSection("session-2");
//                    timer += 15;
//                }
//            }
//            exitSection("session-1");
//            timer += 25;
//        }

//        for (int j = 1; j <= 3; j++) {
//            enterSection("session-1");
//            Thread.sleep(timer);
//            for (int i = 1; i <= 4; i++) {
//                enterSection("session-2");
//                Thread.sleep(timer);
//                for (int k = 1; k <= 1; k++) {
//                    enterSection("session-3");
//                    Thread.sleep(timer);
//                    exitSection("session-3");
//                    timer += 20;
//                }
//                exitSection("session-2");
//                timer += 15;
//            }
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

//        findLevel();
//
//        for (StatisticInfo statisticInfo : listStatistic) {
//            System.out.println(statisticInfo);
//        }
//        System.out.println();
//
        for (Map.Entry<String, StatisticSession> entry : counter().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();

        for (StatisticInfo info : getStatisticInfo()) {
            System.out.println(info);
        }
        System.out.println();


    }

}