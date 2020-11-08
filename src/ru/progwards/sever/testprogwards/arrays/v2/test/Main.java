package ru.progwards.sever.testprogwards.arrays.v2.test;

import ru.progwards.sever.testprogwards.arrays.v2.javatrial.BArray;
import ru.progwards.sever.testprogwards.arrays.v2.javatrial.DArray;
import ru.progwards.sever.testprogwards.arrays.v2.javatrial.PArray;
import ru.progwards.sever.testprogwards.arrays.v2.javatrial.AArray;

import java.util.Date;

public class Main {

    static final int count1 = 100;
    static final int count2 = 100000;

    public static void main(String[] args) {
        testArray(new DArray<Integer>(AArray.CopyType.ITEM), AArray.CopyType.ITEM);
        testArray(new DArray<Integer>(AArray.CopyType.ARRAYS), AArray.CopyType.ARRAYS);
        testArray(new DArray<Integer>(AArray.CopyType.SYSTEM), AArray.CopyType.SYSTEM);

        testArray(new BArray<Integer>(AArray.CopyType.ITEM, 1000, 1000), AArray.CopyType.ITEM);
        testArray(new BArray<Integer>(AArray.CopyType.ARRAYS, 1000, 1000), AArray.CopyType.ARRAYS);
        testArray(new BArray<Integer>(AArray.CopyType.SYSTEM, 1000, 1000), AArray.CopyType.SYSTEM);

        testArray(new PArray<Integer>(AArray.CopyType.ITEM, 1000), AArray.CopyType.ITEM);
        testArray(new PArray<Integer>(AArray.CopyType.ARRAYS, 1000), AArray.CopyType.ARRAYS);
        testArray(new PArray<Integer>(AArray.CopyType.SYSTEM, 1000), AArray.CopyType.SYSTEM);
    }

    static void testArray(AArray a, DArray.CopyType copy) {
        Date start = new Date();
        for (int i = 0; i < count2; i++)
            a.add(i);
        Date stop = new Date();
        //for(int i=0; i<count1*11; i++)
        //    System.out.println(i+" "+a.get(i));
        System.out.println(a.getClass().getName() + " " + copy + " add time =" + (stop.getTime() - start.getTime()));
    }
}
