package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Creator {
    /* спросить у Никиты / Валерия, как они видят правильность решения, так как тип листа не указан в задание */
    public static Collection<Integer> fillEven(int n) {
        /* 3) данное решение как последующее развитие второго решения от Intellij IDEA*/
//        return IntStream.rangeClosed(2, (n * 2)).filter(i -> i % 2 == 0).boxed().collect(Collectors.toList());

        /* 2) данное решение сгенерировано средой Intellij IDEA из решения, представленного ниже
         * через цикл for и в данном решение уже нет привязки к типу листа - ArrayList или LinkedList */
        Collection<Integer> fillList = IntStream.rangeClosed(2, (n * 2)).filter(i -> i % 2 == 0).boxed().collect(Collectors.toList());
        return fillList;

        /* 1) первоначальное решение через цикл for и ArrayList, но в условие задачи не сказано,
         * какой именно тип листа (ArrayList или LinkedList) */
//        Collection<Integer> fillList = new ArrayList<>();
//        for (int i = 2; i <= (n * 2); i++) {
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

        /* так же как и у метода fillEven выше */
//        Collection<Integer> fillList = new ArrayList<>();
//        for (int i = (n * 2); i >= 1; i--) {
//            if (i % 2 != 0) {
//                fillList.add(i);
//            }
//        }
//        return fillList;
    }

    public static Collection<Integer> fill3(int n){
        Collection<Integer> fillList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            /* добавляем элемент со значением i */
            fillList.add(i);
            int index = ((ArrayList<Integer>) fillList).lastIndexOf(i);
            ((ArrayList<Integer>) fillList).set(index, index);
            fillList.add(index*index);
            fillList.add(index*index*index);
        }
        return fillList;

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
//        System.out.println(fillEven(10));
//        System.out.println(fillOdd(8));
        System.out.println(fill3(4));
    }
}
