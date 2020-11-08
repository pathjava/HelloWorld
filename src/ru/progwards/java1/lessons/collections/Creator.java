package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Creator {
    public static Collection<Integer> fillEven(int n) {
        /* 3) данное решение как последующее развитие второго решения от Intellij IDEA*/
//        return IntStream.rangeClosed(2, (n * 2)).filter(i -> i % 2 == 0).boxed().collect(Collectors.toList());

        /* 2) данное решение сгенерировано средой Intellij IDEA из решения, представленного ниже
         * через цикл for и в данном решение уже нет привязки к типу листа - ArrayList или LinkedList */
        Collection<Integer> fillList = IntStream.rangeClosed(2, (n * 2)).filter(i -> i % 2 == 0).boxed().collect(Collectors.toList());
        return fillList;

        /* 1) первоначальное решение через цикл for и ArrayList, но в условие задачи не сказано,
         * какой именно тип листа (ArrayList или LinkedList) */
        /* создаем ArrayList типа Integer */
//        Collection<Integer> fillList = new ArrayList<>();
        /* запускаем цикл, начиная от 2, где в условие i <= (n * 2) n умножаем на 2,
         * так как количество проходов и соответственно добавленных элементов
         * регламентируется входящим значением int n, а так как при выявлении четных чисел через (i % 2 == 0)
         * количество добавленных символов будет в два раза меньше, то значение int n увеличиваем вдвое */
//        for (int i = 2; i <= (n * 2); i++) {
        /* проверяем, является ли значение четным и если да, то добавляем его в лист */
//            if (i % 2 == 0){
//                fillList.add(i);
//            }
//        }
//        return fillList;
    }

    public static Collection<Integer> fillOdd(int n) {
        /* так же как и у метода fillEven выше */
//        return IntStream.iterate((n * 2), i -> i >= 1, i -> i - 1).filter(i -> i % 2 != 0).boxed().collect(Collectors.toList());

        /* так же как и у метода fillEven выше */
        Collection<Integer> fillList = IntStream.iterate((n * 2), i -> i >= 1, i -> i - 1).filter(i -> i % 2 != 0).boxed().collect(Collectors.toList());
        return fillList;

        /* так же как и у метода fillEven выше за некоторомы исключениями */
//        Collection<Integer> fillList = new ArrayList<>();
//        for (int i = (n * 2); i >= 1; i--) {
//            if (i % 2 != 0) {
//                fillList.add(i);
//            }
//        }
//        return fillList;
    }

    public static Collection<Integer> fill3(int n) {
        Collection<Integer> fillList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            /* добавляем элемент со значением i */
            fillList.add(i);
            /* получаем значение индекса последнего добавленного в лист элемента и присваиваем переменной index */
            int index = ((ArrayList<Integer>) fillList).lastIndexOf(i);
            /* устанавливаем значение индекса вместо значения последнего элемента*/
            ((ArrayList<Integer>) fillList).set(index, index);
            /* добавляем новый следующий элемент в лист со значением index в квадрате */
            fillList.add(index * index);
            /* добавляем новый следующий элемент в лист со значением index в кубе */
            fillList.add(index * index * index);
        }
        return fillList;

        /* решение через интерфейс List */
//        List<Integer> fillList = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            fillList.add(i);
//            int index = fillList.lastIndexOf(i);
//            fillList.set(index, index);
//            fillList.add(index*index);
//            fillList.add(index*index*index);
//        }
//        return fillList;
    }


    public static void main(String[] args) {
        System.out.println(fillEven(10));
        System.out.println(fillOdd(8));
        System.out.println(fill3(4));
    }
}
