package ru.progwards.sever.testprogwards.test_9;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Test_03 {
    private int lineCount(String filename) throws IOException {
        int lines = 0;
        try{
            FileReader fileReader = new FileReader(filename);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                lines++;
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException("файл не найден");
        }
        return lines;
    }
}
