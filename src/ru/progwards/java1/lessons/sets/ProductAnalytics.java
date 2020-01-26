package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ProductAnalytics {

    private List<Shop> shops;
    private List<Product> products;

    public ProductAnalytics(List<Product> products, List<Shop> shops) {
        this.shops = shops;
        this.products = products;
    }

    // товары из products, которые имеются во всех магазинах
    public Set<Product> existInAll(){
        Set<Product> allProducts = new HashSet<Product>(products);

        for (Shop shop : shops) {
            allProducts.retainAll(new HashSet<Product>(shop.getProducts()));
        }
        return allProducts;
    }

    // товары из products, которые имеются хотя бы в одном магазине
    public Set<Product> existAtListInOne(){
        if (shops == null || shops.size() == 0) return new HashSet<>();

        Iterator<Shop> it = shops.iterator();
        Set<Product> allProducts = new HashSet<Product>(((Shop) it.next()).getProducts());
        while (it.hasNext()) {
            allProducts.addAll(new HashSet<Product>(((Shop) it.next()).getProducts()));
        }
        allProducts.retainAll(products);
        return allProducts;
    }

    // товары из products, которых нет ни в одном магазине
    public Set<Product> notExistInShops(){
        Set<Product> allProducts = new HashSet<Product>(products);

        for (Shop shop : shops) {
            allProducts.removeAll(new HashSet<Product>(shop.getProducts()));
        }
        return allProducts;
    }

    // товары из products, которые есть только в одном магазине
    public Set<Product> existOnlyInOne(){

        Set<Product> allProducts = new HashSet<Product>();
        if (products == null || products.size() == 0) return allProducts;
        if (shops == null || shops.size() == 0) return allProducts;
        for (int i = 0; i < shops.size(); i++) {
            Set<Product> earlierProducts = new HashSet<Product>();
            Set<Product> tempProducts = new HashSet<Product>();
            Set<Product> currentProducts;
            Iterator<Shop> it = shops.iterator();
            for (int k = 0; k < shops.size(); k++) {
                currentProducts = new HashSet<Product>(((Shop) it.next()).getProducts());
                currentProducts.retainAll(products);
                if (k < i) {
                    earlierProducts.addAll(currentProducts);
                } else if (k == i) {
                    tempProducts = currentProducts;
                    tempProducts.removeAll(earlierProducts);
                } else {
                    tempProducts.removeAll(currentProducts);
                }
            }
            allProducts.addAll(tempProducts);
        }
        return allProducts;
    }

}
