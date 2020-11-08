package ru.progwards.java1.lessons.queues;

import java.util.*;

public class StackCalc {
    /* создаем массив для стека */
    ArrayDeque<Double> arrayDeque = new ArrayDeque<>();

    /* заполняем массив, он же стек */
    public void push(double value) {
        arrayDeque.push(value);
    }

    /* если стек не пустой получаем и удаляем верхнее значение в стеке */
    public double pop() {
        return arrayDeque.pop();
    }

    /* заводим две переменные, проверяем не пустой ли стек и извлекаем из него
     * первые значения по очереди, помещая их в переменные */
    public void add() {
        double one = 0;
        double two = 0;
        if (!arrayDeque.isEmpty()) {
            one = arrayDeque.pop();
        }
        if (!arrayDeque.isEmpty()) {
            two = arrayDeque.pop();
        }
        /* складываем значения двух переменных и результат помещаем на вершину стека */
        arrayDeque.push(one + two);
    }

    public void sub() {
        double one = 0;
        double two = 0;
        if (!arrayDeque.isEmpty()) {
            one = arrayDeque.pop();
        }
        if (!arrayDeque.isEmpty()) {
            two = arrayDeque.pop();
        }
        /* вычитаем из одной переменной другую и результат помещаем на вершину стека */
        arrayDeque.push(two - one);
    }

    public void mul() {
        double one = 0;
        double two = 0;
        if (!arrayDeque.isEmpty()) {
            one = arrayDeque.pop();
        }
        if (!arrayDeque.isEmpty()) {
            two = arrayDeque.pop();
        }
        /* умножаем значения двух переменных и результат помещаем на вершину стека */
        arrayDeque.push(one * two);
    }

    public void div() {
        double one = 0;
        double two = 0;
        if (!arrayDeque.isEmpty()) {
            one = arrayDeque.pop();
        }
        if (!arrayDeque.isEmpty()) {
            two = arrayDeque.pop();
        }
        /* делим значение одной переменной на другую и результат помещаем на вершину стека */
        arrayDeque.push(two / one);
    }


    public static void main(String[] args) {
        /* создаем массив и заполняем его значениями */
        ArrayDeque<Double> arrDeq = new ArrayDeque<>(List.of(10.2, 45.11, 14.124, 28.1, 5.783, 15.02, 20.154));

        /* создаем объект класса */
        StackCalc calc = new StackCalc();
        /* в цикле передаем значения из массива в стек (он же массив) arrayDeque */
        for (Double aDouble : arrDeq) {
            calc.push(aDouble);
        }

        /* вызываем методы и выводим результаты на консоль */
        System.out.println(calc.arrayDeque);
        System.out.println(calc.pop());
        System.out.println(calc.arrayDeque);
        calc.add();
        System.out.println(calc.arrayDeque);
        calc.sub();
        System.out.println(calc.arrayDeque);
        calc.mul();
        System.out.println(calc.arrayDeque);
        calc.div();
        System.out.println(calc.arrayDeque);
    }
}
