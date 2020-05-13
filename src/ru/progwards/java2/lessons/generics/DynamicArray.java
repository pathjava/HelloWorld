// Oleg Kiselev
// 13.05.2020, 15:35

package ru.progwards.java2.lessons.generics;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class DynamicArray<T extends Comparable<? super T>> {

    private T[] arr;
    private int size;

    public DynamicArray() {
    }

    private void add(T element){
        size = (int) Arrays.stream(arr).filter(Objects::nonNull).count();

        if (arr.length == size) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
        arr[size] = element;
    }


    public static void main(String[] args) {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        dynamicArray.arr = new Integer[]{5, 1, 14, 34, 22, 3, 1, 3, 100, 17};

        for (int i = 1; i <= 10; i++) {
            dynamicArray.add(i);
        }

        System.out.println(Arrays.toString(dynamicArray.arr));

//        DynamicArray<String> dynamicArray1 = new DynamicArray<>();
//        dynamicArray1.arr = new String[]{"Михаил", "Алексей", "Сергей", "Игорь", "Даша", "Лена", "Bill", "Mike", "Alex"};
    }

}
