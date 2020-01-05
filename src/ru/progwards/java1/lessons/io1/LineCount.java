package ru.progwards.java1.lessons.io1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LineCount {
    public static int calcEmpty(String fileName) throws FileNotFoundException {
        int lines = 0;
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
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("-1");
        }
        return lines;
    }

    public static void main(String[] args) throws IOException {
        LineCount test = new LineCount();
        test.calcEmpty("test_09_T3.txt");
    }
}
