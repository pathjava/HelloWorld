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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegerHashValue that = (IntegerHashValue) o;

        return key == that.key;
    }

    @Override
    public String toString() {
        return "" + key;
    }
}
