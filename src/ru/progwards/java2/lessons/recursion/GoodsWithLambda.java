// Oleg Kiselev
// 07.05.2020, 19:58

package ru.progwards.java2.lessons.recursion;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class GoodsWithLambda {

    private final List<Goods> listGoods = new ArrayList<>();;

    private void setGoods(List<Goods> list){
        listGoods.addAll(list);
        listGoods.forEach(System.out::println);
    }

//    private List<Goods> sortByName(){
//
//    }
//
//    private List<Goods> sortByNumber(){
//
//    }
//
//    private List<Goods> sortByPartNumber(){
//
//    }
//
//    private List<Goods> sortByAvailabilityAndNumber(){
//
//    }
//
//    private List<Goods> expiredAfter(Instant date){
//
//    }
//
//    private List<Goods> сountLess(int count){
//
//    }
//
//    private List<Goods> сountBetween(int count1, int count2){
//
//    }

    public static void main(String[] args) {
        GoodsWithLambda goodsWithLambda = new GoodsWithLambda();

        List<Goods> list = new ArrayList<>(List.of(
                new Goods("Песнь льда и пламени", "15а10", 5, 225.10, Instant.now()),
                new Goods("Записки о Гальской войне", "12р15", 3, 125.0, Instant.now().minus(1, ChronoUnit.DAYS)),
                new Goods("Шантарам", "20К18", 1, 301.25, Instant.now().plus(2, ChronoUnit.DAYS)),
                new Goods("Дни в Бирме", "32057", 7, 225.10, Instant.now().plus(15, ChronoUnit.HOURS)),
                new Goods("Проклятые короли", "15Ю61", 7, 145.10, Instant.now().plus(10, ChronoUnit.HOURS)),
                new Goods("Цыплёнок ястреб", "10ю11", 0, 85.10, Instant.now().plus(300, ChronoUnit.MINUTES)),
                new Goods("Великие Моголы", "15Ю01", 0, 205.10, Instant.now().minus(2, ChronoUnit.DAYS)),
                new Goods("Туарег", "05ф01", 5, 145.10, Instant.now())
        ));

        goodsWithLambda.setGoods(list);

    }

}
