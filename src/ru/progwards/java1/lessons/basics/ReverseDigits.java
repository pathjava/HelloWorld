package ru.progwards.java1.lessons.basics;

public class ReverseDigits {
    public static void main(String[] args) {
        int number = 135;
        System.out.println(reverseDigits(number));
    }

    public static int reverseDigits(int number){
        int reverse = 0;
        while (number != 0) {
            reverse = reverse * 10 + (number % 10);
            number = number / 10;
        }
        return reverse;
    }
}
