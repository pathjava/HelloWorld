// Oleg Kiselev
// 22.05.2020, 20:14

package ru.progwards.java2.lessons.basetypes;

public class IntegerHashValue implements HashValue {

    public int key;

    public IntegerHashValue(int key) {
        this.key = key;
    }

    @Override
    public int getHash() {
        int hashCode = key;
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        hashCode = hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
        return hashCode;
    }

//    @Override
//    public int getHash() {
//        final double A = 0.61803398875;
//        double d = A * key;
//        return (int) (table.length * (d - Math.floor(d)));
//    }

//    private int hashDivInt(K key) {
//        return (int) key % table.length;
//    }

    @Override
    public String toString() {
        return "" + key;
    }
}
