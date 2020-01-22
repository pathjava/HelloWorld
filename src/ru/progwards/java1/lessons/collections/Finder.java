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
        /* создаем лист ArrayList */
        Collection<Integer> list = new ArrayList<>();
        /* преобразуем входящие значения из numbers в массив */
        Object[] arr = numbers.toArray();

        for (int i = 1; i < arr.length-1; i++) {
            /* проверяет, если значение под индексом i больше значений i-1 и i+1,
            * тогда значение arr[i] помещаем в list*/
            if (((int)arr[i] > (int)arr[i-1]) && ((int)arr[i] > (int)arr[i+1])){
                list.add((Integer) arr[i]);
            }
        }
        return list;
    }

    public static boolean findSequence(Collection<Integer> numbers){
        /* преобразуем входящие значения из numbers в массив */
        Object[] arr = numbers.toArray();
        /* создаем и инициализируем переменную */
        boolean result = true;
        /* создаем и инициализируем переменную и устанавливаем значение по умолчанию 1 */
        int value = 1;
        int i = 0;
        while (i < arr.length) {
            /* крутимся в цикле и проверяем все значения arr[i] на равенство со значением value */
            if ((int)arr[i] == value) {
                /* если условие if равно, то переменной result присваиваем true,
                * а значение переменной value конкатенируем на +1 */
                result = true;
                value++;
                /* значение i сбрасываем в 0 */
                i = -1;
            } else result = false;
            i++;
        }
        return result;
    }

    public static String findSimilar(Collection<String> names){
        Object[] arr = names.toArray();

        int maxCounter = 0;
        String element = "";
        for (int i = 0; i <arr.length ; i++) {
            int counter =1;
            for (int j = i+1; j <arr.length ; j++) {
                if(arr[i] == arr[j]){
                    counter++;
                }
            }
            if(maxCounter < counter){
                maxCounter = counter;
                element = (String) arr[i];
            }
        }
        return element + ":" + maxCounter;
    }


    public static void main(String[] args) {
        List<Integer> test = List.of(98,11,-14,-2,-47,-35,63,92,13,89,37,2,77,24,-45,89,-76,-75,-76);
        System.out.println(findMinSumPair(test));

        List<Integer> test2 = List.of(7,87,60,-74,28,10,33,-42,-25);
        System.out.println(findLocalMax(test2));

        List<Integer> test3 = List.of(8,2,2,1,10,9,10,5,1,4,8,10);
        System.out.println(findSequence(test3));

        List<String> test4 = List.of("Григорий","Борис","Дмитрий","Борис","Григорий","Борис","Александр");
        System.out.println(findSimilar(test4));
    }
}
