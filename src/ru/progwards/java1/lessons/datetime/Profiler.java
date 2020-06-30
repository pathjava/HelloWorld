package ru.progwards.java1.lessons.datetime;


import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Profiler {
    /* массив для хранения данных из входа и ваыхода профилировочных секций */
    public static List<StatisticInfo> listStatistic = new ArrayList<>();

    /* войти в профилировочную секцию, замерить время входа */
    public static void enterSection(String name) {
        long start = System.currentTimeMillis();
        StatisticInfo statisticInfo = new StatisticInfo(name);
        statisticInfo.setStartTime(start);
        /* добавляем в массив данные со стартом профилировочной сессии */
        listStatistic.add(statisticInfo);
    }

    /* выйти из профилировочной секции. Замерить время выхода, вычислить промежуток времени между входом и выходом в миллисекундах */
    public static void exitSection(String name) {
        long end = System.currentTimeMillis();
        StatisticInfo statisticInfo = new StatisticInfo(name);
        statisticInfo.setEndTime(end);
        /* добавляем в массив данные с окончанием профилировочной сессии */
        listStatistic.add(statisticInfo);
    }

    /* получить профилировочную статистику, отсортировать по наименованию секции */
    public static List<StatisticInfo> getStatisticInfo() {
        List<StatisticInfo> list = new ArrayList<>();
        /* перегоняем отсортированные данные из метода sortedDate() в массив list = new ArrayList<>() */
        for (Map.Entry<String, StatisticSession> entry : sortedDate().entrySet()) {
            list.add(new StatisticInfo(entry.getValue().sessionName, entry.getValue().sessionDuration,
                    entry.getValue().sessionCount, entry.getValue().startDuration,
                    entry.getValue().endDuration, entry.getValue().sessionLevel));
        }
        /* заполняем все поля selfTime значениями fullTime - на тот случай, если сессия одноуровневая и временные показатели равны */
        for (StatisticInfo statisticInfo : list) {
            statisticInfo.setSelfTime(statisticInfo.fullTime);
        }
        /* исходя из уровней вложенности и времени старта и окончания профилировочной сессии
         * рассчитываем чистое время каждой сессии */
        for (int i = 1; i < list.size(); i++) {
            /* для получения времени checkStart делим общее время всех циклов сессии на количество циклов */
            long checkStart = (list.get(i).getStartTime() / list.get(i).count);
            long previousStart = (list.get(i - 1).getStartTime() / list.get(i - 1).count);
            long previousEnd = (list.get(i - 1).getEndTime() / list.get(i - 1).count);

            /* в условиях по времени проверяем, рассчитываем и устанавливаем чистое время каждой сессии */
            if (list.get(i).getLevel() > 1 && ((checkStart > previousStart && checkStart < previousEnd)
                    || (checkStart < previousStart && checkStart < previousEnd
                    && list.get(i - 1).getLevel() == list.get(i).getLevel() - 1)
                    || (checkStart == previousStart && checkStart < previousEnd
                    && list.get(i - 1).getLevel() == list.get(i).getLevel() - 1)
                    || (checkStart > previousStart && checkStart > previousEnd
                    && list.get(i - 1).getLevel() == list.get(i).getLevel() - 1))) {
                list.get(i - 1).setSelfTime(list.get(i - 1).fullTime - list.get(i).fullTime);
            } else if (list.get(i).getLevel() > 1 && checkStart > previousStart
                    && checkStart > previousEnd || checkStart == previousEnd) {
                /* переменная boolean отвечает за остановку цикла когда найден первый удовлетворяющий условия результат */
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

    /* метод определения и установки значения int согласно вложенности сессии */
    private static List<StatisticInfo> findLevel() {
        listStatistic.get(0).setLevel(1);

        for (int i = 1; i < listStatistic.size(); i++) {
            int idLevel = listStatistic.get(i - 1).getLevel();
            long startPreviousTime = listStatistic.get(i - 1).getStartTime();
            long endPreviousTime = listStatistic.get(i - 1).getEndTime();
            long startCheckTime = listStatistic.get(i).getStartTime();
            long endCheckTime = listStatistic.get(i).getEndTime();

            /* по временным показателям определяем и устанавливаем уровень вложенности сессии */
            if (startCheckTime > endPreviousTime && endPreviousTime == 0 && endCheckTime == 0) {
                listStatistic.get(i).setLevel(idLevel + 1);
            } else if (startCheckTime == 0 && endPreviousTime == 0 && endCheckTime != 0
                    || startCheckTime == endPreviousTime && endCheckTime == 0
                    || startCheckTime == endPreviousTime && startPreviousTime == 0
                    || startCheckTime > endPreviousTime && startPreviousTime == 0 && endCheckTime == 0) {
                listStatistic.get(i).setLevel(idLevel);
            } else if (startCheckTime == 0 && startPreviousTime == 0 && endCheckTime != 0) {
                listStatistic.get(i).setLevel(idLevel - 1);
            }
        }
        /* для корректного расчета количества повторяющихся сессий для значений массива,
         * содержащих только время окончания сессии устанавливаем значение в 0, таким образом,
         * в дальнейшем подсчитываем только значения по стартам сессий */
        for (StatisticInfo statisticInfo : listStatistic) {
            if (statisticInfo.getStartTime() == 0) {
                statisticInfo.setCount(0);
            }
        }
        return listStatistic;
    }

    /* складываем и сортируем данные, полученные из массива начала и окончания профилировочных сессий
     * и прошедших метод определения уровня вложенности */
    private static TreeMap<String, StatisticSession> sortedDate() {
        TreeMap<String, StatisticSession> treeList = new TreeMap<>();
        for (StatisticInfo info : findLevel()) {
            String sessionName = info.sectionName;
            int sessionLevel = info.getLevel();
            int sessionCount = info.count;
            long startDuration = info.getStartTime();
            long endDuration = info.getEndTime();
            /* если в treeList уже содержится ключ (имя сессии) тогда складываем количества запусков сессии,
             * временные показатели начала и окончания профилировочной сессии */
            if (treeList.containsKey(sessionName)) {
                sessionCount += treeList.get(sessionName).sessionCount;
                startDuration += treeList.get(sessionName).startDuration;
                endDuration += treeList.get(sessionName).endDuration;
            }
            treeList.put(sessionName, new StatisticSession(sessionName,
                    sessionCount, startDuration, endDuration, sessionLevel));
        }

        /* отнимая от времени полного окончания сессии полное время начала сессии получаем полное время сессии и устанавливаем его */
        for (Map.Entry<String, StatisticSession> entry : treeList.entrySet()) {
            StatisticSession statisticSession = entry.getValue();
            long fullDuration = statisticSession.endDuration - statisticSession.startDuration;
            statisticSession.setSessionDuration(fullDuration);
        }
        return treeList;
    }

    /* метод вывода статистики профилировки */
    @SuppressWarnings("unused")
    public static void printStatisticInfo(String fileName) {
        System.out.print(profilerDateTimeStart(fileName)); /* дата и время профилировки */
        for (StatisticInfo info : getStatisticInfo()) /* вывод статистики в консоль */
            System.out.println(info);
        /* если прописать true, то профилировочная статистика будет записываться в дополнение к ранней */
        try (FileWriter profilerFile = new FileWriter(getPathLogFile(fileName), false)) {
            profilerFile.write(profilerDateTimeStart(fileName));  /* дата и время профилировки */
            for (StatisticInfo info : getStatisticInfo()) /* запись статистики в файл */
                profilerFile.write(info + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* дата и время запуска профилировки */
    private static String profilerDateTimeStart(String fileName) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dateTime = now.format(formatter);
        String testingClassName = fileName.substring(0, fileName.length() - 5); /* имя тестируемого класса */
        return "//--- Дата и время запуска профайлера " + testingClassName + ": " + dateTime + " ---//\n";
    }

    /* формируем путь к файлу профилировки */
    private static String getPathLogFile(String fileName) {
        String directory = System.getProperty("user.dir");
        String namePackage = Profiler.class.getName(); /* заменив имя Profiler на другое, можно указать другой путь сохранения */
        int index = namePackage.lastIndexOf(".");
        if (index > -1)
            namePackage = namePackage.substring(0, index);
        namePackage = namePackage.replace(".", "\\");
        return directory + "\\src\\" + namePackage + "\\" + fileName;
    }

    public static void main(String[] args) throws InterruptedException {
//        int timer = 10;
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

//        for (int j = 1; j <= 3; j++) {
//            enterSection("session-1");
////            Thread.sleep(100);
//            if (j == 3) {
//                for (int i = 1; i <= 2; i++) {
//                    enterSection("session-2");
//                    Thread.sleep(200);
//                    for (int k = 1; k <= 1; k++) {
//                        enterSection("session-3");
//                        Thread.sleep(100);
//                        exitSection("session-3");
//                    }
////                    Thread.sleep(200);
//                    exitSection("session-2");
//                }
//            }
//            Thread.sleep(100);
//            exitSection("session-1");
//        }

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


//        enterSection("session-1");
//        enterSection("session-2");
//        Thread.sleep(200);
//        enterSection("session-3");
//        Thread.sleep(100);
//        exitSection("session-3");
//        exitSection("session-2");
//        enterSection("session-2");
//        enterSection("session-3");
//        Thread.sleep(100);
//        exitSection("session-3");
//        Thread.sleep(200);
//        exitSection("session-2");
//        enterSection("session-2");
//        Thread.sleep(200);
//        enterSection("session-3");
//        Thread.sleep(100);
//        exitSection("session-3");
//        exitSection("session-2");
//        enterSection("session-2");
//        Thread.sleep(200);
//        enterSection("session-3");
//        Thread.sleep(100);
//        exitSection("session-3");
//        exitSection("session-2");
//        Thread.sleep(100);
//        exitSection("session-1");
//        enterSection("session-1");
//        Thread.sleep(100);
//        exitSection("session-1");
//        enterSection("session-1");
//        Thread.sleep(100);
//        exitSection("session-1");
//        enterSection("session-1");
//        Thread.sleep(100);
//        exitSection("session-1");

//        for (int i = 0; i < 4; i++) {
//            Profiler.enterSection("Process1");
////            p1++;
//            try { Thread.sleep(100); } catch (InterruptedException e) {}
//
//            for (int n = 0; n < i; n++){
//                Profiler.enterSection("Process2");
////                p2++;
//                try { Thread.sleep(200); } catch (InterruptedException e) {}
//                Profiler.enterSection("Process3");
//                try { Thread.sleep(100); } catch (InterruptedException e) {}
//                Profiler.exitSection("Process3");
//                Profiler.exitSection("Process2");
//            }
//            Profiler.exitSection("Process1");
//        }

//        Profiler.enterSection("Process-1");
//        Thread.sleep(100);
//        Profiler.exitSection("Process-1");
//
//        Thread.sleep(100);
//
//        Profiler.enterSection("Process-1");
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//        }
//
//        for (int n = 0; n < 4; n++) {
//            Profiler.enterSection("Process-2");
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//            }
//            Profiler.enterSection("Process-3");
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//            }
//            Profiler.exitSection("Process-3");
//            Profiler.exitSection("Process-2");
//        }
//        Profiler.exitSection("Process-1");

        findLevel();

        for (StatisticInfo statisticInfo : listStatistic) {
            System.out.println(statisticInfo);
        }
        System.out.println();

        for (Map.Entry<String, StatisticSession> entry : sortedDate().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();

        for (StatisticInfo info : getStatisticInfo()) {
            System.out.println(info);
        }
    }

}

/* Решение, которое дал Сергей Алтунджи */
//import java.time.Instant;
//        import java.util.*;
//
//public class Profiler {
//
//    private static Deque<Long> sectionStartTimeDeque = new ArrayDeque<>();
//    private static Deque<String> sectionNameDeque = new ArrayDeque<>();
//    private static LinkedHashMap<String, StatisticInfo> sectionMap = new LinkedHashMap<>();
//
//    public static void enterSection(String name) {
//        sectionMap.putIfAbsent(name, new StatisticInfo(name));
//        sectionStartTimeDeque.push(Instant.now().toEpochMilli());
//        sectionNameDeque.push(name);
//    }
//
//    public static void exitSection(String name) {
//        int period = (int)(Instant.now().toEpochMilli() - sectionStartTimeDeque.pop());
//        sectionMap.get(name).count++;
//        sectionMap.get(name).fullTime += period;
//        sectionMap.get(name).selfTime += period;
//        sectionNameDeque.pop();
//        if(!sectionNameDeque.isEmpty()) {
//            String externalSectionName = sectionNameDeque.peek();
//            sectionMap.get(externalSectionName).selfTime -= period;
//        }
//    }
//
//    public static List<StatisticInfo> getStatisticInfo() {
//        return new ArrayList<StatisticInfo>(sectionMap.values());
//    }
//
//
//}