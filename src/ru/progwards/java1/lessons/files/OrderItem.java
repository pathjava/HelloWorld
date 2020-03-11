package ru.progwards.java1.lessons.files;

public class OrderItem {
    public String googsName;
    public int count;
    public double price;

    public void setGoogsName(String googsName) {
        this.googsName = googsName;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "googsName='" + googsName + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
