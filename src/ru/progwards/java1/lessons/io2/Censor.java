package ru.progwards.java1.lessons.io2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Censor {
    public static void censorFile(String inoutFileName, String[] obscene){
        if (inoutFileName == null || inoutFileName.compareTo("") == 0) try {
            throw new CensorException("Имя файла передавать обязатльно", inoutFileName);
        } catch (CensorException e) {
            e.printStackTrace();
        }
        if (obscene == null) try {
            throw new CensorException("Последовательность слов передавать обязательно", inoutFileName);
        } catch (CensorException e) {
            e.printStackTrace();
        }
        assert inoutFileName != null;
        try (FileReader fileReader = new FileReader(inoutFileName);
             Scanner scanner = new Scanner(fileReader);) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                assert obscene != null;
                for (int i = 0; i < obscene.length; i++) {
                    if (str.contains(obscene[i])) {
                        str = str.replace(obscene[i], ChangeWord(obscene[i]));
                    }
                }
                FileWriter fileWriter = new FileWriter(inoutFileName);
                fileWriter.write(str);
                fileWriter.close();
            }
        } catch (IOException e) {
            try {
                throw new CensorException(e.getMessage(), inoutFileName);
            } catch (CensorException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String ChangeWord(String obscene){
//        return "*".repeat(obscene.length());
        StringBuilder tempStar = new StringBuilder();
        for (int i = 0; i <= obscene.length() - 1; i++){
            tempStar.append("*");
        }
        return tempStar.toString();
    }

    static class CensorException extends Exception{
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
        censorFile("src\\ru\\progwards\\java1\\lessons\\io2\\censorTest.txt",
                new String[]{"Hello", "World", "Java", "Saint-Petersburg"});
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