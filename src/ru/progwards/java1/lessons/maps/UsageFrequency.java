package ru.progwards.java1.lessons.maps;

import java.io.FileReader;
import java.util.*;

public class UsageFrequency {
    ArrayList<Character > charsList = new ArrayList<>();
    ArrayList<String> wordsList = new ArrayList<>();

    public void processFile(String fileName){
        try (FileReader fileReader = new FileReader(fileName); Scanner scanner = new Scanner(fileReader)) {
            StringBuilder tempString;
            while (scanner.hasNext()){
                tempString = new StringBuilder();
                String str = scanner.nextLine();
                if (!("".equals(str))) {
                    for (int i = 0; i < str.length(); i++) {
                        char temp = str.charAt(i);
                        if (!(Character.isLetter(temp) || Character.isDigit(temp))){
                            tempString.append(" ");
                        } else
                            tempString.append(temp);
                    }

                    String[] strTemp = tempString.toString().split(" ");
                    for (int i = 0; i < strTemp.length; i++) {
                        if (!strTemp[i].isEmpty()) {
                            char ch = strTemp[i].charAt(0);
                            if (Character.isDigit(ch) && strTemp[i].length() == 1){
                                charsList.add(ch);
                            } else if (Character.isLetter(ch) || Character.isDigit(ch)) {
                                wordsList.add(strTemp[i]);
                            }
                        }
                    }

//                    for (int i = 0; i < wordsList.size(); i++) {
//                        for (int j = 0; j < wordsList.get(i).length(); j++) {
//                            char ch = wordsList.get(i).charAt(j);
//                                charsList.add(ch);
//                        }
//                    }
                }
            }
            for (int i = 0; i < wordsList.size(); i++) {
                for (int j = 0; j < wordsList.get(i).length(); j++) {
                    char ch = wordsList.get(i).charAt(j);
                    charsList.add(ch);
                }
            }
//            System.out.println(wordsList);
//            System.out.println(wordsList.size());
//            System.out.println();
//            System.out.println(charsList);
//            System.out.println();
//            System.out.println(charsList.size());
        } catch(Throwable e){
            throw new RuntimeException(e);
        }
    }

    public Map<Character, Integer> getLetters(){
        TreeMap<Character, Integer> countChars = new TreeMap<>();
        int count = 1;
        for (int i = 0; i < charsList.size(); i++) {
            if (countChars.containsKey(charsList.get(i))){
                Integer value = countChars.get(charsList.get(i));
                countChars.put(charsList.get(i), (value + 1));
            } else
                countChars.put(charsList.get(i), count);
        }

        return countChars;
    }

    public Map<String, Integer> getWords(){
        TreeMap<String, Integer> countWords = new TreeMap<>();
        int count = 1;
        for (int i = 0; i < wordsList.size(); i++) {
            if (countWords.containsKey(wordsList.get(i))){
                Integer value = countWords.get(wordsList.get(i));
                countWords.put(wordsList.get(i), (value + 1));
            } else
                countWords.put(wordsList.get(i), count);
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
