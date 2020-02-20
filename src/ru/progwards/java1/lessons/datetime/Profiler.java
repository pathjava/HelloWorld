package ru.progwards.java1.lessons.datetime;

import java.util.*;

public class Profiler {
    public static ArrayList<StatisticInfo> listStatic = new ArrayList<>();
    public static TreeMap<String, StatisticInfo> treeStatic = new TreeMap<>();

    /* войти в профилировочную секцию, замерить время входа */
    public static void enterSection(String name) {
        long start = System.currentTimeMillis();
        StatisticInfo statisticInfo = new StatisticInfo(name);
        statisticInfo.setStartTime(start);
//        listStatic.add(statisticInfo);
        countSession(name, statisticInfo);
        treeStatic.put(name, statisticInfo);
    }

    /* выйти из профилировочной секции. Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах */
    public static void exitSection(String name){
        long end = System.currentTimeMillis();
        StatisticInfo statisticInfo = null;
//        for (StatisticInfo info : listStatic) {
//            if (info.sectionName.equals(name)){
//                statisticInfo = info;
//            }
//        }

        for (Map.Entry<String, StatisticInfo> entry : treeStatic.entrySet()) {
            if (entry.getKey().equals(name)){
                statisticInfo = entry.getValue();
                treeStatic.put(name, statisticInfo);
            }
        }
        assert statisticInfo != null;
        statisticInfo.setEndTime(end);
        /* здесь надо - Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах*/
//        statisticInfo.setDuration(statisticInfo.getEndTime() - statisticInfo.getStartTime());
    }

    /* получить профилировочную статистику, отсортировать по наименованию секции */
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

//    public static StatisticInfo countSession(String string, StatisticInfo statisticInfo){
//
//        for (Map.Entry<String, StatisticInfo> infoEntry : treeStatic.entrySet()) {
//            if (infoEntry.getKey().contains(string)){
//                infoEntry.getValue().count += 1;
//                long temp = statisticInfo.duration;
////                infoEntry.getValue().duration;
//            }
//        }
//        return statisticInfo;
//    }

//    public static Map<String, StatisticInfo> countSession(){
//        TreeMap<String, StatisticInfo> treeMap = new TreeMap<>();
//
//        for (StatisticInfo info : listStatic) {
//            if (treeMap.containsKey(info.sectionName)){
//                info.count += 1;
//                StatisticInfo temp = treeMap.get(info.sectionName);
////                statisticInfo.duration += temp;
//
//                treeMap.put(info.sectionName, info);
//            } else
//            treeMap.put(info.sectionName, info);
//        }
//        return treeMap;
//    }




    public static void main(String[] args) throws InterruptedException {
        int timer = 50;
        for (int j = 0; j < 2; j++) {
            for (int i = 1; i <= 2; i++) {
                enterSection("session" + i);
                Thread.sleep(timer);
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
