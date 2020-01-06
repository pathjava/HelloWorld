package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter) {
        try{
            FileReader fileReader = new FileReader(inFileName);
            Scanner scanner = new Scanner(fileReader);
            FileWriter fileWriter = new FileWriter(outFileName);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                // это рабочий вариант через регулярное выражение, но в нем нет гибкости применения
//                str = str.replaceAll("[^A-Za-zА-Яа-я0-9]", "");
                String strFilter = filter;
                int length = strFilter.length();
                for (int i = 0; i < length; i++){
                    str = str.replace(String.valueOf(strFilter.charAt(i)), "");
                }
                fileWriter.write(str);
                fileWriter.close();
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        filterFile("src\\ru\\progwards\\java1\\lessons\\io1\\charFilterOne.txt",
                "src\\ru\\progwards\\java1\\lessons\\io1\\charFilterTwo.txt", " -,.—()");
    }
}
