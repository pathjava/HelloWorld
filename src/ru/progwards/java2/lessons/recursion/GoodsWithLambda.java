// Oleg Kiselev
// 07.05.2020, 19:58

package ru.progwards.java2.lessons.recursion;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GoodsWithLambda {

    private final List<Goods> listGoods = new ArrayList<>();

    private void setGoods(List<Goods> list) {
        listGoods.addAll(list);
        listGoods.forEach(System.out::println);
    }

    private List<Goods> sortByName() {
        listGoods.sort(Comparator.comparing(n -> n.name));
        listGoods.forEach(System.out::println);
        return listGoods;
    }

    private List<Goods> sortByNumber() {
//        listGoods.sort((a,b) -> a.number.toLowerCase().compareTo(b.number.toLowerCase()));
        listGoods.sort(Comparator.comparing(s -> s.number.toLowerCase()));
        listGoods.forEach(System.out::println);
        return listGoods;
    }

    private List<Goods> sortByPartNumber() {
        listGoods.sort(Comparator.comparing(s -> s.number.substring(0, 2).toLowerCase()));
        listGoods.forEach(System.out::println);
        return listGoods;
    }

    private List<Goods> sortByAvailabilityAndNumber() {
        List<Goods> newList = listGoods.stream().sorted(Comparator.comparing(s -> s.number.toLowerCase()))
                .sorted(Comparator.comparing(s -> s.available)).collect(Collectors.toList());
//        listGoods = listGoods.stream().sorted(Comparator.comparing(s -> s.number.toLowerCase()))
//                .sorted(Comparator.comparing(s -> s.available)).collect(Collectors.toList());
        newList.forEach(System.out::println);
        return newList;
    }

    private List<Goods> expiredAfter(Instant date) {
        List<Goods> newList = listGoods.stream().filter(s -> s.expired.compareTo(date) > 0)
                .sorted(Comparator.comparing(d -> d.expired)).collect(Collectors.toList());
        newList.forEach(System.out::println);
        return newList;
    }

    private List<Goods> сountLess(int count) {
        List<Goods> newList = listGoods.stream().filter(s -> s.available < count).collect(Collectors.toList());
        newList.forEach(System.out::println);
        return newList;
    }

    private List<Goods> сountBetween(int count1, int count2) {
        List<Goods> newList = listGoods.stream().filter(s -> s.available > count1 && s.available < count2).collect(Collectors.toList());
        newList.forEach(System.out::println);
        return newList;
    }

    public static void main(String[] args) {
        GoodsWithLambda goodsWithLambda = new GoodsWithLambda();

        List<Goods> list = List.of(
                new Goods("Песнь льда и пламени", "15а10", 5, 225.10, Instant.now()),
                new Goods("Записки о Гальской войне", "12р15", 3, 125.0, Instant.now().minus(1, ChronoUnit.DAYS)),
                new Goods("Шантарам", "20К18", 1, 301.25, Instant.now().plus(2, ChronoUnit.DAYS)),
                new Goods("Дни в Бирме", "32057", 7, 225.10, Instant.now().plus(15, ChronoUnit.HOURS)),
                new Goods("Проклятые короли", "15Ю61", 7, 145.10, Instant.now().plus(10, ChronoUnit.HOURS)),
                new Goods("Цыплёнок ястреб", "10ю11", 0, 85.10, Instant.now().plus(300, ChronoUnit.MINUTES)),
                new Goods("Великие Моголы", "15А01", 0, 205.10, Instant.now().minus(2, ChronoUnit.DAYS)),
                new Goods("Туарег", "05ф01", 5, 145.10, Instant.now())
        );

        System.out.println("------------ unsorted -----------");
        goodsWithLambda.setGoods(list);
        System.out.println("\n------------ sorted by name -----------");
        goodsWithLambda.sortByName();
        System.out.println("\n------------ sorted by vendor code -----------");
        goodsWithLambda.sortByNumber();
        System.out.println("\n------------ sorted by part vendor code -----------");
        goodsWithLambda.sortByPartNumber();
        System.out.println("\n------------ sorted by availability and vendor code -----------");
        goodsWithLambda.sortByAvailabilityAndNumber();
        System.out.println("\n------------ sorted by expired date -----------");
        goodsWithLambda.expiredAfter(Instant.now());
        System.out.println("\n------------ sorted by less count -----------");
        goodsWithLambda.сountLess(5);
        System.out.println("\n------------ sorted by less && more -----------");
        goodsWithLambda.сountBetween(1, 7);
    }

}
