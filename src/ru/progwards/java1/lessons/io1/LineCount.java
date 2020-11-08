package ru.progwards.java1.lessons.io1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LineCount {
    public static int calcEmpty(String fileName) {
        int lines = 0;
        int allError = -1;
        try {
            FileReader fileReader = new FileReader(fileName);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                /* вариант решения через isEmpty */
//                if (str.isEmpty())
                /* вариант решения через equals */
//                if ("".equals(str))
                if (str.length() == 0) {
                    lines++;
                }
            }
            System.out.println(lines);
            scanner.close();
            fileReader.close();
            // FileNotFoundException является наследником IOException
            // и по идее должно хватать указания только IOException
            // но задача не проходит проверку без указания FileNotFoundException
            // зачем писать дважды фактически одно и тоже?
        } catch (FileNotFoundException e) {
            lines = allError;
        } catch (IOException e) {
            lines = allError;
        }
        return lines;
    }

    public static void main(String[] args) throws IOException {
//        LineCount test = new LineCount();
        calcEmpty("src\\ru\\progwards\\java1\\lessons\\io1\\lineCount.txt");
    }
}
