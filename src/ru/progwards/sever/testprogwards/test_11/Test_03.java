package ru.progwards.sever.testprogwards.test_11;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Test_03 {
    public static void iterator3(ListIterator<Integer> iterator) {
        // методом iterator.hasNext() проверяем, есть ли следующий элемент
        while (iterator.hasNext()) {
            // если метод выше определил наличие элемента
            // методом iterator.next() получаем значение элемента и помещаем в temp
            int temp = iterator.next();
            // значение, помещенное в temp делил по модулю % и если остаток равен 0
            if (temp % 3 == 0) {
                // методом iterator.nextIndex() - 1 получаем индекс элемента
                // и методом iterator.set устанавливаем индекс как значение элемента
                iterator.set(iterator.nextIndex() - 1);
            }
        }
    }


    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for (Integer e : new Integer[]{40, 300, 0, 4, 3, 1, 2}) list.add(e);
        System.out.println(list);
        iterator3(list.listIterator());
        System.out.println(list);
    }
}

//    List<Integer> linkedList = new LinkedList();
//        for (int i = 0; i < 5; i++)
//        linkedList.add(i);
//
//        System.out.println(linkedList);
//
//        for (ListIterator<Integer> listIterator = linkedList.listIterator(); listIterator.hasNext(); ) {
//        Integer n = listIterator.next();
//        if (n % 2 != 0)
//        listIterator.remove();
//        else
//        listIterator.add(n * 2);
//
//        System.out.println(linkedList);
//        }
//        System.out.println(linkedList);
//
//        for (ListIterator<Integer> listIterator = linkedList.listIterator(); listIterator.hasNext(); ) {
//        Integer n = listIterator.next();
//        if (n % 2 != 0)
//        listIterator.set(n * 2);
//        }
//        System.out.println(linkedList);