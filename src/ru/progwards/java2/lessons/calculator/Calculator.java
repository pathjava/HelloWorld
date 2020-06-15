// Oleg Kiselev
// 15.06.2020, 11:37

package ru.progwards.java2.lessons.calculator;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Calculator {

    private final List<String> list = new ArrayList<>();

    public int calculate(String expression) {
        if (expression.isEmpty())
            throw new NoSuchElementException();
        readString(expression);
        if (checkBrackets())
            operationsInBrackets();

        return 0;
    }

    private void readString(String str) {
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

    private void operationsInBrackets() {
        List<String> tempOneList = new ArrayList<>();
        int start = 0;
        int end = 0;
        boolean lock = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("(")) {
                lock = true;
                start = i;
            }
            if (lock)
                tempOneList.add(list.get(i));
            if (list.get(i).equals(")")) {
                end = i;
                break;
            }
        }
        List<String> tempTwoList = new ArrayList<>(mulOrDiv(tempOneList));
        tempOneList.clear();
        tempOneList.addAll(tempTwoList);
        tempTwoList.clear();
    }

    private List<String> mulOrDiv(List<String> list) {
        List<String> tempList = new ArrayList<>();
        String result = null;
        while (checkMulOrDiv(list)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals("*") || list.get(i).equals("/"))
                    if (i - 1 >= 0 && i + 1 <= list.size()) {
                        if (list.get(i).equals("*"))
                            result = mult(list.get(i - 1), list.get(i + 1));
                        else
                            result = div(list.get(i - 1), list.get(i + 1));
                        list.set(i - 1, result);
                        tempList.addAll(delete(list, i, 2));
                        list.clear();
                        list.addAll(tempList);
                        tempList.clear();
                        break;
                    }
            }
        }
        for (String s : list) {
            System.out.print(s + " ");
        }
        return tempList;
    }

    private List<String> delete(List<String> list, int start, int count) {
        List<String> tempList = new ArrayList<>(list);
        int i = 0;
        while (i < count) {
            tempList.remove(start);
            i++;
        }
        return tempList;
    }

    private boolean checkBrackets() {
        int count = 0;
        while (count < list.size()) {
            if (list.get(count).equals("("))
                return true;
            count++;
        }
        return false;
    }

    private boolean checkMulOrDiv(List<String> list) {
        int count = 0;
        while (count < list.size()) {
            if (list.get(count).equals("*") || list.get(count).equals("/"))
                return true;
            count++;
        }
        return false;
    }

    private int add(int a, int b) {
        return a + b;
    }

    private int diff(int a, int b) {
        return a - b;
    }

    private String mult(String a, String b) {
        return String.valueOf(Integer.parseInt(a) * Integer.parseInt(b));
    }

    private String div(String a, String b) {
        return String.valueOf(Integer.parseInt(a) / Integer.parseInt(b));
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.calculate("5+(25+3*2/2)*12/2-3");

//        for (String s : calc.list) {
//            System.out.print(s + " ");
//        }


    }
}
