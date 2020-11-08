package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T> {
    /* заводим массив array, где вместо T (забыл как правильно назвать) может подаваться любой тип (String, int и т.д.) */
    private T[][] array;
    /* заводим переменные, которые будут использоваться для проверки текущего местоположения итератора */
    private int indexRow;
    private int indexCol;

    /* конструктор */
    MatrixIterator(T[][] array) {
        this.array = array;
    }

    /* переопределяем метод hasNext */
    @Override
    public boolean hasNext() {
        /* если текущий indexRow и indexCol меньше длины массива, то следующая ячейка массива существует и метод возвращает true,
         * если индекс == длине массива, то шаг на следующую ячейку невозможен и метод вернет false */
        return indexRow < array.length && indexCol < array[indexRow].length;
    }

    @Override
    public T next() {
        /* переменной nextValue типа Т присваиваем 1 */
        T nextValue = array[indexRow][indexCol++];
        /* проверяем, если indexCol больше или равно длине массива array[indexRow].length, то есть, проверяемой строке матрицы двумерного массива */
        if (indexCol >= array[indexRow].length) {
            /* тогда indexRow итерируем на +1 и устанавливаем indexCol в начальную позицию (индекс 0) строки ячеек массива*/
            indexRow++;
            indexCol = 0;
        }
        return nextValue;
    }

    public static void main(String[] args) {
        MatrixIterator<Integer> iterator =
                new MatrixIterator<>(new Integer[][]{{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, {11, 12, 13, 14, 15, 16, 17, 18, 19, 20}});
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
