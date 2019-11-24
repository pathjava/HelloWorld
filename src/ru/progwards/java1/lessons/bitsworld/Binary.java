package ru.progwards.java1.lessons.bitsworld;

public class Binary {
    public byte num;

    public Binary(byte num){
        this.num = num;
    }

    String result = "";

    public String toString(){
        if (num % 2 == 0)
            result = "0";
        else
            result = "1";
        return result;
    }


    public static void main(String[] args) {
        Binary binary = new Binary((byte) 3);
        System.out.println(binary);
    }
}