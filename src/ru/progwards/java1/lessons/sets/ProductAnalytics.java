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
        Set<Product> allProducts = new HashSet<Product>(products);

        for (Shop shop : shops) {
            allProducts.addAll(new HashSet<Product>(shop.getProducts()));
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
        Set<Product> allProducts = new HashSet<Product>(products);

        int i = 0;
        while (i < shops.size()) {
            Set<Product> earlierProducts = new HashSet<Product>();
            Set<Product> tempProducts = new HashSet<Product>();
            Set<Product> currentProducts;
            Iterator it = shops.iterator();
            int k = 0;
            while (k < shops.size()) {
                currentProducts = new HashSet<Product>(((Shop) it.next()).getProducts());
                currentProducts.retainAll(allProducts);
                if (k < i) {
                    earlierProducts.addAll(currentProducts);
                } else if (k == i) {
                    tempProducts = currentProducts;
                    tempProducts.removeAll(earlierProducts);
                } else {
                    tempProducts.removeAll(currentProducts);
                }
                k++;
            }
            products.addAll(tempProducts);
            i++;
        }

//        for (int i = 0; i < shops.size(); i++) {
//            Set<Product> earlierProducts = new HashSet<Product>();
//            Set<Product> tempProducts = new HashSet<Product>();
//            Set<Product> currentProducts;
//            Iterator it = shops.iterator();
//            for (int k = 0; k < shops.size(); k++) {
//                currentProducts = new HashSet<Product>(((Shop) it.next()).getProducts());
//                currentProducts.retainAll(allProducts);
//                if (k < i) {
//                    earlierProducts.addAll(currentProducts);
//                } else if (k == i) {
//                    tempProducts = currentProducts;
//                    tempProducts.removeAll(earlierProducts);
//                } else {
//                    tempProducts.removeAll(currentProducts);
//                }
//            }
//            products.addAll(tempProducts);
//        }
        return allProducts;
    }

}
