// Oleg Kiselev
// 13.05.2020, 20:17

package ru.progwards.java2.lessons.generics;

import java.util.ArrayList;
import java.util.List;

public class FruitBox<T extends Fruit> extends ArrayList<T> {

    private List<T> fruitList;

    private void addFruit(List<? extends Fruit> list){
        fruitList.addAll(list);
    }


    public static void main(String[] args) {
        FruitBox<Fruit> fruitBox = new FruitBox<>();

        List<Fruit> list = List.of(
                new Apple(1.0),
                new Apple(1.0),
                new Apple(1.0),
                new Orange(1.5),
                new Apple(1.0),
                new Apple(1.0),
                new Apple(1.0),
                new Apple(1.0)
        );

        fruitBox.addFruit(list);
    }

}
