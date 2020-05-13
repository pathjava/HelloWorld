// Oleg Kiselev
// 13.05.2020, 15:35

package ru.progwards.java2.lessons.generics;

import java.util.Arrays;
import java.util.Objects;

public class DynamicArray<T extends Comparable<? super T>> {

    private T[] arr;
    private int size;

    public DynamicArray() {
    }

    private int realSize() {
        return size = (int) Arrays.stream(arr).filter(Objects::nonNull).count();
    }

    private void add(T element) {
        if (arr.length == realSize()) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
        arr[size] = element;
    }

    private void insert(int pos, T element) {
        if (pos < 0)
            return;

        if (arr.length == realSize()) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }

        T[] tempArr = Arrays.copyOfRange(arr, pos, arr.length);

        arr[pos] = element;

        int index = 0;
        for (int i = pos + 1; i < arr.length; i++) {
            arr[i] = tempArr[index];
            index++;
        }
    }


    public static void main(String[] args) {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        dynamicArray.arr = new Integer[]{5, 1, 14, 34, 22, 3, 1, 3, 100, 17};

        for (int i = 1; i <= 10; i++) {
            dynamicArray.add(i);
        }

        for (int i = 1; i < 10; i += 2) {
            dynamicArray.insert(i + 3, i + 50);
        }

        System.out.println(Arrays.toString(dynamicArray.arr));

//        DynamicArray<String> dynamicArray1 = new DynamicArray<>();
//        dynamicArray1.arr = new String[]{"Михаил", "Алексей", "Сергей", "Игорь", "Даша", "Лена", "Bill", "Mike", "Alex"};
    }

}
