package ru.progwards.sever.testprogwards.test_17;

import java.util.StringTokenizer;

public class Test_2 {
    static String swapWords(String sentance) {
        StringBuilder result2 = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(sentance, " \n.,-!");
        String[] result = new String[tokenizer.countTokens()];
        while (tokenizer.hasMoreTokens()) {
            for (int i = 0; i < result.length; i++) {
                result[i] = tokenizer.nextToken();
            }
        }
        for (int i = 0; i < result.length; i++) {
            for (int j = i + 1; j < result.length; j += 2) {
                String temp = result[i];
                result[i] = result[j];
                result[j] = temp;
                result2.append(result[i]).append(" ").append(result[j]).append(" ");
                i += 2;
            }
            if ((result.length % 2) != 0) {
                result2.append(result[result.length - 1]).append(" ");
            }
        }
        return result2.toString();
    }


    public static void main(String[] args) {
//        System.out.println(swapWords("Слово - серебро, молчание - золото!"));
//        System.out.println(swapWords("Убитых словом, добивают молчанием. (c) Уильям Шекспир."));
        System.out.println(swapWords("Тех слов, где есть хоть капля яда\n" + "И в шутку говорить не надо.\n" + "(c) Абу Шукур Балхи"));

//        String txt =
//                "StringTokenizer - этот класс, " +
//                        "нам строку разобьёт на раз.";
//        StringTokenizer tokenizer = new StringTokenizer(txt, " .,");
//        while (tokenizer.hasMoreTokens())
//            System.out.print(tokenizer.nextToken());
        /* StringTokenizer-этоткласснамстрокуразобьётнараз */
    }
}
