package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class DIntArray {
    private int[] a;
    public int size = 0;

    DIntArray(){
    }

    public void add(int num){
        /* public void add(int num) - добавляет элемент num в конец массива, при этом размер массива должен увеличиться на 1.
        Для этого нужно будет разместить новый массив нужного размера, скопировать в него старый, и добавить в хвост элемент num. */

        // вот понимаю, что надо проверить длину массива, после этого создать новый +1, через Arrays.copyOf() скопировать в него старый
        // массив, но как добавить в конец элемент не понимаю.
        int[] b = new int[num];
        System.arraycopy(a, 0, b, 0, a.length + 1);
        //a = Arrays.copyOf(a, size + 1);
        //a[size] = num;
        //size++;
    }

    public void atInsert(int pos, int num){
        /* public void atInsert(int pos, int num) - добавляет элемент num в позицию pos массива, при этом размер массива должен увеличиться на 1.
        Для этого нужно будет разместить новый массив нужного размера, скопировать в него старый,
        c учетом того, что новый элемент окажется где-то в середине, и потом положить в нужный индекс элемент num. */

        //здесь вообще не понимаю как реализовать даже в мыслях
    }

    public void atDelete(int pos){
        /* public void atDelete(int pos) - удаляет элемент в позиции pos массива, при этом размер массива должен уменьшиться на 1.
        Для этого нужно будет разместить новый массив нужного размера, скопировать в него старый, уже без элемента, который был в позиции pos. */

        // то же не понимаю как это реализовать, про удаление ячейки на лекции не помню информации. было про .set, но это перезапись значения
    }

    public int at(int pos){
        return pos;
    }
}
