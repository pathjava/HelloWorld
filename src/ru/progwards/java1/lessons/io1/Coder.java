package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Scanner;

/*Создать статический метод public static void codeFile(String inFileName, String outFileName, char[] code,
String logName), в котором прочитать файл inFileName и перекодировать его посимвольно в соответствии
с заданным шифром, результат записать в outFileName. Шифр задается маcсивом char[] code, где каждому
символу symbol оригинального файла соответствует символ code[(int)symbol] выходного файла.
В случае ошибок, в файл с именем logName вывести название ошибки через метод класса Exception - getMessage()*/

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) throws IOException {
        try{
            FileReader fileReader = new FileReader(inFileName);
            Scanner scanner = new Scanner(fileReader);
            FileWriter fileWriter = new FileWriter(outFileName);
            String result = "";
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                char[] code1 = str.toCharArray();
                for (char symbol : code1) {
                    result += code[(int) symbol];
                }
                fileWriter.write(result);
                fileWriter.close();
            }
//        } catch (IOException e){
//            // что не так? ловим ошибку, пишем ее в файл и закрываем запись.
//            // так как нет возможности видеть ваш тест, то и отследить по Debug невозможно.
//            FileWriter fileWriterLog = new FileWriter(logName, true);
//            fileWriterLog.write(e.getMessage());
//            fileWriterLog.close();
//        }

       } catch (Exception e){
            FileWriter fileWriterLog = new FileWriter(logName, true);
            try{
                fileWriterLog.write(e.getMessage());
            } catch (Exception e1) {
                System.out.println(e1);
            } finally{
                fileWriterLog.close();
            }
        }

    }

    public static void main(String[] args) throws IOException {
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
