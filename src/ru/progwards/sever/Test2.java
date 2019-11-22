package ru.progwards.sever;

public class Test2 {

    public static int sumBits(byte value) {
        byte count;
        for (count = 0; value != 0; count++) {
            value &= (value - 1);
        }
        //System.out.println(count);
        return count;
    }

    public static void main(String[] args) {
        System.out.println(sumBits((byte) 0b00100101));
    }

//    public static void main(String[] args) {
//        byte test = (byte) 0b00110011;
//        byte count;
//        for (count=0; test != 0; count++){
//            test &= (test - 1);
//        }
//        System.out.println(count);
//
//    }
}
