package ru.progwards.java1.lessons.datetime;

import java.util.List;

public class Profiler {
    /* войти в профилировочную секцию, замерить время входа */
    public static void enterSection(String name){

    }

    /* выйти из профилировочной секции. Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах */
    public static void exitSection(String name){

    }

    /* получить профилировочную статистику, отсортировать по наименованию секции */
//    public static List<StatisticInfo> getStatisticInfo(){
//
//    }

    public class StatisticInfo{
        public String sectionName; /* имя секции */
        public int fullTime; /* полное время выполнения секции в миллисекундах */
        public int selfTime; /* чистое время выполнения секции в миллисекундах. Для вложенных секций, из времени выполнения внешней секции нужно вычисть времена выполнения вложенных секций */
        public int count; /* количество вызовов. В случае, если вызовов более одного, fullTime и selfTime содержат суммарное время выполнения всех вызовов */
    }
}
