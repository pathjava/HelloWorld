package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {
    /* заводим массив array, где вместо T (забыл как правильно назвать) может подаваться любой тип (String, int и т.д.) */
    private T[] array;
    /* заводим переменную index, которая далее будет использоваться для проверки текущего местоположения итератора */
    private int index;

    /* конструктор */
    ArrayIterator(T[] array) {
        this.array = array;
    }

    /* переопределяем метод hasNext */
    @Override
    public boolean hasNext() {
        /* если текущий index меньше длины массива, то следующая ячейка массива существует и метод возвращает true,
         * если index == длине массива, то шаг на следующую ячейку невозможен и метод вернет false */
        return index < array.length;
    }

    /* переопределяем метод next */
    @Override
    public T next() {
        /* реализация через тернарный оператор. если this.hasNext() является true, то есть дальнейший шаг по массиву возможен,
         * тогда инкременируем индекс на +1 (array[index++]) и получаем значение следующей ячейки,
         * если this.hasNext() является false, это означает конец массива, следующая ячейка массива недоступна и возвращаем null */
        return this.hasNext() ? array[index++] : null;
    }

    public static void main(String[] args) {
        ArrayIterator<Integer> iterator = new ArrayIterator<>(new Integer[]{5, 2, 7, 4, 10, 55, 10, 1, 12, 100});
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}