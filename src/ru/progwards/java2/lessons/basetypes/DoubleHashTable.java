// Oleg Kiselev
// 17.05.2020, 19:06

package ru.progwards.java2.lessons.basetypes;

import java.math.BigInteger;

public class DoubleHashTable<K, V> implements HashValue {

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
        ItemHashTable<K, V> newItem = new ItemHashTable<>(key, value);
        if (table[getIndex(newItem)] == null)
            addSingle(newItem);
        else
            addCollision(key, value);
    }

    private void addSingle(ItemHashTable<K, V> newItem) {
        int index = getIndex(newItem);
        table[index] = newItem;
        size++;
    }

    private void addCollision(K key, V value) {

    }

    private void copyTable() {
        ItemHashTable<K, V>[] tempTable = table;
        table = new ItemHashTable[sizeTable(sizeTable)];

        for (ItemHashTable<K, V> hashTable : tempTable)
            if (hashTable != null)
                add(hashTable.key, hashTable.value);
    }

    private int getIndex(ItemHashTable<K, V> newItem) {
        return newItem.hashCode() % table.length;
    }

    @Override
    public int getHash() {
        return 0;
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ItemHashTable<?, ?> that = (ItemHashTable<?, ?>) o;

            if (hash != that.hash) return false;
            if (key != null ? !key.equals(that.key) : that.key != null) return false;
            return value != null ? value.equals(that.value) : that.value == null;
        }

        @Override
        public int hashCode() {
            int hashCode = (int) key;
            hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
            hashCode = hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
            hash = hashCode;
            return hashCode;
        }
    }

    private int hashDivInt(K key) {
        return (int) key % table.length;
    }

    private int hashMulInt(K key) {
        double A = 1.61803398875;
        double d = A * (int) key;
        return (int) (table.length * (d - Math.floor(d)));
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

        hashTable.add(321, "value1");
        hashTable.add(20, "value2");
        hashTable.add(553, "value3");
    }

}
