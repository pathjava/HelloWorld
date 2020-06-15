// Oleg Kiselev
// 15.06.2020, 11:37

package ru.progwards.java2.lessons.calculator;


import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private static final List<String> list = new ArrayList<>();

    public static int calculate(String expression) {

        return 0;
    }

    private static void readString(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                builder.append(ch);
                if (i == str.length() - 1)
                    list.add(builder.toString());
            } else {
                if (!builder.toString().equals("")) {
                    list.add(builder.toString());
                }
                list.add(String.valueOf(ch));
                builder = new StringBuilder();
            }
        }
    }

    private static int add(int a, int b) {
        return a + b;
    }

    private static int diff(int a, int b) {
        return a - b;
    }

    private static int mult(int a, int b) {
        return a * b;
    }

    private static int div(int a, int b) {
        return a / b;
    }


    public static void main(String[] args) {
//        calculate("5+(25+3)*12/2-3");

        readString("5+(25+3)*12/2-3");
        for (String s : list) {
            System.out.print(s + " ");
        }
    }
}
