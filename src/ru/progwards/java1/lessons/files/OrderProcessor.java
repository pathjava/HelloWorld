package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.*;
import java.util.*;

public class OrderProcessor {
    private Path startPath;
    private int errorFile = 0;
    public Order order;
    private List<Order> listOrder = new ArrayList<>();
    private List<OrderItem> listItem;
    private List<Path> notValidFiles = new ArrayList<>();

    public OrderProcessor(String startPath) {
        this.startPath = Paths.get(startPath);
    }

    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");
        try {
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(path) && checkTimeModifiedAndShopId(path, start, finish, shopId)) {
                        if (checkOrderItem(path)) {
                            order = new Order();
                            order.setShopId(path.getFileName().toString().substring(0, 3));
                            order.setOrderId(path.getFileName().toString().substring(4, 10));
                            order.setCustomerId(path.getFileName().toString().substring(11, 15));
                            FileTime fileTime = null;
                            try {
                                fileTime = Files.getLastModifiedTime(Paths.get(String.valueOf(path)));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            assert fileTime != null;
                            LocalDateTime localDateTime = LocalDateTime.ofInstant(fileTime.toInstant(), ZoneOffset.UTC);
                            order.setDatetime(localDateTime);
                            order.setItems(listItem);
                            order.setSum(fullSumCostItems(listItem));
                            listOrder.add(order);
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return errorFile;
    }

    private boolean checkTimeModifiedAndShopId(Path path, LocalDate start, LocalDate finish, String shopId) {
        boolean checkTime = false;

        String checkLengthFileName = path.getFileName().toString();
        if (checkLengthFileName.length() != 19) {
            errorFile++;
            notValidFiles.add(path);
            return false;
        }
        String checkShopId = path.getFileName().toString().substring(0, 3);

        if (checkShopId.equals(shopId) || shopId == null) {
            FileTime fileTime = null;
            try {
                fileTime = Files.getLastModifiedTime(Paths.get(String.valueOf(path)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert fileTime != null;
            LocalDate localDate = LocalDate.ofInstant(fileTime.toInstant(), ZoneOffset.UTC);
            if (start == null) {
                if (localDate.compareTo(finish) <= 0) {
                    checkTime = true;
                }
            } else if (finish == null) {
                if (localDate.compareTo(start) >= 0) {
                    checkTime = true;
                }
            } else if (localDate.compareTo(start) >= 0 && localDate.compareTo(finish) <= 0)
                checkTime = true;
        }

        return checkTime;
    }

    private boolean checkOrderItem(Path path) {
        List<String> temporaryItem = new ArrayList<>();
        OrderItem orderItem;
        listItem = new ArrayList<>();
        try {
            temporaryItem = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (temporaryItem.isEmpty()) {
            errorFile++;
            notValidFiles.add(path);
            return false;
        }
        for (String s : temporaryItem) {
            String[] item = s.split(",");
            if (item.length != 3) {
                errorFile++;
                notValidFiles.add(path);
                return false;
            }
            orderItem = new OrderItem();
            orderItem.setGoogsName(item[0]);
            orderItem.setCount(Integer.parseInt(item[1]));
            orderItem.setPrice(Double.parseDouble(item[2]));
            listItem.add(orderItem);
        }
        temporaryItem.clear();
        return true;
    }

    private double fullSumCostItems(List<OrderItem> listItem) {
        double fullSum = 0.0;
        for (OrderItem item : listItem) {
            fullSum += (item.getPrice() * item.getCount());
        }
        return fullSum;
    }

    public List<Order> process(String shopId) {
        List<Order> sortedList = new ArrayList<>();
        for (Order sortTime : listOrder) {
            if (shopId == null) {
                sortedList.add(sortTime);
            } else if (sortTime.getShopId().equals(shopId)) {
                sortedList.add(sortTime);
            }
        }
        sortedList.sort(new Order.ShopIdComparator());
        return sortedList;

//        Collections.sort(listOrder, new Comparator<Order>() {
//            @Override
//            public int compare(Order o1, Order o2) {
//                if (shopId == null){
//                    return o1.datetime.compareTo(o2.datetime);
//                }
//                if (shopId.equals(listOrder.listIterator().next().shopId)){
//                    return o1.datetime.compareTo(o2.datetime);
//                }
//                return 0;
//            }
//        });
//        return listOrder;
    }

    public Map<String, Double> statisticsByShop() {
        Map<String, Double> salesVolumesList = new TreeMap<>();
        for (Order order : listOrder) {
            double fullSum = order.getSum();
            if (salesVolumesList.containsKey(order.getShopId())) {
                fullSum += salesVolumesList.get(order.getShopId());
            }
            salesVolumesList.put(order.getShopId(), fullSum);
        }
        return salesVolumesList;
    }

    public Map<String, Double> statisticsByGoods() {
        Map<String, Double> salesGoodsList = new TreeMap<>();
        for (Order order : listOrder) {
            double fullSum = order.getSum();
            if (salesGoodsList.containsKey(order.items.listIterator().next().googsName)) {
                fullSum += salesGoodsList.get(order.items.listIterator().next().googsName);
            }
            salesGoodsList.put(order.items.listIterator().next().googsName, fullSum);
        }
        return salesGoodsList;
    }

    public Map<LocalDate, Double> statisticsByDay() {
        Map<LocalDate, Double> salesDateList = new TreeMap<>();
        for (Order order : listOrder) {
            LocalDate localDate = order.getDatetime().toLocalDate();
            double fullSum = order.getSum();
            if (salesDateList.containsKey(localDate)) {
                fullSum += salesDateList.get(localDate);
            }
            salesDateList.put(localDate, fullSum);
        }
        return salesDateList;
    }

//    Задача 3, Класс OrderProcessor: не пройдено, оценка: 0.0
//    Комментарий:
//    ERROR: Тест "Метод loadOrders(LocalDate start, LocalDate finish, String shopId)" не пройден. Во время выполнения возникло исключение java.lang.NullPointerException
//    ERROR: Тест "Метод process(String shopId)" не пройден. Во время выполнения возникло исключение java.lang.NullPointerException
//    ERROR: Тест "Метод statisticsByShop()" не пройден. Во время выполнения возникло исключение java.lang.NumberFormatException: For input string: " 1"
//    ERROR: Тест "Метод statisticsByGoods()" не пройден. Во время выполнения возникло исключение java.lang.NumberFormatException: For input string: " 1"
//    ERROR: Тест "Метод statisticsByDay()" не пройден. Во время выполнения возникло исключение java.lang.NumberFormatException: For input string: " 1"
//    По данной задаче в целом не зачет, решение возвращено на доработку. Задача выполнена на 0.00%


    public static void main(String[] args) {
        OrderProcessor test = new OrderProcessor("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\files\\orders");

        System.out.println(test.loadOrders(LocalDate.now().minusDays(3), LocalDate.now(), null));

//        System.out.println("-----------------------------");
//        for (OrderItem orderItem : test.listItem) {
//            System.out.println(orderItem);
//        }

        System.out.println("-----------------------------");
        for (Map.Entry<String, Double> entry : test.statisticsByShop().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("-----------------------------");
        for (Map.Entry<String, Double> entry : test.statisticsByGoods().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("-----------------------------");
        for (Map.Entry<LocalDate, Double> entry : test.statisticsByDay().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("-----------------------------");
        for (Order sort : test.process("S02")) {
            System.out.println(sort);
        }

        System.out.println("-----------------------------");
        for (Path notValidFile : test.notValidFiles) {
            System.out.println(notValidFile);
        }

        System.out.println("-----------------------------");
        for (Order order : test.listOrder) {
            System.out.println(order);
        }
    }
}
