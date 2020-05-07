// Oleg Kiselev
// 07.05.2020, 11:50

package ru.progwards.sever.testprogwards2.test_02;

public class ReverseChars {

    public static String reverseChars(String str){
        return str.isEmpty() ? "" : reverseChars(str.substring(1))+str.charAt(0);
    }

    public static void main(String[] args) {
        System.out.println(reverseChars("54321"));
        System.out.println(reverseChars("кит на море романтик"));
        System.out.println(reverseChars("лилипут сома на мосту пилил"));
        System.out.println(reverseChars("кони топот инок"));
        System.out.println(reverseChars(""));
    }

}
