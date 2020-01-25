package ru.progwards.java1.lessons.sets;

import java.util.List;
import java.util.Set;

public class ProductAnalytics {

    private List<Shop> shops;
    private List<Product> products;

    public ProductAnalytics(List<Product> products, List<Shop> shops) {
        this.shops = shops;
        this.products = products;
    }

    public Set<Product> existInAll(){
        return (Set<Product>) products;
    }
    public Set<Product> existAtListInOne(){
        return (Set<Product>) products;
    }
    public Set<Product> notExistInShops(){
        return (Set<Product>) products;
    }
    public Set<Product> existOnlyInOne(){
        return (Set<Product>) products;
    }

}
