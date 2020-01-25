package ru.progwards.java1.lessons.sets;

import java.util.List;
import java.util.Set;

public class ProductAnalytics {

    private List<Shop> shops;
    private List<Product> products;

    public ProductAnalytics(List<Shop> shops, List<Product> products) {
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

    class Product {
        private String code;

        public Product(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    class Shop {
        private List<Product> products;

        public Shop(List<Product> products) {
            this.products = products;
        }

        public List<Product> getProducts() {
            return products;
        }
    }


}
