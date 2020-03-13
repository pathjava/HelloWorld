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
    private List<Order> listOrder = new ArrayList<>();
    private List<OrderItem> listItem;
    private List<Path> notValidFiles = new ArrayList<>();

    public OrderProcessor(String startPath) {
        this.startPath = Paths.get(startPath);
    }

    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        System.out.println(start + " " + finish + " " + shopId);
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/???-??????-????.csv");
        try {
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(path) && checkTimeModifiedAndShopId(path, start, finish, shopId)) {
                        System.out.println("loadOrders - 1");
                        if (checkOrderItem(path)) {
                            System.out.println("loadOrders - 2");
                            Order order = new Order();
                            String[] segmentsFileName = path.getFileName().toString().substring(0, path.getFileName().toString().lastIndexOf(".")).split("-");
                            if (segmentsFileName[0].length() == 3) order.shopId = segmentsFileName[0];
                            if (segmentsFileName[1].length() == 6) order.orderId = segmentsFileName[1];
                            if (segmentsFileName[2].length() == 4) order.customerId = segmentsFileName[2];
                            FileTime fileTime = null;
                            try {
                                fileTime = Files.getLastModifiedTime(Paths.get(String.valueOf(path)));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            assert fileTime != null;
                            order.datetime = LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault());
                            order.items = listItem;
                            order.sum = fullSumCostItems(listItem);
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
        String checkShopId = path.getFileName().toString().substring(0, 3);
        if (checkShopId.equals(shopId) || shopId == null) {
            FileTime fileTime = null;
            try {
                fileTime = Files.getLastModifiedTime(Paths.get(String.valueOf(path)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert fileTime != null;
            LocalDate modifiedDate = LocalDate.ofInstant(fileTime.toInstant(), ZoneOffset.UTC);
            long timeInSeconds = modifiedDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
            long startInSeconds = 0;
            if (start != null) {
                startInSeconds = start.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
            }
            long finishInSeconds = 0;
            if (finish != null) {
                finishInSeconds = finish.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
            }
            if (startInSeconds == 0 && finishInSeconds == 0) return true;
            System.out.println("checkTimeModifiedAndShopId - 1");
            if (startInSeconds == 0 && timeInSeconds <= finishInSeconds) return true;
            System.out.println("checkTimeModifiedAndShopId - 2");
            if (finishInSeconds == 0 && timeInSeconds >= startInSeconds) return true;
            System.out.println("checkTimeModifiedAndShopId - 3");
            if (timeInSeconds >= startInSeconds && timeInSeconds <= finishInSeconds) checkTime = true;
            System.out.println("checkTimeModifiedAndShopId - 4");
        }
        System.out.println("checkTimeModifiedAndShopId - 5");
        return checkTime;
    }

    private boolean checkOrderItem(Path path) {
        List<String> temporaryItem = new ArrayList<>();
        listItem = new ArrayList<>();
        try {
            temporaryItem = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("checkOrderItem - 1");
        if (temporaryItem.isEmpty()) {
            errorFile++;
            notValidFiles.add(path);
            return false;
        }
        System.out.println("checkOrderItem - 2");
        for (String s : temporaryItem) {
            String[] item = s.split(",");
            for (String s1 : item) {
                System.out.println(s1);
            }
            if (item.length != 3) {
                errorFile++;
                notValidFiles.add(path);
                return false;
            }
            System.out.println("checkOrderItem - 3");
            OrderItem orderItem = new OrderItem();
            System.out.println("checkOrderItem - 4");
            System.out.println(item[0]);
            orderItem.googsName = item[0];
            System.out.println("checkOrderItem - 5");
            System.out.println(Integer.parseInt(item[1].trim()));
            orderItem.count = Integer.parseInt(item[1].trim());
            System.out.println("checkOrderItem - 6");
            orderItem.price = Double.parseDouble(item[2].trim());
            System.out.println("checkOrderItem - 7");
            listItem.add(orderItem);
            System.out.println("checkOrderItem - 8");
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

        System.out.println(test.loadOrders(null, null, null));
//        System.out.println(test.loadOrders(LocalDate.now().minusDays(5), LocalDate.now(), null));

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

//        System.out.println("-----------------------------");
//        for (Path notValidFile : test.notValidFiles) {
//            System.out.println(notValidFile);
//        }

        System.out.println("-----------------------------");
        for (Order order : test.listOrder) {
            System.out.println(order);
        }
    }
}
