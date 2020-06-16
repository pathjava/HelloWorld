// Oleg Kiselev
// 15.06.2020, 11:37

package ru.progwards.java2.lessons.calculator;


import java.util.*;
import java.util.stream.IntStream;

public class Calculator {

    private final List<String> list = new ArrayList<>();
    private final List<String> tempList = new ArrayList<>();

    public int calculate(String expression) {
        if (expression.isEmpty())
            throw new NoSuchElementException();
        readString(expression);
        if (checkBrackets())
            searchBrackets();
        searchArithmeticSigns();
        return Integer.parseInt(list.get(0));
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
                if (!builder.toString().equals(""))
                    list.add(builder.toString());
                list.add(String.valueOf(ch));
                builder = new StringBuilder();
            }
        }
    }

    /* если в выражение есть скобки, начинаем обрабатывать их в первую очередь */
    private void searchBrackets() {
        while (checkBrackets()) {
            tempList.clear();
            int start = 0;
            int end = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals("("))
                    start = i;
                if (list.get(i).equals(")")) {
                    end = i;
                    break;
                }
            }
            IntStream.rangeClosed(start, end).mapToObj(list::get).forEach(tempList::add);
            list.set(start, operationsInBrackets());
            delete(list, start + 1, end - start);
        }
        tempList.clear();
    }

    private String operationsInBrackets() {
        multiplicationAndDivision();
        additionalAndSubtraction();
        return tempList.get(1);
    }

    private void multiplicationAndDivision() {
        boolean lock = true;
        while (lock) {
            if (checkMul(tempList))
                multiplicationInBrackets();
            if (checkDiv(tempList))
                divisionInBrackets();
            else
                lock = false;
        }
    }

    private void additionalAndSubtraction() {
        boolean lock = true;
        while (lock) {
            if (checkPlus(tempList))
                additionalInBrackets();
            if (checkMinus(tempList))
                subtractionInBrackets();
            else
                lock = false;
        }
    }

    private void multiplicationInBrackets() {
        for (int i = 0; i < tempList.size(); i++)
            if (tempList.get(i).equals("*"))
                if (i - 1 >= 0 && i + 1 <= tempList.size()) {
                    String result = mult(tempList.get(i - 1), tempList.get(i + 1));
                    tempList.set(i - 1, result);
                    delete(tempList, i, 2);
                    break;
                }
    }

    private void divisionInBrackets() {
        for (int i = 0; i < tempList.size(); i++)
            if (tempList.get(i).equals("/"))
                if (i - 1 >= 0 && i + 1 <= tempList.size()) {
                    String result = div(tempList.get(i - 1), tempList.get(i + 1));
                    tempList.set(i - 1, result);
                    delete(tempList, i, 2);
                    break;
                }
    }

    private void additionalInBrackets() {
        for (int i = 0; i < tempList.size(); i++)
            if (tempList.get(i).equals("+"))
                if (i - 1 >= 0 && i + 1 <= tempList.size()) {
                    String result = add(tempList.get(i - 1), tempList.get(i + 1));
                    tempList.set(i - 1, result);
                    delete(tempList, i, 2);
                    break;
                }
    }

    private void subtractionInBrackets() {
        for (int i = 0; i < tempList.size(); i++)
            if (tempList.get(i).equals("-"))
                if (i - 1 >= 0 && i + 1 <= tempList.size()) {
                    String result = diff(tempList.get(i - 1), tempList.get(i + 1));
                    tempList.set(i - 1, result);
                    delete(tempList, i, 2);
                    break;
                }
    }

    /* если скобок нет в выражение, выполняем операции согласно приоритета */
    private void searchArithmeticSigns() {
        multiplication();
        division();
        additional();
        subtraction();
    }

    private void multiplication() {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).equals("*")) {
                for (int j = i - 1; j <= i + 1; j++)
                    tempList.add(list.get(j));
                multiplicationInBrackets();
                list.set(i - 1, tempList.get(0));
                delete(list, i, 2);
                tempList.clear();
            }
    }

    private void division() {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).equals("/")) {
                for (int j = i - 1; j <= i + 1; j++)
                    tempList.add(list.get(j));
                divisionInBrackets();
                list.set(i - 1, tempList.get(0));
                delete(list, i, 2);
                tempList.clear();
            }
    }

    private void additional() {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).equals("+")) {
                for (int j = i - 1; j <= i + 1; j++)
                    tempList.add(list.get(j));
                additionalInBrackets();
                list.set(i - 1, tempList.get(0));
                delete(list, i, 2);
                tempList.clear();
            }
    }

    private void subtraction() {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).equals("-")) {
                for (int j = i - 1; j <= i + 1; j++)
                    tempList.add(list.get(j));
                subtractionInBrackets();
                list.set(i - 1, tempList.get(0));
                delete(list, i, 2);
                tempList.clear();
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
        return list.stream().anyMatch(s -> s.equals("("));
    }

    private boolean checkMul(List<String> list) {
        return list.stream().anyMatch(s -> s.equals("*"));
    }

    private boolean checkDiv(List<String> list) {
        return list.stream().anyMatch(s -> s.equals("/"));
    }

    private boolean checkPlus(List<String> list) {
        return list.stream().anyMatch(s -> s.equals("+"));
    }

    private boolean checkMinus(List<String> list) {
        return list.stream().anyMatch(s -> s.equals("-"));
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
        calc.calculate("5+(((15+3))*2/2)*12/2-3*2+(5*7)");

        for (String s : calc.list) {
            System.out.print(s + " ");
        }
    }
}
