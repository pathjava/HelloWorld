// Oleg Kiselev
// 17.05.2020, 19:06

package ru.progwards.java2.lessons.basetypes;

import java.math.BigInteger;
import java.util.Objects;

public class DoubleHashTable<K, V> {

    private int size = 0;
    private final MyHashTable<K, V>[] table;
    private float loadFactor;

    public DoubleHashTable() {
        table = new MyHashTable[101];
        loadFactor = table.length * 0.75f;
    }

    private int getHash(K key){
        int hash = 31;
        hash = hash * 17 + key.hashCode();
        return hash % table.length;
    }

    public class MyHashTable<K, V> {
        private int hash;
        private K key;
        private V value;
        private MyHashTable<K, V> nextKeyValue;

        public MyHashTable(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public int getHash() {
            return hashCode() % table.length;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public MyHashTable<K, V> getNextKeyValue() {
            return nextKeyValue;
        }

        public void setNextKeyValue(MyHashTable<K, V> nextKeyValue) {
            this.nextKeyValue = nextKeyValue;
        }

        @Override
        public int hashCode() {
            hash = 31;
            hash = hash * 17 + key.hashCode();
            hash = hash * 17 + value.hashCode();
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;

            if (obj instanceof MyHashTable){
                MyHashTable<K, V> temp = (MyHashTable) obj;
                return (Objects.equals(key, temp.getKey())
                        && Objects.equals(value, temp.getValue())
                        || Objects.equals(hash, temp.hashCode()));
            }
            return false;
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
