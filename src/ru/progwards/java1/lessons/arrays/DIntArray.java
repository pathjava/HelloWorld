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
        System.arraycopy(a, 0, b, 0, a.length);
        b[a.length] = num;
        a = b;
    }

    public void atInsert(int pos, int num){
        int[] b = new int[a.length + 1];
        System.arraycopy(a, 0, b, 0, pos);
        System.arraycopy(a, pos, b, pos + 1, a.length - pos);
        b[pos] = num;
        a = b;
    }

    public void atDelete(int pos){
        int[] b = new int[a.length - 1];
        System.arraycopy(a, 0, b, 0, pos);
        System.arraycopy(a, pos + 1, b, pos, a.length - pos - 1);
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
    }
}
