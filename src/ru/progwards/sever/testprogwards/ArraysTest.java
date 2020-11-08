// Oleg Kiselev
// 06.05.2020, 14:50

package ru.progwards.sever.testprogwards;

import java.util.Arrays;

public class ArraysTest {
    // поиск минимального и максимального значения в массиве
    int[] nums = {45, 21, -14, 1204, 587, -312, 1, -2, 345, 0};
    int min = nums[0];
    int max = nums[0];

    public void minMaxArray() {
        for (int i = 1; i < 10; i++) {
            if (nums[i] < min) min = nums[i];
            if (nums[i] > max) max = nums[i];
        }
        System.out.println("Min value: " + min);
        System.out.println("Max value: " + max);
        System.out.println();


    }

    // заполнение многомерного массива
    public void arrayFilling() {
        System.out.println();
        System.out.println("Заполнение двумерного массива:");
        int[][] table = new int[4][5];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = (i * (table.length + 1)) + j + 1;
                if (table[i][j] >= 1 && table[i][j] <= 9 && table[i][j] != table[i].length) {
                    System.out.print(table[i][j] + "  ");
                }
//                else if (table[i][j] >= 10 && table[i][j] == table[i].length ) {
//                    System.out.print(table[i][j] + "");
//                }
                else
                    System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // пузырьковая сортировка int
    int[] arr = {45, 21, -14, 1204, 587, -312, 1, -2, 345, 0};

    public void bubbleSort() {
        System.out.println("Исходный массив:");
        for (int item : arr) System.out.print(item + " ");
        System.out.println();

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println();
        System.out.println("Отсортированный массив:");
        for (int value : arr) System.out.print(value + " ");
        System.out.println();
    }

    // пузырьковая сортировка String
    String[] arrStr = {"bear", "a", "one", "tiger", "zero", "elephant", "airplane", "car", "football", "entertainment"};

    public void bubbleSortString() {
        System.out.println("Исходный массив String:");
        for (String item : arrStr) System.out.print(item + " ");
        System.out.println();

//        int n = arrStr.length;
        for (int i = 0; i < arrStr.length - 1; i++) {
            for (int j = 0; j < arrStr.length - 1; j++) {
                if (arrStr[j].compareTo(arrStr[j + 1]) > 0) {
                    String temp = arrStr[j];
                    arrStr[j] = arrStr[j + 1];
                    arrStr[j + 1] = temp;
                }
            }
        }
        System.out.println();
        System.out.println("Отсортированный массив String:");
        for (String value : arrStr) System.out.print(value + " ");
        System.out.println();
    }

    // быстрая сортировка
    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;//завершить выполнение если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = array[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }
            while (array[j] > opora) {
                j--;
            }
            if (i <= j) {//меняем местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }

    public static void main(String[] args) {
        ArraysTest minMax = new ArraysTest();
        minMax.minMaxArray();
        ArraysTest bubble = new ArraysTest();
        bubble.bubbleSort();
        ArraysTest bubbleString = new ArraysTest();
        bubbleString.bubbleSortString();
        ArraysTest arrFill = new ArraysTest();
        arrFill.arrayFilling();

        int[] x = {8, 0, 4, 7, 3, 7, 10, 12, -3};
        System.out.println("Исходный массив:");
        System.out.println(Arrays.toString(x));

        int low = 0;
        int high = x.length - 1;

        quickSort(x, low, high);
        System.out.println("Отсортированный массив:");
        System.out.println(Arrays.toString(x));
    }
}

