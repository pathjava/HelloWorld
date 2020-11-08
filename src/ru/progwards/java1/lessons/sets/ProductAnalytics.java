package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/* данная задача, а именно методы были в значительной степени решены не самостоятельно.
 * для меня до сих пор задача остается не понятной в определенной степени.
 * подобные условия задач в моем понимание, это как строить дом с крыши, а не с фундамента.
 * поясню - чтобы понять, что необходимо сделать, мне нужны входящие данные, то есть, что получаем на входе
 * и как это будет/должно выглядеть на выходе. когда условие задачи такое, я могу представить процессы решения,
 * написать примерный код и уже далее через отладчик найти конечное решение.
 * в данном случае условие задачи для меня как воздух - нельзя потрогать.
 * лично для меня крайне неприятно (именно перед самим собой), что решение копипаст, но я понимаю,
 * что сидеть над решением можно было бесконечно долго с околонулевым результатом.
 * я понимаю, что основная суть задачи на пересечениях/разностях и в первой задаче SetOperations Н12 я решил их довольно легко,
 * но там для меня было все понятно, что приходит и что на выходе должно быть.*/

public class ProductAnalytics {
    /* переменные, хранящие названия магазинов и продуктов */
    private List<Shop> shops;
    private List<Product> products;

    /* конструктор */
    public ProductAnalytics(List<Product> products, List<Shop> shops) {
        this.shops = shops;
        this.products = products;
    }

    // товары из products, которые имеются во всех магазинах
    public Set<Product> existInAll() {
        /* заводим хэш-таблицу */
        Set<Product> allProducts = new HashSet<Product>(products);
        /* проходим циклом по магазинам и получаем список всех имеющихся в магазинах продуктов */
        for (Shop shop : shops) {
            allProducts.retainAll(new HashSet<Product>(shop.getProducts()));
        }
        return allProducts;
    }

    // товары из products, которые имеются хотя бы в одном магазине
    public Set<Product> existAtListInOne() {
        /* заводим итератор */
        Iterator<Shop> iterator = shops.iterator();
        /* заводим хэш-таблицу */
        Set<Product> allProducts = new HashSet<Product>(((Shop) iterator.next()).getProducts());
        /* проходим итератором по продуктам, пока не дойдем до конца перечня */
        while (iterator.hasNext()) {
            /* помещаем/объединяем в хэш-таблицу все товары, собранные итератором */
            allProducts.addAll(new HashSet<Product>(((Shop) iterator.next()).getProducts()));
        }
        /* исключаем из собранного списка продуктов (allProducts) все пересечения с полным списком продуктов products */
        allProducts.retainAll(products);
        return allProducts;
    }

    // товары из products, которых нет ни в одном магазине
    public Set<Product> notExistInShops() {
        /* заводим хэш-таблицу */
        Set<Product> allProducts = new HashSet<Product>(products);
        /* проходим циклом по магазинам и получаем список всех имеющихся в магазинах продуктов */
        for (Shop shop : shops) {
            /* удаляем из allProducts продукты, имеющиеся в магазинах, оставляя в списке только отсутствующие в магазинах */
            allProducts.removeAll(new HashSet<Product>(shop.getProducts()));
        }
        return allProducts;
    }

    // товары из products, которые есть только в одном магазине
    public Set<Product> existOnlyInOne() {
        /* заводим хэш-таблицу */
        Set<Product> allProducts = new HashSet<Product>();
        for (int i = 0; i < shops.size(); i++) {
            /* таблица продуктов ранее присутствовавших в магазинах */
            Set<Product> earlierProducts = new HashSet<Product>();
            /* временная таблица */
            Set<Product> tempProducts = new HashSet<Product>();
            /* текущая таблица продуктов */
            Set<Product> currentProducts;
            /* заводим итератор */
            Iterator<Shop> iterator = shops.iterator();
            /* заводим вложенный цикл */
            for (int j = 0; j < shops.size(); j++) {
                /* получаем список текущих продуктов в магазинах */
                currentProducts = new HashSet<Product>(((Shop) iterator.next()).getProducts());
                /* удаляем из текущей таблицы пересечения с полным списком продуктов */
                currentProducts.retainAll(products);
                if (j < i) {
                    earlierProducts.addAll(currentProducts);
                } else if (j == i) {
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
