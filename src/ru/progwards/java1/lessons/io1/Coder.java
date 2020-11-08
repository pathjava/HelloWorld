package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Scanner;

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        FileReader fileReader = null;
//        Scanner scanner = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(inFileName);
//            scanner = new Scanner(fileReader);
            fileWriter = new FileWriter(outFileName);
            int symbol = fileReader.read();
            while (symbol != -1) {
                fileWriter.write(code[symbol]);
                symbol = fileReader.read();
            }

//            int i;
//            while ((i = fileReader.read()) != -1) {
//                fileWriter.write(code[i]);
//            }

            // по данному решению Валерий говорил, что верно, но робот не принял
//            String result = "";
//            while (scanner.hasNextLine()) {
//                String str = scanner.nextLine();
//                char[] code1 = str.toCharArray();
//                for (int symbol : code1) {
//                    result += code[symbol];
//                }
//                fileWriter.write(result);
//                fileWriter.close();
//            }

        } catch (Throwable e) {
            FileWriter fileWriterLog = null;
            try {
                fileWriterLog = new FileWriter(logName, true);
                fileWriterLog.write(e.getMessage());
                fileWriterLog.close();
            } catch (Throwable e1) {
                e1.printStackTrace();
            } finally {
                try {
                    if (fileWriterLog != null) {
                        fileWriterLog.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//                scanner.close();
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        char[] code = new char[256];
        // заполняем исходящий файл
        for (int i = 0; i < code.length; i++) {
            code[i] = (char) i;
        }
        // вызываем ошибку - если в тексте есть цифра, увеличиваем её значение +1
//        for(int i = 0; i < code.length; i++){
//            code[i] = (char) (Character.isDigit((char) i) ? i + 1 : i);
//        }
        codeFile("src\\ru\\progwards\\java1\\lessons\\io1\\coderIn.txt",
                "src\\ru\\progwards\\java1\\lessons\\io1\\coderOut.txt",
                code, "src\\ru\\progwards\\java1\\lessons\\io1\\coderLog.txt");
    }
}
