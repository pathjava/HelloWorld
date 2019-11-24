package ru.progwards.java1.lessons.bitsworld;

public class Binary {
    public byte num;

    public Binary(byte num){
        this.num = num;
    }

    String result = "";

    public String toString(){
        String result = "";

        for (int i = Byte.SIZE - 1; i >= 0; i--) {
            result += (num >> i) & 0b00000001;
        }

        return result;

        //данное решение не мое
        //return String.format("%8s", Integer.toBinaryString(num & 0xFF)).replace(' ', '0');
    }


    public static void main(String[] args) {
        Binary binary = new Binary((byte) 23);
        System.out.println(binary);
    }
}