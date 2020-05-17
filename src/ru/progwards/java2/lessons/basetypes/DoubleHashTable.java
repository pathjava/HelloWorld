// Oleg Kiselev
// 17.05.2020, 19:06

package ru.progwards.java2.lessons.basetypes;

import java.math.BigInteger;

public class DoubleHashTable<K, V> {

    private int size = 101;

    class HashTableContainer<K, V> {
        private int hash;
        private K key;
        private V value;
        private HashTableContainer<K, V> nextKey;
    }

    public int sizeTable(int currentSize) {
        int newSize = 0;
        int tempSize = currentSize * 2;
        boolean check = false;
        while (!check) {
            BigInteger bigInteger = BigInteger.valueOf(tempSize);
            if (check = bigInteger.isProbablePrime((int) Math.log(tempSize)))
                newSize = tempSize;
            else
                tempSize++;
        }
        return newSize;
    }


    public static void main(String[] args) {
        DoubleHashTable<Integer, String> hashTable = new DoubleHashTable<>();

        System.out.println(hashTable.sizeTable(101));
    }

}
