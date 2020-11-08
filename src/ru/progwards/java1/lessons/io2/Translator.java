package ru.progwards.java1.lessons.io2;

public class Translator {
    private String[] inLang;
    private String[] outLang;

    public Translator(String[] inLang, String[] outLang) {
        this.inLang = inLang;
        this.outLang = outLang;
    }

    public String translate(String sentence) {
        StringBuilder stringBuilders = new StringBuilder();
        /*разбиваем предложение по словам, пробелам и знакам препинания и размещаем в отдельные ячейки массива*/
        String[] tempArr = sentence.split("(?<=\\b|[^\\p{L}])");
        for (int i = 0; i < tempArr.length; i++) {
            /*проверяем, является ли символ под индексом 0 буквой и если да, запускаем проверку*/
            char temp = tempArr[i].charAt(0);
            if (Character.isAlphabetic(temp)) {
                for (int j = 0; j < inLang.length; j++) {
                    /*проверяем первый символ каждого слова из строки sentence - Заглавный или строчный*/
                    if (tempArr[i].toLowerCase().equals(inLang[j])) {
                        char chTemp = tempArr[i].charAt(0);
                        /*если Заглавный, то в методе FirstUpperCase делаем первую букву слова из outLang[j] Заглавной*/
                        tempArr[i] = Character.isUpperCase(chTemp) ? FirstUpperCase(outLang[j]) : outLang[j];
                    }
                }
            }
            stringBuilders.append(tempArr[i]);
        }
        return stringBuilders.toString();
    }

    public static String FirstUpperCase(String outLang) {
        return outLang.substring(0, 1).toUpperCase() + outLang.substring(1);
    }

    public static void main(String[] args) {
        Translator test = new Translator(new String[]{"hello", "world", "java", "saint", "petersburg"},
                new String[]{"привет", "мир", "джава", "санкт", "петербург"});
        System.out.println(test.translate("Hello, World! How are you? A'm live in Saint-Petersburg."));
    }
}

/*
Данное решение было первым, но оно работает только при условии,
что вся входящая строка в нижнем регистре (строчные). В этом решение
нет возможности проверять отдельно взятое слово из входящей строки
и проверять на то, какая первая буква - Заглавная или строчная

sentence = sentence.toLowerCase();
        for (int i = 0; i < inLang.length; i++) {
            if (sentence.contains(inLang[i])){
                sentence = sentence.replace(inLang[i], outLang[i]);
            }
        }
        return sentence;
*/

