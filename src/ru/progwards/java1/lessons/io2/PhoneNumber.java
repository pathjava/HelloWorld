package ru.progwards.java1.lessons.io2;


public class PhoneNumber {
    public static String format(String phone){
        String formatNumber = "";
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : phone.toCharArray()){
            if (Character.isDigit(c)){
                stringBuilder.append(c);
            }
            formatNumber = stringBuilder.toString();
        }
        if (formatNumber.length() >= 11){
            formatNumber = (formatNumber.substring(formatNumber.length() - 10));
        }
        StringBuilder stringBuilder1 = new StringBuilder(formatNumber);
        stringBuilder1.insert(0, "+7");
        stringBuilder1.insert(2, "(");
        stringBuilder1.insert(6, ")");
        stringBuilder1.insert(10, "-");
        formatNumber = stringBuilder1.toString();

        return  formatNumber;
    }

    public static void main(String[] args) {
        System.out.println(format("911)552-04-85"));
    }
}
