package ru.progwards.java1.lessons.files;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class Order {
    public String shopId;
    public String orderId;
    public String customerId;
    public LocalDateTime datetime;
    public List<OrderItem> items;
    public double sum;

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getShopId() {
        return shopId;
    }

    public static class ShopIdComparator implements Comparator<Order> {
        @Override
        public int compare(Order o1, Order o2) {
            return o1.datetime.compareTo(o2.datetime);
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "shopId='" + shopId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", datetime=" + datetime +
                ", items=" + items +
                ", sum=" + sum +
                '}';
    }
}
