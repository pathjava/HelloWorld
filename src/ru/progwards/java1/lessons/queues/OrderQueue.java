package ru.progwards.java1.lessons.queues;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class OrderQueue {
    ArrayDeque<Order> thirdQueue = new ArrayDeque<>();
    ArrayDeque<Order> secondQueue = new ArrayDeque<>();
    ArrayDeque<Order> firstQueue = new ArrayDeque<>();

    public void add(Order order){
        if (order.getSum() > 0 && order.getSum() <= 10000.0){
            thirdQueue.offer(order);
        } else if (order.getSum() > 10000.0 && order.getSum() <= 20000.0){
            secondQueue.offer(order);
        } else
            firstQueue.offer(order);
    }

    Order returnOrder;
    public Order get(){
        for (int i = 0; i < 3; i++) {
            switch (i){
                case 0:
                    if (!firstQueue.isEmpty()){
                        return firstQueue.poll();
                    }
                    break;
                case 1:
                    if (!secondQueue.isEmpty()){
                        return secondQueue.poll();
                    }
                    break;
                case 2:
                    if (!thirdQueue.isEmpty()){
                        return thirdQueue.poll();
                    }
                    break;
            }
        }
        return returnOrder;
    }


    public static void main(String[] args) {
        OrderQueue orderQueue = new OrderQueue();
        orderQueue.add(new Order(26257.0));
        orderQueue.add(new Order(28723.0));
        orderQueue.add(new Order(8258.0));
        orderQueue.add(new Order(25805.0));
        orderQueue.add(new Order(3005.0));
        orderQueue.add(new Order(18765.0));
        orderQueue.add(new Order(2705.0));
        orderQueue.add(new Order(6447.0));
        orderQueue.add(new Order(13275.0));
        orderQueue.add(new Order(14840.0));
        orderQueue.add(new Order(19946.0));
        orderQueue.add(new Order(23877.0));
        orderQueue.add(new Order(6494.0));
        orderQueue.add(new Order(23694.0));
        orderQueue.add(new Order(22783.0));
        orderQueue.add(new Order(29282.0));
        orderQueue.add(new Order(11130.0));
        orderQueue.add(new Order(2443.0));
        orderQueue.add(new Order(13402.0));
        orderQueue.add(new Order(25664.0));
        orderQueue.add(new Order(19901.0));
        orderQueue.add(new Order(11239.0));
        orderQueue.add(new Order(11138.0));
        orderQueue.add(new Order(27393.0));

        Order order = orderQueue.get();
        while (order != null){
            System.out.println(order);
            order = orderQueue.get();
        }
    }
}
