package ru.progwards.sever.testprogwards.test_10;

import java.util.Scanner;

public class Test_02 {
    public void scanLines(){
        try (Scanner scanner = new Scanner(System.in)) {
            String str, strLow;
            while (true){
                str = scanner.nextLine();
//                strLow = str.toLowerCase();
                if (str.contains("привет")){
                    System.out.println("Здравствуйте!");
                } else if (str.contains("как дела")){
                    System.out.println("Хорошо");
                } else if (str.contains("/stop")){
                    return;
                } else
                    System.out.println(str);
            }
        }
    }

    public static void main(String[] args) {
        Test_02 test = new Test_02();
        test.scanLines();
    }

}
