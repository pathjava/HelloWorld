// Oleg Kiselev
// 07.05.2020, 12:52

package ru.progwards.sever.testprogwards2.test_02;

import java.util.List;

public class FastSearchInSortedList {
    static Integer fastSearch(List<Integer> list, int firstIdx, int lastIdx, int elem2Search) {
        if (lastIdx >= firstIdx) {
            int half = firstIdx + (lastIdx - firstIdx) / 2;

            // условие выхода:
            // если средний элемент совпадает с искомым, вернуть его индекс
            if (list.get(half) == elem2Search)
                return half;

            // если средний элемент больше искомого, ищем в слева от half
            if (list.get(half) > elem2Search)
                return fastSearch(list, firstIdx, half - 1, elem2Search);

            // иначе, ищем в справа от half
            return fastSearch(list, half + 1, lastIdx, elem2Search);
        }

        return null; // выход из рекурсии, если элемент не найден
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233);

        System.out.println(fastSearch(list, 0, list.size() - 1, 89));
    }
}
