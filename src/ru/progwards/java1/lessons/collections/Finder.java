package ru.progwards.java1.lessons.collections;

import java.util.*;

public class Finder {
    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers){
        /* преобразуем входящие значения из numbers в массив */
        Object[] arr = numbers.toArray();
        /* заводим и инициализируем переменные */
        int id1 = 0;
        int id2 = 0;
        /* переменную min инициализируем суммой значений под индексами 0 и 1 */
        int min = ((int) arr[0] + (int)arr[1]);
        for (int i = 0; i < arr.length - 1; i++) {
            /* сравниваем сумму двух соседних значений с суммой переменной min */
            if (((int)arr[i] + (int)arr[i + 1]) < min) {
                /* если сумма двух значений меньше min, то переменной min присваиваем новое минимальное значение*/
                min = ((int) arr[i] + (int) arr[i + 1]);
                /* переменной id1 присваиваем индекс первого значения из минимальной суммы */
                id1 = i;
                /* переменной id2 присваиваем индекс второго значения из минимальной суммы */
                id2 = i + 1;
            } /* если значение двух пар, например при сложении -76,-75,-76 одинаковое, то берем индексы последних двух значений*/
            else if (((int)arr[i] + (int)arr[i + 1]) == min) {
                min = ((int) arr[i] + (int) arr[i + 1]);
                id1 = i;
                id2 = i + 1;
            }
        }
        /* создаем лист ArrayList */
        Collection<Integer> list = new ArrayList<>();
        /* помещаем в лист значение индекса переменных id1 и id2 */
        list.add(id1);
        list.add(id2);

        return list;
    }

    public static Collection<Integer> findLocalMax(Collection<Integer> numbers){
        return numbers;
    }

    public static boolean findSequence(Collection<Integer> numbers){
        return true;
    }

    public static String findSimilar(Collection<String> names){

        return String.valueOf(names);
    }


    public static void main(String[] args) {
        List<Integer> test = List.of(98,11,-14,-2,-47,-35,63,92,13,89,37,2,77,24,-45,89,-76,-75,-76);
        System.out.println(findMinSumPair(test));
    }
}
