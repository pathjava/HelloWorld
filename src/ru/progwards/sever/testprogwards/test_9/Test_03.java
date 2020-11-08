package ru.progwards.sever.testprogwards.test_9;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Test_03 {
    private int lineCount(String filename) throws IOException {
        int lines = 0;
        try {
            FileReader fileReader = new FileReader(filename);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                if (str.length() > 0) {
                    lines++;
                }
            }
            System.out.println(lines);
            fileReader.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("файл не найден");
        }
        return lines;
    }

    public static void main(String[] args) throws IOException {
        Test_03 test = new Test_03();
        test.lineCount("src\\ru\\progwards\\sever\\testprogwards\\test_9\\test_09_T3.txt");
    }
}
