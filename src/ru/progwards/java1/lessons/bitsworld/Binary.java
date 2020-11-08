package ru.progwards.java1.lessons.bitsworld;

public class Binary {
    public byte num;

    public Binary(byte num) {
        this.num = num;
    }

//    String result = "";

    public String toString() {
        String result = "";

//        for (int i = 0; i < Byte.SIZE; i++) {
//            result = ((num >> i) & 0b00000001) + result;
//        }
        for (int i = Byte.SIZE - 1; i >= 0; i--) {
            result += (num >> i) & 0b00000001;
        }
        //решение выше обрабатывается в компиляторе так:
//        for (int i = Byte.SIZE - 1; i >= 0; i--) {
//            result += ((Integer)((num >> i) & 0b00000001)).toString();
//        }

        // решение без циклов
//        result += (num >> 7) & 0b00000001;
//        result += (num >> 6) & 0b00000001;
//        result += (num >> 5) & 0b00000001;
//        result += (num >> 4) & 0b00000001;
//        result += (num >> 3) & 0b00000001;
//        result += (num >> 2) & 0b00000001;
//        result += (num >> 1) & 0b00000001;
//        result += (num >> 0) & 0b00000001;

        return result;

        //данное решение не мое
        //return String.format("%8s", Integer.toBinaryString(num & 0xFF)).replace(' ', '0');
    }


    public static void main(String[] args) {
        Binary binary = new Binary((byte) 123);
        System.out.println(binary);
//        System.out.println("\"" + binary + "\"");
    }
}