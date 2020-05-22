// Oleg Kiselev
// 22.05.2020, 20:14

package ru.progwards.java2.lessons.basetypes;

public class IntegerHashValue implements HashValue {

    public int value;

    public IntegerHashValue(int value) {
        this.value = value;
    }

    @Override
    public int getHash() {
        int hashCode = value;
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        hashCode = hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
        return hashCode;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
