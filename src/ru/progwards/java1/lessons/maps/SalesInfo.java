package ru.progwards.java1.lessons.maps;

import java.io.FileReader;
import java.util.*;

public class SalesInfo {
    TreeMap<Integer, ArrayList<String>> treeMap = new TreeMap<>();
    static int count;
    public int loadOrders(String fileName){
        try (FileReader fileReader = new FileReader(fileName); Scanner scanner = new Scanner(fileReader)) {
            int indexMap = 1;
            ArrayList<String> list = null;
            outerloop:
            while (scanner.hasNext()){
                count = 0;
                list = new ArrayList<>();
                String str = scanner.nextLine();
                String[] arr = str.split("[\\\\;,]");
                if ((!(arr.length == 4))){
                    continue;
                }
                for (String s : arr) {
                    if (!(isOnlyLetters(s))) {
                        continue outerloop;
                    } else {
                        count++;
                        list.add(s.trim());
                    }
                }
                treeMap.put(indexMap, list);
                indexMap++;
            }

            for (Map.Entry<Integer, ArrayList<String>> entry : treeMap.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        } catch(Throwable e){
            throw new RuntimeException(e);
        }
        return 0;
    }

    private boolean isOnlyLetters(String array){
        char ch = ' ';
        boolean safe;
        int failCount = 0;
        if (count == 0){
            for (int j = 0; j < array.length(); j++) {
                ch = array.charAt(j);
                if (!(Character.isLetter(ch)) && !(array.charAt(j) == ' '))
                    failCount += 1;
            }
        } else if (count == 1){
            for (int j = 0; j < array.length(); j++) {
                ch = array.charAt(j);
                if (!(Character.isLetterOrDigit(ch)) && !(array.charAt(j) == ' '))
                    failCount += 1;
            }
        } else if (count == 2){
            for (int j = 0; j < array.length(); j++) {
                ch = array.charAt(j);
                if (!(Character.isDigit(ch)) && !(array.charAt(j) == ' '))
                    failCount += 1;
            }
        } else if (count == 3){
            for (int j = 0; j < array.length(); j++) {
                ch = array.charAt(j);
                if (!(Character.isDigit(ch)) && !(array.charAt(j) == ' ') && !(array.charAt(j) == '.'))
                    failCount += 1;
            }
        }
        safe = failCount == 0;
        return safe;
    }



//    public Map<String, Double> getGoods(){
//
//    }
//
//    public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers(){
//
//    }

    public static void main(String[] args) {
        SalesInfo test = new SalesInfo();
        test.loadOrders("src/ru/progwards/java1/lessons/maps/fullSalesInfo.csv");
    }
}
