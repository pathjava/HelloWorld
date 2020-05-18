// Oleg Kiselev
// 17.05.2020, 19:06

package ru.progwards.java2.lessons.basetypes;

import java.math.BigInteger;

public class DoubleHashTable<K, V> {

    private int size = 101;
    private HashTable<K, V>[] table;

    public DoubleHashTable() {
        table = new HashTable[size];
    }

    private int getHash(int key){
        return key % table.length;
    }

    class HashTable<K, V> {
        private int hash;
        private K key;
        private V value;
        private HashTable<K, V> nextKeyValue;

        public HashTable(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public HashTable<K, V> getNextKeyValue() {
            return nextKeyValue;
        }

        public void setNextKeyValue(HashTable<K, V> nextKeyValue) {
            this.nextKeyValue = nextKeyValue;
        }
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
