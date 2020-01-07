package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Scanner;

/*Создать статический метод public static void codeFile(String inFileName, String outFileName, char[] code,
String logName), в котором прочитать файл inFileName и перекодировать его посимвольно в соответствии
с заданным шифром, результат записать в outFileName. Шифр задается маcсивом char[] code, где каждому
символу symbol оригинального файла соответствует символ code[(int)symbol] выходного файла.
В случае ошибок, в файл с именем logName вывести название ошибки через метод класса Exception - getMessage()*/

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        try{
            FileReader fileReader = new FileReader(inFileName);
            Scanner scanner = new Scanner(fileReader);
            FileWriter fileWriter = new FileWriter(outFileName);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                int result = 0;
                char[] code1 = str.toCharArray();
                for (int i = 1; i < str.length(); i++){
                    char symbol = code1[i];
                    result += code[(int)symbol];
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
//        codeFile("src\\ru\\progwards\\java1\\lessons\\io1\\coderIn.txt",
//                "src\\ru\\progwards\\java1\\lessons\\io1\\coderOut.txt",
//                code, "src\\ru\\progwards\\java1\\lessons\\io1\\coderLog.txt");
    }
}
