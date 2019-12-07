package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class DIntArray {
    //private int[] a = {};
    // тестовый массив
    private int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    DIntArray(){
    }

    public void add(int num){
        int[] b = new int[a.length + 1];
        //копируем массив от 0, в конце остается +1 ячейка
        System.arraycopy(a, 0, b, 0, a.length);
        //помещаем num в конец массива (присваивваем ячейке)
        b[a.length] = num;
        //присваиваем b в a
        a = b;
    }

    public void atInsert(int pos, int num){
        int[] b = new int[a.length + 1];
        //копируем массив от 0 до значения позиции
        System.arraycopy(a, 0, b, 0, pos);
        //копируем массив от значения позиции +1 до конца массива
        System.arraycopy(a, pos, b, pos + 1, a.length - pos);
        //помещааем num в pos (присваивваем ячейке)
        b[pos] = num;
        //присваиваем b в a
        a = b;
    }

    public void atDelete(int pos){
        int[] b = new int[a.length - 1];
        //копируем массив от 0 до pos
        System.arraycopy(a, 0, b, 0, pos);
        //копируем массив от pos +1 в индексе до конца, уменьшая длинну массива на -1
        System.arraycopy(a, pos + 1, b, pos, a.length - pos - 1);
        //присваиваем b в a
        a = b;
    }

    public int at(int pos){
        return a[pos];
    }

    public static void main(String[] args) {
        DIntArray dIntArray = new DIntArray();
        // как сделать вызов массива для теста из main так и не понял
        //int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        dIntArray.add(12);
        System.out.println(Arrays.toString(dIntArray.a));
        dIntArray.atInsert(5, 15);
        System.out.println(Arrays.toString(dIntArray.a));
        dIntArray.atDelete(7);
        System.out.println(Arrays.toString(dIntArray.a));
        System.out.println("Значение в массиве под индексом " + dIntArray.at(2-1) + ": " + dIntArray.at(2));
    }
}
