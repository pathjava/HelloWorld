package ru.progwards.java1.lessons.bitsworld;

public class SumBits {
    public static int sumBits(byte value) {
        //данный метод долго мучал сам, но была ошибка при старшей 1 - на консультации разобрали причину
        // ошибка была  в нехватке  || value < -1 в условие цикла
        int result = 0;

        while (value > 0 || value < -1) {
            if (value > 0 && (value & 1) == 1 ||
                    value < 0 && (value & 1) == 0)
                result++;
            value = (byte) (value >> 1);
        }
        if (value < 0)
            result = 8 - result;
        return result;


        //данный метод показал Валерий на консультации 23.11.2019
//        int result = 0;
//        if ((value & 1) == 1)
//            result++;
//        value >>= 1;
//        if ((value & 1) == 1)
//            result++;
//        value >>= 1;
//        if ((value & 1) == 1)
//            result++;
//        value >>= 1;
//        if ((value & 1) == 1)
//            result++;
//        value >>= 1;
//        if ((value & 1) == 1)
//            result++;
//        value >>= 1;
//        if ((value & 1) == 1)
//            result++;
//        value >>= 1;
//        if ((value & 1) == 1)
//            result++;
//        value >>= 1;
//        if ((value & 1) == 1)
//            result++;
//        return result;


        // данный метод подсмотрел в Гугл
//        byte count;
//        for (count = 0; value != 0; count++) {
//            value &= (value - 1);
//        }
//        return count;


    }

    public static void main(String[] args) {
        System.out.println(sumBits((byte) 0b0100101));
    }
}
