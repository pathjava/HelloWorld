package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Scanner;

/*Создать статический метод public static void codeFile(String inFileName, String outFileName, char[] code,
String logName), в котором прочитать файл inFileName и перекодировать его посимвольно в соответствии
с заданным шифром, результат записать в outFileName. Шифр задается маcсивом char[] code, где каждому
символу symbol оригинального файла соответствует символ code[(int)symbol] выходного файла.
В случае ошибок, в файл с именем logName вывести название ошибки через метод класса Exception - getMessage()*/

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName)  {
        FileReader fileReader = null;
        Scanner scanner = null;
        FileWriter fileWriter = null;
        try{
            fileReader = new FileReader(inFileName);
            scanner = new Scanner(fileReader);
            fileWriter = new FileWriter(outFileName);
            String result = "";
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                char[] code1 = str.toCharArray();
                for (char symbol : code1) {
                    result += code[(int) symbol];
                }
//                fileWriter.write(result);
//                fileWriter.close();
            }
       } catch (IOException e){
            FileWriter fileWriterLog = null;
            try{
                fileWriterLog = new  FileWriter(logName, true);
                fileWriterLog.write(e.getMessage());
            } catch (IOException e1) {
            } finally{
                try {
                    assert fileWriterLog != null;
                    fileWriterLog.close();
                } catch (IOException e1){
                }
            }
        } finally {
            try {
                assert fileWriter != null;
                fileWriter.close();
                scanner.close();
                fileReader.close();
            }catch (IOException e){

            }
        }

    }

    public static void main(String[] args)  {
        char[] code = new char[256];
        // заполняем исходящий файл
        for (int i = 0; i < code.length; i++) {
            code[i] = (char)i;
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
