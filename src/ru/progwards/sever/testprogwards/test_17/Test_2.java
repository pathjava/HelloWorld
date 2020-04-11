package ru.progwards.sever.testprogwards.test_17;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Test_2 {
    static String swapWords(String sentance) {
        StringBuilder stringResult = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(sentance, " \n.,-!");
        String[] arr = new String[tokenizer.countTokens()];
        while (tokenizer.hasMoreTokens()) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = tokenizer.nextToken();
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j += 2) {
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                stringResult.append(arr[i]).append(" ").append(arr[j]).append(" ");
                i += 2;
            }
            if ((arr.length % 2) != 0) {
                stringResult.append(arr[arr.length - 1]);
            }
        }
        return stringResult.toString().trim();
    }

    static String swapWordsThree(String sentance) {
        StringBuilder stringResult = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(sentance, " \n.,-!");
        String[] arr = new String[tokenizer.countTokens()];
        while (tokenizer.hasMoreTokens()) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = tokenizer.nextToken();
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k += 3) {
                    String one = arr[k];
                    String two = arr[i];
                    arr[k] = arr[j];
                    arr[i] = one;
                    arr[j] = two;
                    stringResult.append(arr[i]).append(" ").append(arr[j]).append(" ").append(arr[k]).append(" ");
                    j += 3;
                    i += 3;
                }
            }
        }
        if ((arr.length % 3) == 2) {
            stringResult.append(arr[arr.length - 1]).append(" ").append(arr[arr.length - 2]);
        } else if ((arr.length % 3) == 1) {
            stringResult.append(arr[arr.length - 1]);
        }
        return stringResult.toString();
    }


    public static void main(String[] args) {
        List<String> testList = new ArrayList<>(List.of("Тех слов, где есть хоть капля яда\n" + "И в шутку говорить не надо.\n" + "(c) Абу Шукур Балхи",
                "Слово - серебро, молчание - золото!",
                "Убитых словом, добивают молчанием. (c) Уильям Шекспир."));

        for (String s : testList) {
            System.out.println(swapWords(s));
        }
        System.out.println("--------------------------------");
        for (String s : testList) {
            System.out.println(swapWordsThree(s));
        }

        String txt =
                "StringTokenizer - этот класс, " +
                        "нам строку разобьёт на раз.";
        StringTokenizer tokenizer = new StringTokenizer(txt, " .,");
        while (tokenizer.hasMoreTokens())
            System.out.print(tokenizer.nextToken());
        /* StringTokenizer-этоткласснамстрокуразобьётнараз */
    }
}
