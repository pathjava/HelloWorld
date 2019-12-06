package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class DIntArray {
    private int[] a;

    DIntArray(){
    }

    public void add(int num){
        /* public void add(int num) - добавляет элемент num в конец массива, при этом размер массива должен увеличиться на 1.
        Для этого нужно будет разместить новый массив нужного размера, скопировать в него старый, и добавить в хвост элемент num. */

        int len = a.length;
        int[] a = new int[len + 1];
        a = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = num;

        // ранее написанные варианты решеения
//        int[] a = new int[] {num};
//        a = Arrays.copyOf(a, a.length + 1);
//        a[a.length - 1] = num;

//        int[] b = new int[a.length + 1];
//        System.arraycopy(a, 0, b, 0, a.length);
//        a = b;
    }

    public void atInsert(int pos, int num){
        /* public void atInsert(int pos, int num) - добавляет элемент num в позицию pos массива, при этом размер массива должен увеличиться на 1.
        Для этого нужно будет разместить новый массив нужного размера, скопировать в него старый,
        c учетом того, что новый элемент окажется где-то в середине, и потом положить в нужный индекс элемент num. */

        int len = a.length;
        int[] a = new int[len + 1];
        a = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = num;
    }

    public void atDelete(int pos){
        /* public void atDelete(int pos) - удаляет элемент в позиции pos массива, при этом размер массива должен уменьшиться на 1.
        Для этого нужно будет разместить новый массив нужного размера, скопировать в него старый, уже без элемента, который был в позиции pos. */

        // то же не понимаю как это реализовать, про удаление ячейки на лекции не помню информации. было про .set, но это перезапись значения
    }

    public int at(int pos){
        return pos;
    }

    public static void main(String[] args) {
        DIntArray dIntArray = new DIntArray();
        // как запустить отладку?
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        dIntArray.add(11);
        System.out.println(Arrays.toString(arr));
    }
}
