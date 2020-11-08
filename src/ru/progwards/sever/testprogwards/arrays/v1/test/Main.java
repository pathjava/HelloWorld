package ru.progwards.sever.testprogwards.arrays.v1.test;

import ru.progwards.sever.testprogwards.arrays.v1.javatrial.BArray;
import ru.progwards.sever.testprogwards.arrays.v1.javatrial.DArray;

import java.util.Date;

public class Main {

    static final int count1 = 100;
    static final int count2 = 1000000;

    public static void main(String[] args) {
        DArray<Integer> a1 = new DArray<Integer>();
        BArray<Integer> a2 = new BArray<Integer>(10000, 10000);

        Date start = new Date();
        for (int i = 0; i < count2; i++)
            a1.add(i);
        Date middle = new Date();
        for (int i = 0; i < count2; i++)
            a2.add(i);
        Date stop = new Date();

        for (int i = 0; i < count1; i++)
            System.out.println(i + " " + a1.get(i) + " " + a1.get(i));
        System.out.println("PArray add time =" + (middle.getTime() - start.getTime()));
        System.out.println("BArray add time =" + (stop.getTime() - middle.getTime()));
    }
}
