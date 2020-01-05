package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LineCount {
    public static int calcEmpty(String fileName) throws IOException {
        int lines = 0;
        int allError = -1;
        try{
            FileReader fileReader = new FileReader(fileName);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if(str.length() == 0) {
                    lines++;
                }
            }
            System.out.println(lines);
            scanner.close();
        } catch (IOException e){
            return allError;
        }
        return lines;
    }

    public static void main(String[] args) throws IOException {
        LineCount test = new LineCount();
        test.calcEmpty("test_09_T3.txt");
    }
}
