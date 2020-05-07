// Oleg Kiselev
// 06.05.2020, 14:59

package ru.progwards.sever.testprogwards2.test_02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortLambda{

    // решение, предложенное IDE на основе моего - ниже вариант
    void sortAndPrint(List<Person> list){
        list.sort(Comparator.comparingInt(i -> i.age));
        list.forEach(System.out::println);
    }

    // решение, написанное мной
//    void sortAndPrint(List<Person> list){
//        list.sort((i1, i2) -> Integer.compare(i1.age, i2.age));
//        list.forEach(System.out::println);
//    }

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>(List.of(
                new Person("Михаил", 15),
                new Person("Иван", 12),
                new Person("Сергей", 25),
                new Person("Игорь", 32),
                new Person("Алексей", 28)
        ));

        SortLambda sortLambda = new SortLambda();
        sortLambda.sortAndPrint(list);
    }
}


class Person {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return name + " " + age;
    }
}