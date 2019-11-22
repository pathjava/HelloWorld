package ru.progwards.java1.lessons.bitsworld;

public class SumBits {
    public static int sumBits(byte value) {
        byte count;
        for (count = 0; value != 0; count++) {
            value &= (value - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(sumBits((byte) 0b00110101));

//        int res = 177;
//        System.out.println("Number of one bits = " + Integer.bitCount(res));
    }
}
