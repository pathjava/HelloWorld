package ru.progwards.java1.lessons.io2;

public class Translator {
    private String[] inLang;
    private String[] outLang;

    public Translator(String[] inLang, String[] outLang) {
        this.inLang = inLang;
        this.outLang = outLang;
    }

    public String translate(String sentence){
        StringBuilder stringBuilders = new StringBuilder();
//        sentence = sentence.toLowerCase();
        String[] tempArr = sentence.split("(?<=\\b|[^\\p{L}])");
        for (int i = 0; i < tempArr.length; i++) {
            for (int j = 0; j < inLang.length; j++) {
                char chTemp = tempArr[i].charAt(0);
                if (tempArr[i].toLowerCase().equals(inLang[j])){
                    tempArr[i] = outLang[j];
                }
                if(Character.isLowerCase(chTemp)){

                } else if (Character.isUpperCase(chTemp)) {

                }
            } stringBuilders.append(tempArr[i]);
        }
        return stringBuilders.toString();
    }

    public static void main(String[] args) {
        Translator test = new Translator(new String[]{"hello", "world", "java"}, new String[]{"привет", "мир", "джава"});
        System.out.println(test.translate("Hello, World! How are you?"));
    }
}

/*
sentence = sentence.toLowerCase();
        for (int i = 0; i < inLang.length; i++) {
            if (sentence.contains(inLang[i])){
                sentence = sentence.replace(inLang[i], outLang[i]);
            }
        }
        return sentence;
*/

/*    public String translate(String sentence){
        String temp = "";
        sentence = sentence.toLowerCase();
        String[] tempArr = sentence.split(" ");
        for (int i = 0; i < tempArr.length; i++) {
            // for each нужен для прохода по всему массиву каждый раз
            for (int j = 0; j < inLang.length; j++) {
                if (tempArr[i].equals(inLang[j])){
                    tempArr[i] = outLang[j];
                }
            }
        }
        return Arrays.toString(tempArr);
    }*/
