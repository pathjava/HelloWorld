package ru.progwards.java1.lessons.maps;

import java.io.FileReader;
import java.util.*;

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
                        if (Character.isLetter(temp) || Character.isDigit(temp)) {
                            for (int j = 0; j < words[i].length(); j++) {
                                char ch = words[i].charAt(j);
                                if (Character.isLetter(ch) || Character.isDigit(ch)) {
                                    charsList.add(ch);
                                }
                            }
                        }
                    }
                }
            }
//            System.out.println(wordsList);
//            System.out.println(wordsList.size());
//            System.out.println();
            System.out.println(charsList);
//            System.out.println();
//            System.out.println(charsList.size());
        } catch(Throwable e){
            throw new RuntimeException(e);
        }
    }

    public Map<Character, Integer> getLetters(){
        HashMap<Character, Integer> countChars = new HashMap<>();
        int count = 1;
        for (int i = 0; i < charsList.size(); i++) {
            Integer oldVal = countChars.putIfAbsent(charsList.get(i), count);
            if (oldVal == null) count++;
        }
        return countChars;
    }

    public Map<String, Integer> getWords(){
        HashMap<String, Integer> countWords = new HashMap<>();
        int count = 1;
        for (int i = 0; i < wordsList.size(); i++) {
            Integer oldVal = countWords.putIfAbsent(wordsList.get(i), count);
            if (oldVal == null) count++;
        }
        return countWords;
    }



    public static void main(String[] args) {
        UsageFrequency test = new UsageFrequency();
        test.processFile("src/ru/progwards/java1/lessons/maps/wiki.test.tokens");
        System.out.println(test.getLetters());
        System.out.println(test.getWords());
    }
}
