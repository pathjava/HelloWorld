package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {
    private Path startPath;
    private int errorFile = 0;
    public Order order;
    private List<Order> listOrder = new ArrayList<>();
    private List<OrderItem> listItem;

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
        if (!(checkLengthFileName.length() == 19)) {
            errorFile++;
            return false;
        }
        String checkShopId = path.getFileName().toString().substring(0, 3);
        if (!(checkShopId.equals(shopId))) {
            return false;
        }

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
        for (String s : temporaryItem) {
            String[] item = s.split(",");
            if (!(item.length == 3)) {
                errorFile++;
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
            int count = item.getCount();
            double cost = item.getPrice();
            fullSum += (cost * count);
        }
        return fullSum;
    }


    public static void main(String[] args) {
        OrderProcessor test = new OrderProcessor("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\java1\\lessons\\files\\orders");

        System.out.println(test.loadOrders(LocalDate.now().minusDays(2), LocalDate.now(), "S02"));

//        for (Order s : test.listOrder) {
//            System.out.println(s);
//        }
//        System.out.println("-----------------------------");
//        for (OrderItem orderItem : test.listItem) {
//            System.out.println(orderItem);
//        }

        System.out.println("-----------------------------");
        for (Order order : test.listOrder) {
            System.out.println(order);
        }
    }
}
