package ru.progwards.java1.lessons.bitsworld;

public class CheckBit {
    public static int checkBit(byte value, int bitNumber) {
        //реализовано при условии проверки значениями bitNumber от 0 до 7
        //если надо проверять значениями от 1 до 8, то вместо bitNumber используем --bitNumber
        int result;
        value >>= bitNumber;
        if ((value & 1) == 1)
            result = 1;
        else
            result = 0;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(checkBit((byte) 0b01000101, 3));
    }
}
