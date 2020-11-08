package ru.progwards.sever.testprogwards.test_10;

import java.util.Arrays;

public class Test_03 {
    String invertWords(String sentence) {
        StringBuilder strResult = new StringBuilder();
        String[] wordsArr = sentence.split(" ");
        for (int i = wordsArr.length - 1; i >= 0; i--) {
            strResult.append(wordsArr[i]);
            if (i > 0) strResult.append('.');
        }
        return strResult.toString();
    }

//    String invertWords(String sentence){
//        String strResult = "";
//        String[] wordsArr = sentence.split(" ");
//        for (int i = wordsArr.length - 1; i >= 0; i--){
//            strResult += (wordsArr[i] + ".");
//        }
//        return strResult;
//    }

    public static void main(String[] args) {
        Test_03 test = new Test_03();
        System.out.println(test.invertWords("Буря мглою небо кроет"));
    }
}
