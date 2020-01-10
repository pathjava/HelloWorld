package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/*Создать статический метод public static void codeFile(String inFileName, String outFileName, char[] code,
String logName), в котором прочитать файл inFileName и перекодировать его посимвольно в соответствии
с заданным шифром, результат записать в outFileName. Шифр задается маcсивом char[] code, где каждому
символу symbol оригинального файла соответствует символ code[(int)symbol] выходного файла.
В случае ошибок, в файл с именем logName вывести название ошибки через метод класса Exception - getMessage()*/

public class Coder {
//    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) throws IOException {
//        try{
//            FileReader fileReader = new FileReader(inFileName);
//            Scanner scanner = new Scanner(fileReader);
//            FileWriter fileWriter = new FileWriter(outFileName);
////            FileWriter fileWriterLog = new FileWriter(logName, true);
//            String result = "";
//            while (scanner.hasNextLine()) {
//                String str = scanner.nextLine();
//                char[] code1 = str.toCharArray();
//                for (char symbol : code1) {
//                    result += code[(int) symbol];
//                }
//                fileWriter.write(result);
//                fileWriter.close();
//            }
//        } catch (Exception e){
//            FileWriter fileWriterLog = new FileWriter(logName, true);
////            System.out.println(e.getMessage());
//            fileWriterLog.write(e.getMessage());
//        }
//    }

    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        FileInputStream fIn = null;
        BufferedInputStream bIn = null;
        FileOutputStream fOut = null;
        BufferedOutputStream bOut = null;
        try {
            fIn = new FileInputStream(inFileName);
            bIn = new BufferedInputStream(fIn);
            fOut = new FileOutputStream(outFileName);
            bOut = new BufferedOutputStream(fOut);
            int i;
            while ((i = bIn.read()) != -1) {
                bOut.write(code[i]);
            }
        } catch (Throwable e) {
            FileWriter fEOut = null;
            BufferedWriter bEOut = null;
            try {
                fEOut = new FileWriter(logName, true);
                bEOut = new BufferedWriter(fEOut);
                bEOut.write(e.getMessage());
            } catch (Throwable e2) {
            } finally {
                try {
                    if (bEOut != null) bEOut.close();
                    if (fEOut != null) fEOut.close();
                } catch (Throwable e3) {
                }
            }
        } finally {
            try {
                if (bOut != null) bOut.close();
                if (fOut != null) fOut.close();
                if (bIn != null) bIn.close();
                if (fIn != null) fIn.close();
            } catch (Throwable e) {
            }
        }
    }

    public static void main(String[] args) throws IOException {
        char[] code = new char[256];
        // заполняем исходящий файл
//        for (int i = 0; i < code.length; i++) {
//            code[i] = (char)i;
//        }
        // вызываем ошибку - если в тексте есть цифра, увеличиваем её значение +1
        for(int i = 0; i < code.length; i++){
            code[i] = (char) (Character.isDigit((char) i) ? i + 1 : i);
        }
        codeFile("src\\ru\\progwards\\java1\\lessons\\io1\\coderIn.txt",
                "src\\ru\\progwards\\java1\\lessons\\io1\\coderOut.txt",
                code, "src\\ru\\progwards\\java1\\lessons\\io1\\coderLog.txt");
    }
}
