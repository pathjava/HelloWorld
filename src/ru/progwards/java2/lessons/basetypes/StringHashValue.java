// Oleg Kiselev
// 22.05.2020, 20:14

package ru.progwards.java2.lessons.basetypes;

import java.util.zip.CRC32;

public class StringHashValue implements HashValue {

    public String key;

    public StringHashValue(String key) {
        this.key = key;
    }

    /* данный метод хеширования показал меньше всего коллизий */
    @Override
    public int getHash() {
        /* SDBM — специальный алгоритм, используемый в проекте SDBM' */
        long hash = 0;

        for (int i = 0; i < key.length(); i++) {
            hash = key.charAt(i) + (hash << 6) + (hash << 16) - hash;
        }
        return unsignedInt(hash);
    }

    private static final int INT_MAX = Integer.MAX_VALUE;

    private static int unsignedInt(long hash) {
        return (int) (hash % INT_MAX);
    }

    /* данные методы хеширования также рабочие */
//    @Override
//    public int getHash() {
//        /* CRC32 — стандартная контрольная сумма с полиномом */
//        String[] str = key.split("");
//        CRC32 crc = new CRC32();
//        for (String string : str) {
//            crc.update(string.getBytes());
//        }
//        return (int) (crc.getValue());
//    }

//    @Override
//    public int getHash() {
//        /* rs — простая хэш-функция из книги Роберта Седжвика 'Фундаментальные алгоритмы на C' */
//        long b = 378551;
//        long a = 63689;
//        long hash = 0;
//        for (int i = 0; i < key.length(); i++) {
//            hash = unsignedInt(hash * a + key.charAt(i));
//            a = unsignedInt(a * b);
//        }
//        return (int) hash;
//    }
//
//    static final long UINT_MAX = 4294967295L;
//
//    static long unsignedInt(long num) {
//        return num % UINT_MAX;
//    }

//    @Override
//    public int getHash() {
//        /* BKDR — хэш-функция из книги Брайана Кернигана и Денниса Ритчи 'Язык программирования C' */
//        long seed = 131;
//        long hash = 0;
//
//        for (int i = 0; i < key.length(); i++) {
//            hash = unsignedLong((hash * seed) + key.charAt(i));
//        }
//        return unsignedInt(hash);
//    }
//
//    private static final long LONG_MAX = Long.MAX_VALUE;
//
//    private static long unsignedLong(long hash) {
//        return hash % LONG_MAX;
//    }
//
//    private static final int INT_MAX = Integer.MAX_VALUE;
//
//    private static int unsignedInt(long hash) {
//        return (int) (hash % INT_MAX);
//    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringHashValue that = (StringHashValue) o;

        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public String toString() {
        return "" + key;
    }
}
