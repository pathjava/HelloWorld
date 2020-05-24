// Oleg Kiselev
// 17.05.2020, 19:06

package ru.progwards.java2.lessons.basetypes;

import java.math.BigInteger;
import java.util.Iterator;

public class DoubleHashTable<K extends HashValue, V> {

    private int size = 0;
    private int sizeTable = 101;
    private ItemHashTable<K, V>[] table;
    private int loadFactor = 75;
    private boolean rebuildComplete = true;

    /* это поля для сбора информации о количестве операций - по названию видно назначение */
    private int countCollision = 0;
    private int countRebuild = 0;
    private int countRemove = 0;
    private int countChange = 0;

    public DoubleHashTable() {
        table = new ItemHashTable[sizeTable];
    }

    public void add(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key is null!");

        if (rebuildComplete && size + 1 >= loadFactor) {
            rebuildTable();
            loadFactor = (int) (table.length * (75.0f / 100.0f));
            countRebuild++;
        }

        ItemHashTable<K, V> newItem = new ItemHashTable<>(key, value);
        newItem.hash = key.getHash();
        int index = getIndex(newItem.hash);

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
        int hash = key.getHash();
        int index = getIndex(hash);
        ItemHashTable<K, V> currentItem = table[index];

        while (currentItem != null) {
            if (currentItem.hash == hash)
                if (currentItem.key.equals(key))
                    return currentItem.value;
            currentItem = currentItem.next;
        }
        return null;
    }

    public void remove(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key is null!");
        int hash = key.getHash();
        int index = getIndex(hash);
        ItemHashTable<K, V> tempItem = null;
        ItemHashTable<K, V> currentItem = table[index];

        while (currentItem != null) {
            if (currentItem.hash == hash)
                if (currentItem.key.equals(key)) {
                    if (tempItem == null) {
                        currentItem = currentItem.getNext();
                        table[index] = currentItem;
                    } else
                        tempItem.setNext(currentItem.getNext());

                    size--;
                    countRemove++;
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

        countChange++;
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

    private void rebuildTable() {
        rebuildComplete = false;
        size = 0;
        ItemHashTable<K, V>[] tempTable = table;
        table = new ItemHashTable[sizeTable(sizeTable)];
        int i = 0;
        ItemHashTable<K, V> current;

        while (i < tempTable.length) {
            if (tempTable[i] != null) {
                current = tempTable[i];
                while (current != null) {
                    add(current.key, current.value);
                    current = current.next;
                }
            }
            i++;
        }
        rebuildComplete = true;
        countCollision = 0;
    }

    public int size() {
        return size;
    }

    public int getIndex(int hash) {
        final double A = 0.61803398875;
        double d = A * hash;
        return (int) (table.length * (d - Math.floor(d)));
    }

    public static class ItemHashTable<K, V> {
        private final K key;
        private V value;
        private int hash;
        private ItemHashTable<K, V> next;

        public ItemHashTable(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
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

        /* данный toString переопределен только для отладки в ходе написания кода */
        @Override
        public String toString() {
            return "key=" + key + ", value=" + value;
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

    public int realSizeTable() {
        int count = 0;
        for (ItemHashTable<K, V> kvItemHashTable : table) {
            if (kvItemHashTable != null)
                count++;
        }
        return count;
    }


    public static void main(String[] args) {
        /* IntegerHashValue, String */
//        DoubleHashTable<IntegerHashValue, String> hashTable = new DoubleHashTable<>();

//        hashTable.add(new IntegerHashValue(321), "value1");
//        hashTable.add(new IntegerHashValue(321), "valueNew1");
//        hashTable.add(new IntegerHashValue(120), "value2");
//        hashTable.add(new IntegerHashValue(225), "value3");
//        hashTable.add(new IntegerHashValue(722), "value4");
//        hashTable.add(new IntegerHashValue(327), "value5");
//        hashTable.add(new IntegerHashValue(286), "value6");
//        hashTable.add(new IntegerHashValue(553), "value7");
//        hashTable.add(new IntegerHashValue(225), "valueNew3");

//        int min = 100;
//        int max = 10000;
//        for (int i = 0; i < 10000; i++) {
//            int randomNumOne = min + (int) (Math.random() * ((max - min) + 1));
//            int randomNumTwo = min + (int) (Math.random() * ((max - min) + 1));
//            hashTable.add(new IntegerHashValue(i), "value" + randomNumTwo);
////            hashTable.add(new IntegerHashValue(randomNumOne + randomNumTwo), "value" + randomNumTwo);
//        }

//        hashTable.remove(new IntegerHashValue(984));

//        hashTable.change(new IntegerHashValue(120), new IntegerHashValue(286));

//        for (Iterator<ItemHashTable<IntegerHashValue, String>> it = hashTable.getIterator(); it.hasNext(); ) {
//            ItemHashTable<IntegerHashValue, String> temp = it.next();
//            System.out.println(temp.key + " : " + temp.value);
//        }

//        System.out.println("\n--------------------------------\n");

//        System.out.println(hashTable.get(new IntegerHashValue(722)));

        /* StringHashValue, String */
        DoubleHashTable<StringHashValue, String> hashTable = new DoubleHashTable<>();

//        hashTable.add(new StringHashValue("value1"), "Vvalue1");
//        hashTable.add(new StringHashValue("value1"), "VvalueNew1");
//        hashTable.add(new StringHashValue("ключ2"), "Vvalue2");
//        hashTable.add(new StringHashValue("value3"), "Vvalue3");
//        hashTable.add(new StringHashValue("value4"), "Vvalue4");
//        hashTable.add(new StringHashValue("ключ тестовый 5"), "Vvalue5");
//        hashTable.add(new StringHashValue("value6"), "Vvalue6");
//        hashTable.add(new StringHashValue("value проверка длины ключа 7"), "Vvalue7");
//        hashTable.add(new StringHashValue("value3"), "VvalueNew3");

        int min = 100;
        int max = 100000;
        for (int i = 0; i < 100000; i++) {
            int randomNumOne = min + (int) (Math.random() * ((max - min) + 1));
            int randomNumTwo = min + (int) (Math.random() * ((max - min) + 1));
//            hashTable.add(new StringHashValue("Хэш-функции" + randomNumOne + " для строк" + randomNumTwo), "value" + randomNumOne);
            hashTable.add(new StringHashValue("Хэш-функции для строк" + i), "value" + randomNumOne);
        }

//        hashTable.remove(new StringHashValue("value7"));
//
//        hashTable.change(new StringHashValue("value2"), new StringHashValue("value6"));

        for (Iterator<ItemHashTable<StringHashValue, String>> it = hashTable.getIterator(); it.hasNext(); ) {
            ItemHashTable<StringHashValue, String> temp = it.next();
            System.out.println(temp.key + " : " + temp.value);
        }

        System.out.println("\n--------------------------------\n");

        System.out.println(hashTable.get(new StringHashValue("value1")));

        System.out.println("\n--------------------------------\n");

        System.out.println("Размер хеш таблицы (количество пар ключ/значение): " + hashTable.size());
        System.out.println("Размер (количество ячеек) в массиве table: " + hashTable.table.length);
        System.out.println("Количество коллизий при последнем перестроении: " + hashTable.countCollision);
        System.out.println("Реальное количество занятых ячеек в массиве table: " + hashTable.realSizeTable());
        System.out.println("Количество перестроений таблицы: " + hashTable.countRebuild);
        System.out.println("Количество удаленных пар: " + hashTable.countRemove);
        System.out.println("Количество измененных значений: " + hashTable.countChange);
    }

}
