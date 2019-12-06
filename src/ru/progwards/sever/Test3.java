package ru.progwards.sever;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Test3 {
    public static void main(String[] args) {
        String str1 = "12345";
        String str2 = "1234";
        str2 += "5";

        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));
    }
}
