package ru.progwards.java1.lessons.sets;

import java.io.FileReader;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class LettersInFile {
    public static String process(String fileName) {
        /* создаем "деревья" TreeSet для дальнейшей передачи и сортировки в них заглавных и строчных букв */
        Set<Character> latinUpper = new TreeSet<>();
        Set<Character> latinLower = new TreeSet<>();
        Set<Character> cyrillicUpper = new TreeSet<>();
        Set<Character> cyrillicLower = new TreeSet<>();
        /* а также "дерево" для символов */
        Set<Character> symbols = new TreeSet<>();
        /* в try-with-resources создаем файлридер и сканер, которые в случае проблем будут принудительно закрыты */
        try (FileReader fileReader = new FileReader(fileName); Scanner scanner = new Scanner(fileReader)) {
            /* запускаем цикл и крутимся в нем пока есть строки, что проверяем методом hasNextLine() */
            while (scanner.hasNextLine()) {
                /* полученную сканером строку помещаем в переменную типа String */
                String str = scanner.nextLine();
                /* крутимся в цикле пока не закончится строка str.length() */
                for (int i = 0; i < str.length(); i++) {
                    /* получаем символ из строки согласно индекса (i) */
                    char ch = str.charAt(i);
                    /* проверяем, является ли поступивший символ буквой через метод isLetter, если это буква, проверяем
                     * является ли она латинской */
                    if (Character.isLetter(ch) && Character.UnicodeBlock.of(ch) == Character.UnicodeBlock.BASIC_LATIN) {
                        /* проверяем, является ли буква заглавной и если да, то передаем её в TreeSet через метод add */
                        if (Character.isUpperCase(ch)) {
                            latinUpper.add(ch);
                            /* если при прроверке выше буква оказалась не заглавной, а значит строчная,
                             * тогда передаем её в TreeSet через метод add */
                        } else
                            latinLower.add(ch);
                        /* проверяем, является ли поступивший символ буквой через метод isLetter, если это буква, проверяем
                         * является ли она кириллицей */
                    } else if (Character.isLetter(ch) && Character.UnicodeBlock.of(ch) == Character.UnicodeBlock.CYRILLIC) {
                        /* проверяем, является ли буква заглавной и если да, то передаем её в TreeSet через метод add */
                        if (Character.isUpperCase(ch)) {
                            cyrillicUpper.add(ch);
                            /* если при прроверке выше буква оказалась не заглавной, а значит строчная,
                             * тогда передаем её в TreeSet через метод add */
                        } else
                            cyrillicLower.add(ch);
                        /* если переданный выше символ не является буквой, тогда помещаем его в TreeSet через метод add к разным символам */
                    } else
                        symbols.add(ch);
                }
            }
            /* пробрасываем исключения наверх */
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        /* создаем экземляры StringBuilder для дальнейшей конкатенации символов из TreeSet */
        StringBuilder latinUpperString = new StringBuilder();
        StringBuilder latinLowerString = new StringBuilder();
        StringBuilder cyrillicUpperString = new StringBuilder();
        StringBuilder cyrillicLowerString = new StringBuilder();
        /* в циклах проходим по содержимому TreeSet и конкатенируем полученные данные в StringBuilder */
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
        /* собираем и выводим в консоль все лишние символы */
        StringBuilder symbolsString = new StringBuilder();
        for (Character symbol : symbols) {
            symbolsString.append(symbol);
        }
//        System.out.println(symbolsString.toString());
        /* конкатенируем все StringBuilder в единую строку и возвращаем в метод */
        return String.valueOf(latinUpperString.append(latinLowerString).append(cyrillicUpperString).append(cyrillicLowerString));
    }

    public static void main(String[] args) {
        System.out.println(process("src/ru/progwards/java1/lessons/sets/letters.txt"));
    }
}