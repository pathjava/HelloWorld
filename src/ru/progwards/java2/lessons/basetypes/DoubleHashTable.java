// Oleg Kiselev
// 17.05.2020, 19:06

package ru.progwards.java2.lessons.basetypes;

import java.math.BigInteger;
import java.util.Iterator;

public class DoubleHashTable<K, V> {

    private int size = 0;
    private int sizeTable = 5;
    private ItemHashTable<K, V>[] table;
    private int loadFactor = 75;

    public DoubleHashTable() {
        table = new ItemHashTable[sizeTable];
    }

    public void add(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key is null!");
//        if (size + 1 >= loadFactor) {
//            copyTable();
//            loadFactor = (int) (table.length * (75.0f / 100.0f));
//        }
        ItemHashTable<K, V> newItem = new ItemHashTable<>(key, value, null);
        int index = getIndex(newItem);
        if (table[index] == null)
            addSingle(newItem, index);
        else
            addChains(newItem, index);
    }

    private void addSingle(ItemHashTable<K, V> newItem, int index) {
        table[index] = newItem;
        size++;
    }

    private void addChains(ItemHashTable<K, V> newItem, int index) {
        ItemHashTable<K, V> currentItem = table[index];

        while (currentItem.next != null) {
            if (currentItem.hash == newItem.hash)
                if (currentItem.key.equals(newItem.key)) {
                    currentItem.value = newItem.value;
                    return;
                }
            currentItem = currentItem.next;
        }

        if (currentItem.hash == newItem.hash) {
            if (currentItem.key.equals(newItem.key))
                currentItem.value = newItem.value;
        } else {
            currentItem.next = newItem;
            size++;
        }
    }

    public V get(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key is null!");
        int hash = hash(key);
        int index = hash % table.length;
        ItemHashTable<K, V> currentItem = table[index];

        while (currentItem != null) {
            if (currentItem.hash == hash)
                if (currentItem.key.equals(key)) {
                    return currentItem.value;
                }
            currentItem = currentItem.next;
        }
        return null;
    }

    public void remove(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key is null!");
        int hash = hash(key);
        int index = hash % table.length;
        ItemHashTable<K, V> tempItem = null;
        ItemHashTable<K, V> currentItem = table[index];

        while (currentItem != null) {
            if (currentItem.hash == hash)
                if (currentItem.key.equals(key)) {
                    if (tempItem == null) {
                        currentItem = currentItem.getNext();
                        table[index] = currentItem;
                    } else {
                        tempItem.setNext(currentItem.getNext());
                    }
                    size--;
                    return;
                }
            tempItem = currentItem;
            currentItem = currentItem.getNext();
        }
    }

    public void change(K keyOne, K keyTwo) {
        if (keyOne == null || keyTwo == null)
            throw new IllegalArgumentException("Key is null!");
        V tempValue = get(keyTwo);
        remove(keyTwo);

        add(keyOne, tempValue);
    }

    public Iterator<DoubleHashTable<K,V>> getIterator(){
        return new Iterator<DoubleHashTable<K, V>>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public DoubleHashTable<K, V> next() {
                return null;
            }
        };
    }

    private void copyTable() {
        ItemHashTable<K, V>[] tempTable = table;
        table = new ItemHashTable[sizeTable(sizeTable)];

        for (ItemHashTable<K, V> hashTable : tempTable)
            if (hashTable != null)
                add(hashTable.key, hashTable.value);
    }

    private int getIndex(ItemHashTable<K, V> newItem) {
        return newItem.hash % table.length;
    }

    public int size() {
        return size;
    }

    public class ItemHashTable<K, V> {
        private K key;
        private V value;
        private int hash;
        private ItemHashTable<K, V> next;

        public ItemHashTable(K key, V value, ItemHashTable<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hashCode();
            this.next = next;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public int getHash() {
            return hashCode();
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public ItemHashTable<K, V> getNext() {
            return next;
        }

        public void setNext(ItemHashTable<K, V> next) {
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ItemHashTable<?, ?> that = (ItemHashTable<?, ?>) o;

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

    public int hash(K key) {
        int hashCode = (int) key;
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        hashCode = hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
        return hashCode;
    }

//    private int hashDivInt(K key) {
//        return (int) key % table.length;
//    }
//
//    private int hashMulInt(K key) {
//        double A = 1.61803398875;
//        double d = A * (int) key;
//        return (int) (table.length * (d - Math.floor(d)));
//    }

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
        hashTable.add(321, "valueNew1");
        hashTable.add(120, "value2");
        hashTable.add(225, "value3");
        hashTable.add(722, "value4");
        hashTable.add(327, "value5");
        hashTable.add(286, "value6");
        hashTable.add(553, "value7");
        hashTable.add(225, "valueNew3");

        System.out.println(hashTable.get(553));

        hashTable.remove(553);

        hashTable.change(120, 286);

        System.out.println(hashTable.size());
    }

}
