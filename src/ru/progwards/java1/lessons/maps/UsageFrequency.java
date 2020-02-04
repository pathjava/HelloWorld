package ru.progwards.java1.lessons.maps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UsageFrequency {

    ArrayList<Character > charsList = new ArrayList<>();
    ArrayList<String> wordsList = new ArrayList<>();

    public void processFile(String fileName){
        try (FileReader fileReader = new FileReader(fileName); Scanner scanner = new Scanner(fileReader)) {
            while (scanner.hasNext()){
                String str = scanner.nextLine();
                if (!("".equals(str))) {
                    String[] words = str.split("(?<=\\b|[^\\p{L}])");
                    for (int i = 0; i < words.length; i++) {
                        char temp = words[i].charAt(0);
                        if (Character.isLetter(temp)){
                            wordsList.add(words[i]);
                        }
                    }
                    for (int i = 0; i < words.length; i++) {
                        char temp = words[i].charAt(0);
                        if (Character.isAlphabetic(temp)) {
                            for (int j = 0; j < words[i].length(); j++) {
                                char ch = words[i].charAt(j);
                                if (Character.isLetter(ch)) {
                                    charsList.add(ch);
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(wordsList);
            System.out.println(wordsList.size());
            System.out.println();
            System.out.println(charsList);
            System.out.println(charsList.size());
        } catch(Throwable e){
            throw new RuntimeException(e);
        }
    }





    public static void main(String[] args) {
        UsageFrequency test = new UsageFrequency();
        test.processFile("src/ru/progwards/java1/lessons/maps/wiki.test.tokens");
//        System.out.println(test);
    }
}
