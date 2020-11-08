package ru.progwards.java1.lessons.queues;

import java.util.ArrayDeque;
import java.util.List;

public class OrderQueue {
    /* заводим три очереди для каждой группы заказов в зависимости от суммы заказа */
    ArrayDeque<Order> thirdQueue = new ArrayDeque<>();
    ArrayDeque<Order> secondQueue = new ArrayDeque<>();
    ArrayDeque<Order> firstQueue = new ArrayDeque<>();

    /* метод, отвечающий за добавление заказов в очередь ArrayDeque */
    public void add(Order order) {
        /* проверяем сумму заказа и помещаем заказ в соответствующую очередь */
        if (order.getSum() > 0 && order.getSum() <= 10000.0) {
            thirdQueue.offer(order);
        } else if (order.getSum() > 10000.0 && order.getSum() <= 20000.0) {
            secondQueue.offer(order);
        } else
            firstQueue.offer(order);
    }

    /* метод, отвечающий за получение/вывод заказа */
    public Order get() {
        /* если первая очередь не пустая, извлекаем первый заказ в очереди и возвращаем его в метод */
        if (!firstQueue.isEmpty()) {
            return firstQueue.poll();
            /* если первая очередь дошла до конца и пустая, переходим ко второй очереди и т.д. */
        } else if (!secondQueue.isEmpty()) {
            return secondQueue.poll();
        } else if (!thirdQueue.isEmpty()) {
            return thirdQueue.poll();
        }
        /* когда все очереди заказов пустые, возвращаем null */
        return null;
    }


    public static void main(String[] args) {
        OrderQueue orderQueue = new OrderQueue();
        /* передаем в метод add(Order order) параметр (сумму заказа) */

        ArrayDeque<Double> arrayDeque = new ArrayDeque<>(List.of(26257.0, 28723.0, 8258.0, 25805.0, 3005.0, 18765.0, 2705.0, 6447.0, 13275.0, 14840.0, 19946.0, 23877.0, 6494.0, 23694.0, 22783.0, 29282.0, 11130.0, 2443.0, 13402.0, 25664.0, 19901.0, 11239.0, 11138.0, 27393.0));
        for (Double aDouble : arrayDeque) {
            orderQueue.add(new Order(aDouble));
        }

//        orderQueue.add(new Order(26257.0));
//        orderQueue.add(new Order(28723.0));
//        orderQueue.add(new Order(8258.0));
//        orderQueue.add(new Order(25805.0));
//        orderQueue.add(new Order(3005.0));
//        orderQueue.add(new Order(18765.0));
//        orderQueue.add(new Order(2705.0));
//        orderQueue.add(new Order(6447.0));
//        orderQueue.add(new Order(13275.0));
//        orderQueue.add(new Order(14840.0));
//        orderQueue.add(new Order(19946.0));
//        orderQueue.add(new Order(23877.0));
//        orderQueue.add(new Order(6494.0));
//        orderQueue.add(new Order(23694.0));
//        orderQueue.add(new Order(22783.0));
//        orderQueue.add(new Order(29282.0));
//        orderQueue.add(new Order(11130.0));
//        orderQueue.add(new Order(2443.0));
//        orderQueue.add(new Order(13402.0));
//        orderQueue.add(new Order(25664.0));
//        orderQueue.add(new Order(19901.0));
//        orderQueue.add(new Order(11239.0));
//        orderQueue.add(new Order(11138.0));
//        orderQueue.add(new Order(27393.0));

        Order order = orderQueue.get();
        while (order != null) {
            System.out.println(order);
            order = orderQueue.get();
        }
    }
}
