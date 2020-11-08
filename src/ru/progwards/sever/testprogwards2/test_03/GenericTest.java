// Oleg Kiselev
// 14.05.2020, 12:05

package ru.progwards.sever.testprogwards2.test_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericTest {

    enum CompareResult {LESS, EQUAL, GREATER}

    public static <T extends Comparable<? super T>> CompareResult compare(T one, T two) {
        int result = one.compareTo(two);
        if (result == 0)
            return CompareResult.EQUAL;
        else if (result > 0)
            return CompareResult.GREATER;
        else
            return CompareResult.LESS;
    }

    public static <T> void swap(List<T> list, int a, int b) {
        T temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }

//    @SuppressWarnings("unchecked")
//    public static <T> ArrayList<T> from(T... arr) {
//        return new ArrayList<>(Arrays.asList(arr));
//    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> from(T... arr) {
        ArrayList<T> list = new ArrayList<>();
        for (T t : arr) {
            list.add(t);
        }
        return list;
    }


    public static void main(String[] args) {

        Integer[] fromI = {1, 2, 3, 4, 5};
        System.out.println(from(fromI));

        String[] fromS = {"Red", "Blue", "Green"};
        System.out.println(from(fromS));

        //-------------------------

        Integer[] swapI = {1, 2, 3, 4, 5};
        List<Integer> li = from(swapI);
        swap(li, 1, 3);
        System.out.println(li);

        String[] swapS = {"Red", "Blue", "Green"};
        List<String> ls = from(swapS);
        swap(ls, 0, 2);
        System.out.println(ls);

        //-------------------------

        System.out.println(compare(1, 2));
        System.out.println(compare("Петя", "Вася"));
        System.out.println(compare(CompareResult.GREATER, CompareResult.GREATER));
    }

}
