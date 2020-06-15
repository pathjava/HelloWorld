// Oleg Kiselev
// 15.06.2020, 11:37

package ru.progwards.java2.lessons.calculator;


import java.util.Map;
import java.util.TreeMap;

public class Calculator {

    private static final Map<Integer, String> map = new TreeMap<>();


    public static int calculate(String expression) {

        return 0;
    }

    private static void readString(String str) {
        StringBuilder builder = new StringBuilder();
        int count = 1;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                builder.append(ch);
                if (i == str.length()-1)
                    map.put(count, builder.toString());
            } else {
                if (!builder.toString().equals("")) {
                    map.put(count, builder.toString());
                    count++;
                }
                map.put(count, String.valueOf(ch));
                builder = new StringBuilder();
                count++;
            }
        }
    }


    public static void main(String[] args) {
//        calculate("5+(25+3)*12/2-3");

        readString("5+(25+3)*12/2-3");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }
    }
}
