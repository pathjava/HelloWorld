package ru.progwards.java1.lessons.queues;

import java.util.ArrayDeque;

public class OrderQueue {
    ArrayDeque<Order> thirdQueue = new ArrayDeque<>();
    ArrayDeque<Order> secondQueue = new ArrayDeque<>();
    ArrayDeque<Order> firstQueue = new ArrayDeque<>();

    public void add(Order order){
        if (order.getSum() > 0 && order.getSum() <= 10000){
            thirdQueue.offer(order);
        } else if (order.getSum() > 10000 && order.getSum() <= 20000){
            secondQueue.offer(order);
        } else
            firstQueue.offer(order);
    }

    public Order get(){
        Order returnOrder = null;
        for (int i = 0; i < 3; i++) {
            switch (i){
                case 0:
                    while (!firstQueue.isEmpty()){
                        returnOrder = firstQueue.poll();
                    }
                    break;
                case 1:
                    while (!secondQueue.isEmpty()){
                        returnOrder = secondQueue.poll();
                    }
                    break;
                case 2:
                    while (!thirdQueue.isEmpty()){
                        returnOrder = thirdQueue.poll();
                    }
                    break;
            }
        }
        return returnOrder;
    }
}
