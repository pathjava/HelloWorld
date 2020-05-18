// Oleg Kiselev
// 17.05.2020, 19:06

package ru.progwards.java2.lessons.basetypes;

import java.math.BigInteger;
import java.util.Objects;

public class DoubleHashTable<K, V> {

    private int size = 0;
    private int sizeTable = 101;
    private ItemHashTable<K, V>[] table;
    private int loadFactor = 75;

    public DoubleHashTable() {
        table = new ItemHashTable[sizeTable];
    }

    public void add(K key, V value) {
        if (size + 1 >= loadFactor) {
            copyTable();
            loadFactor = (int) (table.length * (75.0f / 100.0f));
        }
        if (table[getIndex(key)] == null)
            addSingle(key, value);

    }

    private void addSingle(K key, V value) {
        int index = getIndex(key);
        ItemHashTable<K, V> newItem = new ItemHashTable<>(key, value);
        table[index] = newItem;
        size++;
    }

    private void copyTable() {
        ItemHashTable<K, V>[] tempTable = table;
        table = new ItemHashTable[sizeTable(sizeTable)];

        for (ItemHashTable<K, V> hashTable : tempTable)
            if (hashTable != null)
                add(hashTable.key, hashTable.value);
    }

    private int getIndex(K key) {
        int hash = 31 * 17 + key.hashCode();
        return hash % table.length;
    }

    public class ItemHashTable<K, V> {
        private int hash;
        private K key;
        private V value;
        private ItemHashTable<K, V> nextKeyValue;

        public ItemHashTable(K key, V value) {
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

        public ItemHashTable<K, V> getNextKeyValue() {
            return nextKeyValue;
        }

        public void setNextKeyValue(ItemHashTable<K, V> nextKeyValue) {
            this.nextKeyValue = nextKeyValue;
        }

        @Override
        public int hashCode() {
            return hash = 31 * 17 + key.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;

            if (obj instanceof DoubleHashTable.ItemHashTable) {
                ItemHashTable<K, V> temp = (ItemHashTable) obj;
                return (Objects.equals(key, temp.getKey())
                        && Objects.equals(value, temp.getValue()));
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
        sizeTable = newSize;
        return newSize;
    }


    public static void main(String[] args) {
        DoubleHashTable<Integer, String> hashTable = new DoubleHashTable<>();

        System.out.println(hashTable.sizeTable(101));
    }

}
