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
        System.out.println("loadOrders");
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");
        System.out.println("loadOrders-1");
        try {
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    System.out.println("loadOrders-2");
                    if (pathMatcher.matches(path) && checkTimeModifiedAndShopId(path, start, finish, shopId)) {
                        System.out.println("loadOrders-3");
                        if (checkOrderItem(path)) {
                            System.out.println("loadOrders-4");
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
            for (Order order : listOrder) {
                System.out.println(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return errorFile;
    }

    private boolean checkTimeModifiedAndShopId(Path path, LocalDate start, LocalDate finish, String shopId) {
        boolean checkTime = false;
        System.out.println("checkTimeModifiedAndShopId-1");
        String checkLengthFileName = path.getFileName().toString();
        if (checkLengthFileName.length() != 19) {
            errorFile++;
            notValidFiles.add(path);
            return false;
        }
        System.out.println("checkTimeModifiedAndShopId-2");
        String checkShopId = path.getFileName().toString().substring(0, 3);
        System.out.println("checkTimeModifiedAndShopId-3");
        if (checkShopId.equals(shopId) || shopId == null) {
            FileTime fileTime = null;
            try {
                fileTime = Files.getLastModifiedTime(Paths.get(String.valueOf(path)));
                System.out.println("checkTimeModifiedAndShopId-4");
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert fileTime != null;
//            LocalDate modifiedDate = LocalDate.ofInstant(fileTime.toInstant(), ZoneOffset.UTC);
            long test = fileTime.toInstant().getEpochSecond();
//            LocalDate modifiedDate = LocalDate.
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(test), ZoneId.systemDefault());
            LocalDate modifiedDate = localDateTime.toLocalDate();
            System.out.println("checkTimeModifiedAndShopId-5");
            if (start == null) {
                System.out.println("checkTimeModifiedAndShopId-5-1");
                if (modifiedDate.compareTo(finish) <= 0) {
                    System.out.println("checkTimeModifiedAndShopId-6");
                    checkTime = true;
                }
            } else if (finish == null) {
                if (modifiedDate.compareTo(start) >= 0) {
                    System.out.println("checkTimeModifiedAndShopId-7");
                    checkTime = true;
                }
            } else if (modifiedDate.compareTo(start) >= 0 && modifiedDate.compareTo(finish) <= 0) {
                System.out.println("checkTimeModifiedAndShopId-8");
                checkTime = true;
            }
        }
        System.out.println("checkTimeModifiedAndShopId-9");
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
        for (Order order1 : sortedList) {
            System.out.println(order1);
        }
        return sortedList;

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


    public static void main(String[] args) {
        OrderProcessor test = new OrderProcessor("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\files\\orders");

        System.out.println(test.loadOrders(LocalDate.now().minusDays(5), LocalDate.now(), null));

//        System.out.println("-----------------------------");
//        for (OrderItem orderItem : test.listItem) {
//            System.out.println(orderItem);
//        }

        test.process("S02");

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

//        System.out.println("-----------------------------");
//        for (Order sort : test.process("S02")) {
//            System.out.println(sort);
//        }

//        System.out.println("-----------------------------");
//        for (Path notValidFile : test.notValidFiles) {
//            System.out.println(notValidFile);
//        }

//        System.out.println("-----------------------------");
//        for (Order order : test.listOrder) {
//            System.out.println(order);
//        }
    }
}
