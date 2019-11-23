package ru.progwards.java1.lessons.bitsworld;

public class Binary {
    public byte num;

    public Binary(byte num){
        this.num = num;
    }

    public String toString(){
        return "нет ответа";
    }

    public static void main(String[] args) {
        Binary binary = new Binary((byte) 123);
        System.out.println(binary);
    }
}
