package ru.progwards.java1.lessons.io1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LineCount {
    public static int calcEmpty(String fileName) {
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
        } catch (FileNotFoundException e) {
            lines = allError;
        } catch (IOException e) {
            lines = allError;
        }
        return lines;
    }

    public static void main(String[] args) throws IOException {
        LineCount test = new LineCount();
        test.calcEmpty("test_09_T3.txt");
    }
}
