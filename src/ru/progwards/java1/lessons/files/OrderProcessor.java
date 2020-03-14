package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.*;
import java.util.*;

public class OrderProcessor {
    private Path startPath;
    /* поле для подсчета ошибочных файлов */
    private int errorFile = 0;
    private List<Order> listOrder = new ArrayList<>();
    private List<OrderItem> listItem;
    /* ArrayList для сохранения путей на ошибочные файлы */
    private List<Path> notValidFiles = new ArrayList<>();

    public OrderProcessor(String startPath) {
        this.startPath = Paths.get(startPath);
    }

    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        /* шаблон для определения файла по расширению и имени */
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/???-??????-????.csv");
        try {
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    /* проверка совпадения условий на файл по шаблону и по параметрам в методе checkTimeModifiedAndShopId() */
                    if (pathMatcher.matches(path) && checkTimeModifiedAndShopId(path, start, finish, shopId)) {
                        /* проверка совпадения условий в методе checkOrderItem() */
                        if (checkOrderItem(path)) {
                            /* создаем объект Order */
                            Order order = new Order();
                            /* разбиваем имя файла и помещаем сегменты в массив */
                            String[] segmentsFileName = path.getFileName().toString().substring(0, path.getFileName().toString().lastIndexOf(".")).split("-");
                            /* если каждый из сегментов соответствует условию, присваиваем значение полю объекта */
                            if (segmentsFileName[0].length() == 3)
                                order.shopId = segmentsFileName[0];
                            if (segmentsFileName[1].length() == 6)
                                order.orderId = segmentsFileName[1];
                            if (segmentsFileName[2].length() == 4)
                                order.customerId = segmentsFileName[2];
                            /* получаем Last Modified из файла */
                            FileTime fileTime = null;
                            try {
                                fileTime = Files.getLastModifiedTime(Paths.get(String.valueOf(path)));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            assert fileTime != null;
                            order.datetime = LocalDateTime.ofInstant(fileTime.toInstant(), ZoneId.systemDefault());
                            /* присваиваем List<OrderItem> listItem в поле объекта */
                            order.items = listItem;
                            /* в методе fullSumCostItems() считаем сумму заказов и присваиваем полю объекта */
                            order.sum = fullSumCostItems(listItem);
                            /* помещаем объект order в ArrayList */
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
        /* получаем shopId из имени файла */
        String checkShopId = path.getFileName().toString().substring(0, 3);
        /* проверяем на условие shopId */
        if (checkShopId.equals(shopId) || shopId == null) {
            FileTime fileTime = null;
            try {
                fileTime = Files.getLastModifiedTime(Paths.get(String.valueOf(path)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert fileTime != null;
            long timeModifiedInSeconds = fileTime.toInstant().toEpochMilli();
            long startDateInSeconds = 0;
            if (start != null) {
                startDateInSeconds = start.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
            }
            long finishDateInSeconds = 0;
            if (finish != null) {
                finishDateInSeconds = finish.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
            }

            if (startDateInSeconds == 0 && finishDateInSeconds == 0)
                return true;
            if (startDateInSeconds == 0 && timeModifiedInSeconds <= finishDateInSeconds)
                return true;
            if (finishDateInSeconds == 0 && timeModifiedInSeconds >= startDateInSeconds)
                return true;
            return timeModifiedInSeconds >= startDateInSeconds && timeModifiedInSeconds <= finishDateInSeconds;
        }
        return false;
    }

    private boolean checkOrderItem(Path path) {
        /* заводим временный массив ArrayList */
        List<String> temporaryItem = new ArrayList<>();
        listItem = new ArrayList<>();
        try {
            /* считываем содержимое файла в массив ArrayList */
            temporaryItem = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* проверяем, пустой массив ArrayList или нет */
        if (temporaryItem.isEmpty()) {
            errorFile++;
            notValidFiles.add(path);
            return false;
        }
        /* считываем содержимое массива ArrayList */
        for (String s : temporaryItem) {
            /* делим строки по запятой и помещаем в ячейки массива строк */
            String[] item = s.split(",");
            /* проверяем, является ли длина массива трём */
            if (item.length != 3) {
                errorFile++;
                notValidFiles.add(path);
                return false;
            }
            /* создаем объект класса OrderItem и присваиваем полям значения ячеек массива строк item */
            OrderItem orderItem = new OrderItem();
            orderItem.googsName = item[0];
            /* методом trim() сжимаем - удаляем лишние пробелы */
            orderItem.count = Integer.parseInt(item[1].trim());
            orderItem.price = Double.parseDouble(item[2].trim());
            /* добавляем объект в массив */
            listItem.add(orderItem);
        }
        /* переопределяем компаратор через анонимный класс и сортирует массив по именам товаров */
        listItem.sort(new Comparator<OrderItem>() {
            @Override
            public int compare(OrderItem o1, OrderItem o2) {
                return o1.googsName.compareTo(o2.googsName);
            }
        });
        /* очищаем временный массив */
        temporaryItem.clear();
        return true;
    }

    private double fullSumCostItems(List<OrderItem> listItem) {
        double fullSum = 0.0;
        for (OrderItem item : listItem) {
            /* получаем стоимость товара и умножаем на количество купленных товаров */
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
        /* в данном случае сортировка через переопределенный компаратор, находящийся в классе Order
        * вариант выше и этот отличаются реализацией - это только для себя - для отработки */
        sortedList.sort(new Order.ShopIdComparator());
        return sortedList;
    }

    public Map<String, Double> statisticsByShop() {
        /* заводим TreeMap */
        Map<String, Double> salesVolumesList = new TreeMap<>();
        for (Order order : listOrder) {
            /* получаем сумму заказов */
            double fullSum = order.getSum();
            /* если ключ уже есть в TreeMap, то к ранее полученной сумме складываем сумму из TreeMap */
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
