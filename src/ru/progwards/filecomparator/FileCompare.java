package ru.progwards.filecomparator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileCompare {

    private Map<Integer, String> listMapOne = new TreeMap<>();
    private Map<Integer, String> listMapTwo = new TreeMap<>();

    public void readFiles(String pathOne, String pathTwo) {
        if (pathOne == null || pathTwo == null){
            System.out.println("Не выбран файл!");
            return;
        }
        try(BufferedReader readerOne = new BufferedReader(new FileReader(pathOne))){
            String lineOne;
            int numberLineOne = 0;
            while ((lineOne = readerOne.readLine()) != null){
                listMapOne.put(numberLineOne, lineOne);
                numberLineOne++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedReader readerTwo = new BufferedReader(new FileReader(pathTwo))){
            String lineTwo;
            int numberLineTwo = 0;
            while ((lineTwo = readerTwo.readLine()) != null){
                listMapTwo.put(numberLineTwo, lineTwo);
                numberLineTwo++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        FileCompare test = new FileCompare();
        test.readFiles("C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\01.txt",
                "C:\\Intellij Idea\\programming\\HelloWorld\\src\\ru\\progwards\\filecomparator\\02.txt");

        System.out.println("-----------One------------");
        for (Map.Entry<Integer, String> entry : test.listMapOne.entrySet()) {
            System.out.format("%-2d", entry.getKey());
            System.out.println(" : " + entry.getValue());
        }

        System.out.println("-----------Two------------");
        for (Map.Entry<Integer, String> entry : test.listMapTwo.entrySet()) {
            System.out.format("%-2d", entry.getKey());
            System.out.println(" : " + entry.getValue());
        }
    }
}
