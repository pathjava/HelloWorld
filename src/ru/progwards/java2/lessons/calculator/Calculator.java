// Oleg Kiselev
// 15.06.2020, 11:37

package ru.progwards.java2.lessons.calculator;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Calculator {

    private final List<String> list = new ArrayList<>();
    private final List<String> tempList = new ArrayList<>();

    public int calculate(String expression) {
        if (expression.isEmpty())
            throw new NoSuchElementException();
        readString(expression);
        if (checkBrackets())
            searchBrackets();

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

    private void searchBrackets() {
        while (checkBrackets()) {
            int start = 0;
            int end = 0;
            for (int i = 0; i < list.size(); i++)
                if (list.get(i).equals("(")) {
                    start = i;
                    break;
                }
            for (int i = start; i < list.size(); i++)
                if (list.get(i).equals(")")) {
                    end = i;
                    break;
                }
            for (int i = start; i <= end; i++)
                tempList.add(list.get(i));
            list.set(start, operationsInBrackets());
            delete(list, start + 1, end - start);
        }
    }

    private String operationsInBrackets() {
        boolean lock = true;
        while (lock) {
            if (checkMul(tempList))
                multiplication();
            if (checkDiv(tempList))
                division();
            else
                lock = false;
        }
        lock = true;
        while (lock) {
            if (checkPlus(tempList))
                additional();
            if (checkMinus(tempList))
                subtraction();
            else
                lock = false;
        }
        return tempList.get(1);
    }

    private void multiplication() {
        for (int i = 0; i < tempList.size(); i++)
            if (tempList.get(i).equals("*"))
                if (i - 1 >= 0 && i + 1 <= tempList.size()) {
                    String result = mult(tempList.get(i - 1), tempList.get(i + 1));
                    tempList.set(i - 1, result);
                    delete(tempList, i, 2);
                    break;
                }
    }

    private void division() {
        for (int i = 0; i < tempList.size(); i++)
            if (tempList.get(i).equals("/"))
                if (i - 1 >= 0 && i + 1 <= tempList.size()) {
                    String result = div(tempList.get(i - 1), tempList.get(i + 1));
                    tempList.set(i - 1, result);
                    delete(tempList, i, 2);
                    break;
                }
    }

    private void additional() {
        for (int i = 0; i < tempList.size(); i++)
            if (tempList.get(i).equals("+"))
                if (i - 1 >= 0 && i + 1 <= tempList.size()) {
                    String result = add(tempList.get(i - 1), tempList.get(i + 1));
                    tempList.set(i - 1, result);
                    delete(tempList, i, 2);
                    break;
                }
    }

    private void subtraction() {
        for (int i = 0; i < tempList.size(); i++)
            if (tempList.get(i).equals("-"))
                if (i - 1 >= 0 && i + 1 <= tempList.size()) {
                    String result = diff(tempList.get(i - 1), tempList.get(i + 1));
                    tempList.set(i - 1, result);
                    delete(tempList, i, 2);
                    break;
                }
    }

    private void delete(List<String> list, int start, int count) {
        int i = 0;
        while (i < count) {
            list.remove(start);
            i++;
        }
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

    private boolean checkMul(List<String> list) {
        for (String s : list) {
            if (s.equals("*"))
                return true;
        }
        return false;
    }

    private boolean checkDiv(List<String> list) {
        for (String s : list) {
            if (s.equals("/"))
                return true;
        }
        return false;
    }

    private boolean checkPlus(List<String> list) {
        for (String s : list) {
            if (s.equals("+"))
                return true;
        }
        return false;
    }

    private boolean checkMinus(List<String> list) {
        for (String s : list) {
            if (s.equals("-"))
                return true;
        }
        return false;
    }

    private String add(String a, String b) {
        return String.valueOf(Integer.parseInt(a) + Integer.parseInt(b));
    }

    private String diff(String a, String b) {
        return String.valueOf(Integer.parseInt(a) - Integer.parseInt(b));
    }

    private String mult(String a, String b) {
        return String.valueOf(Integer.parseInt(a) * Integer.parseInt(b));
    }

    private String div(String a, String b) {
        return String.valueOf(Integer.parseInt(a) / Integer.parseInt(b));
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.calculate("5+((25+3)*2/2)*12/2-3");

        for (String s : calc.list) {
            System.out.print(s + " ");
        }
    }
}
