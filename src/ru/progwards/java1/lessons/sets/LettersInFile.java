package ru.progwards.java1.lessons.sets;

import java.io.FileReader;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class LettersInFile {
    public static String process(String fileName) {
        Set<Character> latinUpper = new TreeSet<>();
        Set<Character> latinLower = new TreeSet<>();
        Set<Character> cyrillicUpper = new TreeSet<>();
        Set<Character> cyrillicLower = new TreeSet<>();
        Set<Character> symbols = new TreeSet<>();
        Scanner scanner;
        try (FileReader fileReader = new FileReader(fileName)) {
            scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                for (int i = 0; i < str.length(); i++) {
                    char ch = str.charAt(i);
                    if (Character.isLetter(ch) && Character.isUpperCase(ch)) {
                        if (Character.UnicodeBlock.of(ch) == Character.UnicodeBlock.BASIC_LATIN) {
                            latinUpper.add(ch);
                        } else if (Character.UnicodeBlock.of(ch) == Character.UnicodeBlock.CYRILLIC) {
                            cyrillicUpper.add(ch);
                        }
                    } else if (Character.isLetter(ch) && Character.isLowerCase(ch)) {
                        if (Character.UnicodeBlock.of(ch) == Character.UnicodeBlock.BASIC_LATIN) {
                            latinLower.add(ch);
                        } else if (Character.UnicodeBlock.of(ch) == Character.UnicodeBlock.CYRILLIC) {
                            cyrillicLower.add(ch);
                        }
                    } else
                        symbols.add(ch);
                }
            }
            scanner.close();
        } catch(Throwable e){
            throw new RuntimeException(e);
        }
        StringBuilder latinUpperString = new StringBuilder();
        StringBuilder latinLowerString = new StringBuilder();
        StringBuilder cyrillicUpperString = new StringBuilder();
        StringBuilder cyrillicLowerString = new StringBuilder();
        for (Character character : latinUpper) {
            latinUpperString.append(character);
        }
        for (Character character : latinLower) {
            latinLowerString.append(character);
        }
        for (Character character : cyrillicUpper) {
            cyrillicUpperString.append(character);
        }
        for (Character character : cyrillicLower) {
            cyrillicLowerString.append(character);
        }

        StringBuilder symbolsString = new StringBuilder();
        for (Character symbol : symbols) {
            symbolsString.append(symbol);
        }
        System.out.println(symbolsString.toString());

        return String.valueOf(latinUpperString.append(latinLowerString).append(cyrillicUpperString).append(cyrillicLowerString));
    }

    public static void main(String[] args) {
        System.out.println(process("src/ru/progwards/java1/lessons/sets/letters.txt"));
    }
}