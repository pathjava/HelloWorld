package ru.progwards.sever;

import java.math.BigDecimal;

public class Test5 {
    //    public static void main(String[] args)
//    {
//        // Creating BigDecimal object
//        BigDecimal b1
//                = new BigDecimal("924567");
//
//        // Exponent or power
//        int n = 5;
//
//        // Using pow() method
//        BigDecimal result = b1.pow(n);
//
//        // Display result
//        System.out.println("Result of pow operation "
//                + "between BigDecimal "
//                + b1 + " and exponent "
//                + n + " equal to "
//                + result);
//    }
    public static void main(String[] args) {
        div(6, 3);
        div(10, 6);
        div(2, 4);
    }

    public static void div(int a, int b) {
        System.out.println(a / b);

    }
}