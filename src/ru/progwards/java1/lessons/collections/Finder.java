package ru.progwards.java1.lessons.collections;

import java.util.*;

public class Finder {
    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers) {
        /* преобразуем входящие значения из numbers в массив */
        Object[] arr = numbers.toArray();
        /* заводим и инициализируем переменные */
        int id1 = 0;
        int id2 = 0;
        /* переменную min инициализируем суммой значений под индексами 0 и 1 */
        int min = ((int) arr[0] + (int) arr[1]);
        for (int i = 0; i < arr.length - 1; i++) {
            /* сравниваем сумму двух соседних значений с суммой переменной min */
            if (((int) arr[i] + (int) arr[i + 1]) < min) {
                /* если сумма двух значений меньше min, то переменной min присваиваем новое минимальное значение*/
                min = ((int) arr[i] + (int) arr[i + 1]);
                /* переменной id1 присваиваем индекс первого значения из минимальной суммы */
                id1 = i;
                /* переменной id2 присваиваем индекс второго значения из минимальной суммы */
                id2 = i + 1;
            } /* если значение двух пар, например при сложении -76,-75,-76 одинаковое, то берем индексы первых двух значений*/ else if (((int) arr[i] + (int) arr[i + 1]) == min) {
                id1 = i - 1;
                id2 = i;
            }
        }
        /* создаем лист ArrayList */
        Collection<Integer> list = new ArrayList<>();
        /* помещаем в лист значение индекса переменных id1 и id2 */
        list.add(id1);
        list.add(id2);

        return list;
    }

    public static Collection<Integer> findLocalMax(Collection<Integer> numbers) {
        /* создаем лист ArrayList */
        Collection<Integer> list = new ArrayList<>();
        /* преобразуем входящие значения из numbers в массив */
        Object[] arr = numbers.toArray();

        for (int i = 1; i < arr.length - 1; i++) {
            /* проверяет, если значение под индексом i больше значений i-1 и i+1,
             * тогда значение arr[i] помещаем в list*/
            if (((int) arr[i] > (int) arr[i - 1]) && ((int) arr[i] > (int) arr[i + 1])) {
                list.add((Integer) arr[i]);
            }
        }
        return list;
    }

    public static boolean findSequence(Collection<Integer> numbers) {
        /* преобразуем входящие значения из numbers в массив */
        Object[] arr = numbers.toArray();
        /* создаем и инициализируем переменную */
        boolean result = true;
        /* создаем и инициализируем переменную и устанавливаем значение по умолчанию 1 */
        int value = 1;
        int i = 0;
        /* проверяем в value <= numbers.size(), чтобы значение value было меньше или равно значению numbers.size(),
         * чтобы value не вышло за пределы значения numbers.size() */
        while (i < numbers.size() && value <= numbers.size()) {
            /* крутимся в цикле и проверяем все значения arr[i] на равенство со значением value */
            if ((int) arr[i] == value) {
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

    /* данную задачу можно сделать через цикл for-each без перевода names в массив,
     * но изначально сделал как реализовано ниже, хотя перевести в for-each дело пары кликов,
     * но тогда надо переписывать комментарии к коду */
    public static String findSimilar(Collection<String> names) {
        /* преобразуем входящие значения из names в массив */
        Object[] arr = names.toArray();
        /* заводим переменную lastElement для хранения последнего проверенного имени */
        String lastElement = "";
        /* заводим переменную для хранения последнего максимального количества встретившихся подряд имен (по умолчанию 1) */
        int lastMaxCounter = 1;
        /* заводим переменную для хранения последнего имени,
         * встретившегося максимальное количество раз подряд (по умочанию имя под индексом 0) */
        String lastCountElement = (String) arr[0];
        /* заводим переменную, считающую количество встретившихся подряд имен (по умолчанию 1) */
        int counter = 1;
        for (int i = 0; i < arr.length; i++) {
            /* сравниваем lastElement и arr[i] и если равно, то инкрементируем переменную counter на +1 */
            if (lastElement.equals(arr[i])) {
                counter++;
            } else {
                /* если условие выше не равно, то переменной lastElement приваиваем arr[i] */
                lastElement = (String) arr[i];
                /* а счетчик сбрасываем до 1 */
                counter = 1;
            }
            /* если counter больше lastMaxCounter (которая по умолчанию 1) */
            if (lastMaxCounter < counter) {
                /* тогда переменной lastMaxCounter присваиваем значение переменной counter */
                lastMaxCounter = counter;
                /* а переменной lastCountElement присваиваем последнее наиболее повторяющееся подряд имя из arr[i] */
                lastCountElement = (String) arr[i];
            }
        }
        return lastCountElement + ":" + lastMaxCounter;
    }

    /* данное решение было реализовано, так как в условие задачи не было сказано, что совпадения должны идти подряд
     * это решение ищет и считает все вхождения определенного слова (а не только расположенные подряд)
     * и определяет наиболее часто встречающееся */
    public static String findSimilar2(Collection<String> names) {
        /* преобразуем входящие значения из names в массив */
        Object[] arr = names.toArray();
        /* заводим счетчик последнего, наиболее часто встретившегося в массиве имени (по умолчанию 1) */
        int lastCounter = 1;
        /* заводим переменную, хранящую последнее, наиболее часто встречающееся имя (по умочанию имя под индексом 0)
         * имя по умолчанию для того, если все имена в массиве встречаются только один раз, то выведется самое первое */
        String element = (String) arr[0];
        for (int i = 0; i < arr.length; i++) {
            /* заводим и инициализируем счетчик сколько раз встретилось проверяемое имя */
            int counter = 1;
            /* заводим внутренний цикл, в котором имя arr[i] поочереди проверяем на совпадение со всеми именами в массиве */
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    /* при каждом совпадении счетчик инкременируем на +1 */
                    counter++;
                }
            }
            /* проверяем, если значение счетчика counter больше значения lastCounter (которая по умолчанию 1) */
            if (lastCounter < counter) {
                /* тогда переменной lastCounter присваиваем значение счетчика counter */
                lastCounter = counter;
                /* а переменной element присваиваем имя, наиболее часто встретившееся при обходе в цикле */
                element = (String) arr[i];
            }
        }
        return element + ":" + lastCounter;
    }


    public static void main(String[] args) {
//        List<Integer> test = List.of(98,11,-14,-2,-47,-35,63,92,13,89,37,2,77,24,-45,89,-76,-75,-76);
        List<Integer> test = List.of(-3, -73, -3, 6, -40, 19, 10, -40);
        System.out.println(findMinSumPair(test));
//
//        List<Integer> test2 = List.of(7,87,60,-74,28,10,33,-42,-25);
//        System.out.println(findLocalMax(test2));

//        List<Integer> test3 = List.of(8,2,2,1,10,9,10,5,1,4,8,10);
//        List<Integer> test3 = List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18);
//        System.out.println(findSequence(test3));

//        List<String> test4 = List.of("Григорий","Борис","Дмитрий","Борис","Григорий","Борис","Александр");
//        List<String> test4 = List.of("Дмитрий","Борис","Борис","Борис","Дмитрий","Борис","Дмитрий","Григорий","Борис",
//                "Александр","Григорий","Дмитрий","Василий","Борис","Дмитрий","Борис");
//        List<String> test4 = List.of("Александр","Борис","Борис","Александр","Александр","Василий",
//                "Дмитрий","Дмитрий","Александр","Василий","Григорий");
//        List<String> test4 = List.of("Александр","Борис","Борис","Александр","Александр","Василий");
//        System.out.println(findSimilar(test4));
    }
}
