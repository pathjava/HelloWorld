package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Creator {
    /* спросить у Никиты / Валерия, как они видят правильность решения, так как тип листа не указан в задание */
    public static Collection<Integer> fillEven(int n) {
        /* 3) данное решение как последующее развитие второго решения от Intellij IDEA*/
//        return IntStream.range(2, n).filter(i -> i % 2 == 0).boxed().collect(Collectors.toList());

        /* 2) данное решение сгенерировано средой Intellij IDEA из решения, представленного ниже
         * через цикл for и в данном решение уже нет привязки к типу листа - ArrayList или LinkedList */
        List<Integer> fillList = IntStream.range(2, n).filter(i -> i % 2 == 0).boxed().collect(Collectors.toList());
        return fillList;

        /* 1) первоначальное решение через цикл for и ArrayList, но в условие задачи не сказано,
         * какой именно тип листа (ArrayList или LinkedList) */
//        List<Integer> fillList = new ArrayList<>();
//        for (int i = 2; i < n; i++) {
//            if (i % 2 == 0){
//                fillList.add(i);
//            }
//        }
//        return fillList;
    }

    public static Collection<Integer> fillOdd(int n) {
        /* так же как и у метода fillEven выше */
//        return IntStream.iterate(n, i -> i >= 1, i -> i - 1).filter(i -> i % 2 != 0).boxed().collect(Collectors.toList());

        /* так же как и у метода fillEven выше */
        List<Integer> fillList = IntStream.iterate(n, i -> i >= 1, i -> i - 1).filter(i -> i % 2 != 0).boxed().collect(Collectors.toList());
        return fillList;

        /* так же как и у метода fillEven выше */
//        List<Integer> fillList = new ArrayList<>();
//        for (int i = n; i >= 1; i--) {
//            if (i % 2 != 0) {
//                fillList.add(i);
//            }
//        }
//        return fillList;
    }

    public static Collection<Integer> fill3(int n){
        List<Integer> fillList = new ArrayList<>();
        for (int i = 2; i < (n * 3); i*=2) {
            fillList.add(i);
//            fillList.set(i-1, fillList.indexOf(i) * fillList.indexOf(i));
            int index = fillList.indexOf(i);
//            int index2 = (index * index * index);
            int index2 = (int) Math.pow(index, 3);
            fillList.set(index, index2);
        }
        return fillList;
    }


    public static void main(String[] args) {
        System.out.println(fillEven(100));
        System.out.println(fillOdd(100));
        System.out.println(fill3(100));
    }
}
