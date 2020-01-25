package ru.progwards.java1.lessons.sets;

import java.util.List;

public class Shop {
    private List<ProductAnalytics.Product> products;

    public Shop(List<ProductAnalytics.Product> products) {
        this.products = products;
    }

    public List<ProductAnalytics.Product> getProducts() {
        return products;
    }
}
