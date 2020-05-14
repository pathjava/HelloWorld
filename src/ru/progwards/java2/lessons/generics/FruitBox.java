// Oleg Kiselev
// 13.05.2020, 20:17

package ru.progwards.java2.lessons.generics;

import java.util.ArrayList;
import java.util.List;

public class FruitBox<T extends Fruit> extends ArrayList<T> {

    private final List<T> fruitList = new ArrayList<>();

    private void addFruit(List<T> list) {
        fruitList.addAll(list);
    }

    private double getWeight() {
        return fruitList.stream().mapToDouble(Fruit::getWeight).sum();
    }

    private void moveTo(FruitBox<T> box) {
        if (box.isEmpty() || fruitList.isEmpty())
            return;

        try {
            box.fruitList.addAll(fruitList);
        } catch (Exception e) {
            throw new UnsupportedOperationException();
        }
        fruitList.clear();
    }


    public static void main(String[] args) {
        Apple applesOne = new Apple(1.0);
        Apple applesTwo = new Apple(1.0);
        Orange orangesOne = new Orange(1.5);
        Orange orangesTwo = new Orange(1.5);


        FruitBox<Apple> fruitBoxOne = new FruitBox<>();
        for (int i = 0; i < 3; i++)
            fruitBoxOne.addFruit(List.of(applesOne, applesTwo));

        FruitBox<Apple> fruitBoxThree = new FruitBox<>();
        for (int i = 0; i < 2; i++)
            fruitBoxThree.addFruit(List.of(applesOne, applesTwo));

        FruitBox<Orange> fruitBoxTwo = new FruitBox<>();
        for (int i = 0; i < 3; i++)
            fruitBoxTwo.addFruit(List.of(orangesOne, orangesTwo));

        FruitBox<Orange> fruitBoxFour = new FruitBox<>();
        for (int i = 0; i < 2; i++)
            fruitBoxFour.addFruit(List.of(orangesOne, orangesTwo));

        fruitBoxOne.moveTo(fruitBoxThree);
        fruitBoxOne.fruitList.forEach(System.out::println);
        System.out.println("-----------------");

        fruitBoxOne.fruitList.forEach(System.out::println);
        System.out.println(fruitBoxOne.getWeight());
        System.out.println("-----------------");
        fruitBoxThree.fruitList.forEach(System.out::println);
        System.out.println(fruitBoxThree.getWeight());
        System.out.println("-----------------");
        fruitBoxTwo.fruitList.forEach(System.out::println);
        System.out.println(fruitBoxTwo.getWeight());
        System.out.println("-----------------");
        fruitBoxFour.fruitList.forEach(System.out::println);
        System.out.println(fruitBoxFour.getWeight());
        System.out.println("-----------------");


    }

}
