// Oleg Kiselev
// 22.05.2020, 20:14

package ru.progwards.java2.lessons.basetypes;

import java.math.BigDecimal;

public class StringHashValue implements HashValue {

    public String key;

    public StringHashValue(String key) {
        this.key = key;
    }

    @Override
    public int getHash() {
        /* BKDR — хэш-функция из книги Брайана Кернигана и Денниса Ритчи 'Язык программирования C' */
        long seed = 131;
        long hash = 0;

        for (int i = 0; i < key.length(); i++) {
            hash = unsignedLong((hash * seed) + key.charAt(i));
        }

        return unsignedInt(hash);
    }

    static final long LONG_MAX = Long.MAX_VALUE;

    static long unsignedLong(long hash) {
        return hash % LONG_MAX;
    }

    static final int INT_MAX = Integer.MAX_VALUE;

    static int unsignedInt(long hash) {
        return (int) (hash % INT_MAX);
    }

//    @Override
//    public int getHash() {
//        /* PJW - алгоритм, основанный на работе Peter J. Weinberger */
//        int hashCode;
//        String str = key;
//        long BitsInUnsignedInt = 4 * 8;
//        long ThreeQuarters = (BitsInUnsignedInt * 3) / 4;
//        long OneEighth = BitsInUnsignedInt / 8;
//        long HighBits = (long) (0xFFFFFFFF) << (BitsInUnsignedInt - OneEighth);
//        long hash = 0;
//        long test;
//
//        for (int i = 0; i < str.length(); i++) {
//            hash = (hash << OneEighth) + str.charAt(i);
//
//            if ((test = hash & HighBits) != 0) {
//                hash = ((hash ^ (test >> ThreeQuarters)) & (~HighBits));
//            }
//        }
//        hashCode = (int) hash;
//        return hashCode;
//    }

    @Override
    public String toString() {
        return "" + key;
    }
}
