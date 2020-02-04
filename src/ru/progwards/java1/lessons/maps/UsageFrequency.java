package ru.progwards.java1.lessons.maps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UsageFrequency {

    ArrayList<String > charsList = new ArrayList<>();
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
                }
            }
            System.out.println(wordsList);
            System.out.println(wordsList.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        UsageFrequency test = new UsageFrequency();
        test.processFile("src/ru/progwards/java1/lessons/maps/wiki.test.tokens");
//        System.out.println(test);
    }
}
