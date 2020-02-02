package ru.progwards.java1.lessons.queues;

import java.util.*;

public class StackCalc {
    ArrayDeque<Double> arrayDeque = new ArrayDeque<>();

    public void push(double value){
        if (!(value == 0)) {
            arrayDeque.push(value);
        }
//        System.out.println(arrayDeque);
    }

    public double pop(){
        if (arrayDeque.isEmpty()){
            System.out.println("array is null");
        }
        return arrayDeque.pop();
    }

    public void add(){
        double one = 0;
        double two = 0;
        if (!arrayDeque.isEmpty()) {
            one = arrayDeque.poll();
        }
        if (!arrayDeque.isEmpty()) {
            two = arrayDeque.poll();
        }
        arrayDeque.push(one + two);
    }

    public void sub(){
        double one = 0;
        double two = 0;
        if (!arrayDeque.isEmpty()) {
            one = arrayDeque.poll();
        }
        if (!arrayDeque.isEmpty()) {
            two = arrayDeque.poll();
        }
        arrayDeque.push(two - one);
    }

    public void mul(){
        double one = 0;
        double two = 0;
        if (!arrayDeque.isEmpty()) {
            one = arrayDeque.poll();
        }
        if (!arrayDeque.isEmpty()) {
            two = arrayDeque.poll();
        }
        arrayDeque.push(one * two);
    }

    public void div(){
        double one = 0;
        double two = 0;
        if (!arrayDeque.isEmpty()) {
            one = arrayDeque.poll();
        }
        if (!arrayDeque.isEmpty()) {
            two = arrayDeque.poll();
        }
        arrayDeque.push(one / two);
    }


    public static void main(String[] args) {
        ArrayDeque<Double> arrDeq = new ArrayDeque<>(List.of(10.2,45.11,14.124,28.1,5.783,15.02,20.154));
//        final int ELEMENT = 10;
//        Random random = new Random();
//
//        for (int i = 0; i < ELEMENT; i++) {
//            arrDeq.push(random.nextDouble());
//        }

        StackCalc calc = new StackCalc();
        for (Double aDouble : arrDeq) {
            calc.push(aDouble);
        }

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
