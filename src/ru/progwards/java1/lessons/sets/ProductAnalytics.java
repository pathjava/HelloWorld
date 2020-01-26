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
//        Set<Product> allProducts = new HashSet<Product>(products2);
//
//        for (Shop shop : shops) {
//            allProducts.addAll(new HashSet<Product>(shop.getProducts()));
//        }
//        allProducts.retainAll(products2);

//        Iterator<Shop> iterator = shops.iterator();
//        Set<Product> allProducts = new HashSet<Product>(((Shop) iterator.next()).getProducts());
//        while (iterator.hasNext()) {
//            products2.addAll(new HashSet<Product>(((Shop) iterator.next()).getProducts()));
//        }
//        products2.retainAll(allProducts);
//        return allProducts;

        if (shops == null || shops.size() == 0) return new HashSet<>();

        Iterator it = shops.iterator();
        //Set<Product> products2 = new HashSet<Product>(allProducts);
        Set<Product> products2 = new HashSet<Product>(((Shop) it.next()).getProducts());
        while (it.hasNext()) {
            products2.addAll(new HashSet<Product>(((Shop) it.next()).getProducts()));
        }
        products2.retainAll(products);
        return products2;
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

        Set<Product> products2 = new HashSet<Product>();
        if (products == null || products.size() == 0) return products2;
        if (shops == null || shops.size() == 0) return products2;
        for (int i = 0; i < shops.size(); i++) {
            Set<Product> productsBefore = new HashSet<Product>();
            Set<Product> products1 = new HashSet<Product>();
            Set<Product> productsNow;
            Iterator it = shops.iterator();
            for (int k = 0; k < shops.size(); k++) {
                productsNow = new HashSet<Product>(((Shop) it.next()).getProducts());
                productsNow.retainAll(products);
                if (k < i) {
                    productsBefore.addAll(productsNow);
                } else if (k == i) {
                    products1 = productsNow;
                    products1.removeAll(productsBefore);
                } else {
                    products1.removeAll(productsNow);
                }
            }
            products2.addAll(products1);
        }

        return products2;

//        Set<Product> allProducts = new HashSet<Product>(products2);
//
//        int i = 0;
//        while (i < shops.size()) {
//            Set<Product> earlierProducts = new HashSet<Product>();
//            Set<Product> tempProducts = new HashSet<Product>();
//            Set<Product> currentProducts;
//            Iterator it = shops.iterator();
//            int k = 0;
//            while (k < shops.size()) {
//                currentProducts = new HashSet<Product>(((Shop) it.next()).getProducts());
//                currentProducts.retainAll(products2);
//                if (k < i) {
//                    earlierProducts.addAll(currentProducts);
//                } else if (k == i) {
//                    tempProducts = currentProducts;
//                    tempProducts.removeAll(earlierProducts);
//                } else {
//                    tempProducts.removeAll(currentProducts);
//                }
//                k++;
//            }
//            products2.addAll(tempProducts);
//            i++;
//        }
//        return allProducts;
    }

}
