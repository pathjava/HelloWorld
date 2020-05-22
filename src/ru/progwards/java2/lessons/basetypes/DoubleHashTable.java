// Oleg Kiselev
// 17.05.2020, 19:06

package ru.progwards.java2.lessons.basetypes;

import java.math.BigInteger;
import java.util.Iterator;

public class DoubleHashTable<K, V> {

    private int size = 0;
    private int countCollision = 0;
    private int sizeTable = 1000;
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
        newItem.hash = hash(key);
        int index = newItem.hash % table.length;
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
            countCollision++;
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

    public Iterator<ItemHashTable<K, V>> getIterator() {
        return new Iterator<>() {
            private int i = 0;
            private ItemHashTable<K, V> current = table[i];

            private void getCurrent() {
                while (i < table.length) {
                    if (table[i] != null) {
                        current = table[i];
                        break;
                    }
                    i++;
                }
            }

            @Override
            public boolean hasNext() {
                if (current == null)
                    getCurrent();
                return current != null;
            }

            @Override
            public ItemHashTable<K, V> next() {
                ItemHashTable<K, V> result = current;
                current = current.next;
                if (current == null)
                    i++;
                result.next = null; // null присваиваю, чтобы из итератора уходил только данный узел, а не цепочка
                return result;
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

//    private int getIndex(ItemHashTable<K, V> newItem) {
//        return newItem.hash % table.length;
//    }

    public int size() {
        return size;
    }

    public static class ItemHashTable<K, V> {
        private K key;
        private V value;
        private int hash;
        private ItemHashTable<K, V> next;

        public ItemHashTable(K key, V value, ItemHashTable<K, V> next) {
            this.key = key;
            this.value = value;
//            this.hash = hashCode();
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

//        @Override
//        public int hashCode() {
//            int hashCode;
//            if (key instanceof String) {
//                /* PJW - алгоритм, основанный на работе Peter J. Weinberger */
//                String str = (String) key;
//                long BitsInUnsignedInt = 4 * 8;
//                long ThreeQuarters = (BitsInUnsignedInt * 3) / 4;
//                long OneEighth = BitsInUnsignedInt / 8;
//                long HighBits = (long) (0xFFFFFFFF) << (BitsInUnsignedInt - OneEighth);
//                long hash = 0;
//                long test;
//
//                for (int i = 0; i < str.length(); i++) {
//                    hash = (hash << OneEighth) + str.charAt(i);
//
//                    if ((test = hash & HighBits) != 0) {
//                        hash = ((hash ^ (test >> ThreeQuarters)) & (~HighBits));
//                    }
//                }
//                hashCode = (int) hash;
//            } else {
//                hashCode = (int) key;
//                hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
//                hashCode = hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
//            }
//            hash = hashCode;
//            return hashCode;
//        }

        @Override
        public String toString() {
            return "key=" + key + ", value=" + value;
        }
    }

    public int hash(K key) {
        int hashCode;
        if (key instanceof String) {
            String str = (String) key;
            long BitsInUnsignedInt = 4 * 8;
            long ThreeQuarters = (BitsInUnsignedInt * 3) / 4;
            long OneEighth = BitsInUnsignedInt / 8;
            long HighBits = (long) (0xFFFFFFFF) << (BitsInUnsignedInt - OneEighth);
            long hash = 0;
            long test;

            for (int i = 0; i < str.length(); i++) {
                hash = (hash << OneEighth) + str.charAt(i);

                if ((test = hash & HighBits) != 0) {
                    hash = ((hash ^ (test >> ThreeQuarters)) & (~HighBits));
                }
            }
            hashCode = (int) hash;
        } else {
            hashCode = (int) key;
            hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
            hashCode = hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
        }
        return hashCode;
    }

//    private int hashDivInt(K key) {
//        return (int) key % table.length;
//    }

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

    public int realSizeTable() {
        int count = 0;
        for (ItemHashTable<K, V> kvItemHashTable : table) {
            if (kvItemHashTable != null)
                count++;
        }
        return count;
    }


    public static void main(String[] args) {
        /* Integer, String */
//        DoubleHashTable<Integer, String> hashTable = new DoubleHashTable<>();
//
//        hashTable.add(321, "value1");
//        hashTable.add(321, "valueNew1");
//        hashTable.add(120, "value2");
//        hashTable.add(225, "value3");
//        hashTable.add(722, "value4");
//        hashTable.add(327, "value5");
//        hashTable.add(286, "value6");
//        hashTable.add(553, "value7");
//        hashTable.add(225, "valueNew3");
//
//        int min = 100;
//        int max = 1000;
//        for (int i = 0; i < 1000; i++) {
//            int randomNum = min + (int)(Math.random() * ((max - min) + 1));
//            hashTable.add(randomNum, "value" + randomNum);
//        }
//
//        System.out.println(hashTable.get(722));
//
//        hashTable.remove(553);
//
//        hashTable.change(120, 286);
//
//        for (Iterator<ItemHashTable<Integer, String>> it = hashTable.getIterator(); it.hasNext(); ) {
//            ItemHashTable<Integer, String> temp = it.next();
//            System.out.println(temp.key + " : " + temp.value);
//        }

        /* String, String */
        DoubleHashTable<String, String> hashTable = new DoubleHashTable<>();

//        hashTable.add("value1", "Vvalue1");
//        hashTable.add("value1", "VvalueNew1");
//        hashTable.add("ключ2", "Vvalue2");
//        hashTable.add("value3", "Vvalue3");
//        hashTable.add("value4", "Vvalue4");
//        hashTable.add("ключ тестовый 5", "Vvalue5");
//        hashTable.add("value6", "Vvalue6");
//        hashTable.add("value проверка длины ключа 7", "Vvalue7");
//        hashTable.add("value3", "VvalueNew3");

        int min = 100;
        int max = 1000;
        for (int i = 0; i < 1000; i++) {
            int randomNum = min + (int) (Math.random() * ((max - min) + 1));
            hashTable.add("key" + randomNum, "value" + randomNum);
        }

//        System.out.println(hashTable.get("value1"));

//        hashTable.remove("value7");
//
//        hashTable.change("value2", "value6");

        for (Iterator<ItemHashTable<String, String>> it = hashTable.getIterator(); it.hasNext(); ) {
            ItemHashTable<String, String> temp = it.next();
            System.out.println(temp.key + " : " + temp.value);
        }

        System.out.println("Размер таблицы: " + hashTable.size());
        System.out.println("Количество коллизий: " + hashTable.countCollision);
        System.out.println("Реальное количество занятых ячеек в массиве table: " + hashTable.realSizeTable());
    }

}
