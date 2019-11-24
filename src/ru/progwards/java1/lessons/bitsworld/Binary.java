package ru.progwards.java1.lessons.bitsworld;

public class Binary {
    public byte num;

    public Binary(byte num){
        this.num = num;
    }

    String result = "";

    public String toString(){
//        if (num % 2 == 0)
//            result = "0";
//        else
//            result = "1";
//        return result;

        return String.format("%8s", Integer.toBinaryString(num & 0xFF)).replace(' ', '0');
    }


    public static void main(String[] args) {
        Binary binary = new Binary((byte) -23);
        System.out.println(binary);
    }
}