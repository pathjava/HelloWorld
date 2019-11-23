package ru.progwards.java1.lessons.bitsworld;

public class SumBits {
    public static int sumBits(byte value) {
    //данный метод долго мучал сам, но была ошибка при старшей 1 - на консультации разобрали причину
    // ошибка была  в нехватке  || value < -1 в условие цикла и определении result 0 или <0
    // данный метод на консультации исправляли с Валерием, но робот не принял данный вариант
//        int result;
//        if (value < 0)
//            result = 1;
//        else
//            result = 0;
//        while (value > 0 || value < -1) {
//            if ((value & 1) == 1) {
//                result++;
//            }
//            value = (byte) (value >> 1);
//        }
//        return result;
//    }
//    public static void main(String[] args) {
//        System.out.println(sumBits((byte) 0b10101110));
//    }

    //данный метод показал Валерий на консультации 23.11.2019
    //данный метод не прошел проверку роботом, хотя на консультации Валерий говорил, что ожидал такого решения
        byte result = 0;
        value >>= 1;
        if ((value & 1) == 1)
            result++;
        value >>= 1;
        if ((value & 1) == 1)
            result++;
        value >>= 1;
        if ((value & 1) == 1)
            result++;
        value >>= 1;
        if ((value & 1) == 1)
            result++;
        value >>= 1;
        if ((value & 1) == 1)
            result++;
        value >>= 1;
        if ((value & 1) == 1)
            result++;
        value >>= 1;
        if ((value & 1) == 1)
            result++;
        return result;
    }
    public static void main(String[] args) {
        System.out.println(sumBits((byte) 0b10101110));
    }

    // данный метод подсмотрел в Гугл
//        byte count;
//        for (count = 0; value != 0; count++) {
//            value &= (value - 1);
//        }
//        return count;
//    }
//    public static void main(String[] args) {
//        System.out.println(sumBits((byte) 0b00110101));
//    }
}
