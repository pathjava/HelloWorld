package ru.progwards.java1.lessons.io2;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Censor {
    public static void censorFile(String inoutFileName, String[] obscene) throws CensorException {
        try (FileReader fileReader = new FileReader(inoutFileName);
             Scanner scanner = new Scanner(fileReader)) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                /* проверяем, есть ли слова из массива obscene в строке из файла */
                for (int i = 0; i < obscene.length; i++) {
                    if (str.contains(obscene[i])) {
                        /* и если строка содержит слово из массива obscene, меняем слово на * через
                        метод ChangeWord с тем же количеством символов */
                        str = str.replace(obscene[i], ChangeWord(obscene[i]));
                    }
                }
                /* записываем результат из строки str в исходный файл */
                try (FileWriter fileWriter = new FileWriter(inoutFileName)) {
                    fileWriter.write(str);
                }
            }
        } catch (Exception e) {
            throw new CensorException(e.getMessage(), inoutFileName);
        }
    }

    public static String ChangeWord(String obscene) {
        /* компилятор progwards не обрабатывает решение из одной строки */
//        return "*".repeat(obscene.length());

        /* из метода censorFile получаем ChangeWord(obscene[i]) и генерируем строку из * с тем же
         * количеством символов*/
        StringBuilder tempStar = new StringBuilder();
        for (int i = 0; i <= obscene.length() - 1; i++) {
            tempStar.append("*");
        }
        return tempStar.toString();
    }

    /* свой класс обработчик ошибок*/
    static class CensorException extends Exception {
        String errName;
        String fileName;

        CensorException(String errName, String fileName) {
            this.errName = errName;
            this.fileName = fileName;
        }

        @Override
        public String toString() {
            return errName + ":" + fileName;
        }
    }

    public static void main(String[] args) {
//        try {
//            censorFile(null,
//                    null);
//        } catch (CensorException e) {
//            System.out.println(e.toString());
//        }

        try {
            censorFile("src\\ru\\progwards\\java1\\lessons\\io2\\censorTest.txt",
                    new String[]{"Hello", "World", "Java", "Saint-Petersburg"});
        } catch (CensorException e) {
            System.out.println(e.toString());
        }
    }
}


/*
    public static String ChangeWord(String obscene){
        StringBuilder tempStar = new StringBuilder();
        for (int i = 0; i <= obscene.length() - 1; i++){
            tempStar.append("*");
        }
        return tempStar.toString();
    }
*/