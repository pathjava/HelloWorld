// Oleg Kiselev
// 13.05.2020, 20:17

package ru.progwards.java2.lessons.generics;

import java.util.ArrayList;
import java.util.List;

public class FruitBox<T extends Fruit> extends ArrayList<T> {

    private List<T> fruitList = new ArrayList<>();

    private void addFruit(List<T> list){
        fruitList.addAll(list);
    }

    private void getWeight(){

    }


    public static void main(String[] args) {
        Apple applesOne = new Apple(1.0);
        Apple applesTwo = new Apple(1.0);
        Orange orangesOne = new Orange(1.5);
        Orange orangesTwo = new Orange(1.5);

        FruitBox<Apple> fruitBoxOne = new FruitBox<>();
        FruitBox<Orange> fruitBoxTwo = new FruitBox<>();

//        fruitBoxOne.addFruit(List.of(applesOne, applesTwo, orangesOne, orangesTwo));
//        fruitBoxOne.fruitList.forEach(System.out::println);

        for (int i = 0; i < 4; i++) {
            fruitBoxOne.addFruit(List.of(applesOne, applesTwo));
        }
        for (int i = 0; i < 3; i++) {
            fruitBoxTwo.addFruit(List.of(orangesOne, orangesTwo));
        }

        fruitBoxOne.fruitList.forEach(System.out::println);
        System.out.println("-----------------");
        fruitBoxTwo.fruitList.forEach(System.out::println);
    }

}
