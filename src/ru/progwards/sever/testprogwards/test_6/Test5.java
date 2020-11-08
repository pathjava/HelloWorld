package ru.progwards.sever.testprogwards.test_6;

import java.util.Arrays;

public class Test5 {

    private int[] nums = {};

    Test5() {
    }

    ;

    // добавляет элемент num в конец массива
    public void add(int num) {
        int[] nums1 = new int[nums.length + 1];
        System.arraycopy(nums, 0, nums1, 0, nums.length);
        nums1[nums.length] = num;
        nums = nums1;
    }

    // добавляет элемент num в позицию pos массива
    public void atInsert(int pos, int num) {
        int[] nums1 = new int[nums.length + 1];
        System.arraycopy(nums, 0, nums1, 0, pos);
        System.arraycopy(nums, pos, nums1, pos + 1, nums.length - pos);
        nums1[pos] = num;
        nums = nums1;
    }

    // удаляет элемент в позиции pos массива
    public void atDelete(int pos) {
        int[] nums1 = new int[nums.length - 1];
        System.arraycopy(nums, 0, nums1, 0, pos);
        System.arraycopy(nums, pos + 1, nums1, pos, nums.length - pos - 1);
        nums = nums1;
    }

    // возвращает элемент по индексу pos
    public int at(int pos) {
        return nums[pos];
    }

    @Override
    public String toString() {
        return Arrays.toString(nums);
    }

    public void print() {
        System.out.println(this.toString());
    }

    // test
    public static void main(String[] args) {
        Test5 a = new Test5();
        a.print();
        a.add(1);
        a.add(2);
        a.add(4);
        a.add(5);
        a.print();
        a.atInsert(2, 3);
        a.print();
        a.atDelete(1);
        a.print();
        System.out.println("a[2] = " + a.at(2));
    }

}