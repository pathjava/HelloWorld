package ru.progwards.java1.lessons.maps;

import java.io.FileReader;
import java.util.*;

import static java.util.AbstractMap.*;

public class SalesInfo {
    private TreeMap<Integer, ArrayList<String>> treeMap = new TreeMap<>();
    private static int count;
    public int loadOrders(String fileName){
        int indexMap = 1;
        try (FileReader fileReader = new FileReader(fileName); Scanner scanner = new Scanner(fileReader)) {
            ArrayList<String> list;
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
        } catch(Throwable e){
            throw new RuntimeException(e);
        }
        return indexMap - 1;
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


    public Map<String, Double > getGoods(){
        TreeMap<String, Double> goodsList = new TreeMap<>();

        for (Map.Entry<Integer, ArrayList<String>> entry : treeMap.entrySet()) {
            if (goodsList.containsKey(entry.getValue().get(1))){
                Double sum = goodsList.get(entry.getValue().get(1));
                goodsList.put(entry.getValue().get(1), (Double.parseDouble(entry.getValue().get(3))+ sum));
            } else
            goodsList.put(entry.getValue().get(1), Double.parseDouble(entry.getValue().get(3)));
        }
        return goodsList;
    }

    public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers(){
        TreeMap<String, AbstractMap.SimpleEntry<Double, Integer>> goodsList = new TreeMap<>();

        for (Map.Entry<Integer, ArrayList<String>> entry : treeMap.entrySet()) {
            if (goodsList.containsKey(entry.getValue().get(0))){
                double sum = 0;
                int count = 0;
                for (SimpleEntry<Double, Integer> value : goodsList.values()) {
                    sum = value.getKey();
                    count = value.getValue();
                }
                goodsList.put(entry.getValue().get(0),
                        (new AbstractMap.SimpleEntry<>((Double.parseDouble(entry.getValue().get(3)) + sum),
                                (Integer.parseInt(entry.getValue().get(2))) + count)));
            } else
                goodsList.put(entry.getValue().get(0),
                        (new AbstractMap.SimpleEntry<>((Double.parseDouble(entry.getValue().get(3))),
                                (Integer.parseInt(entry.getValue().get(2))))));
        }

//        for (Entry<String, SimpleEntry<Double, Integer>> entry : goodsList.entrySet()) {
//            System.out.println(entry.getKey() +" : "+ entry.getValue().getKey() +" : "+ entry.getValue().getValue());
//        }
        return goodsList;
    }

    public static void main(String[] args) {
        SalesInfo test = new SalesInfo();
        System.out.println(test.loadOrders("src/ru/progwards/java1/lessons/maps/fullSalesInfo.csv"));
        System.out.println(test.getGoods());
        System.out.println(test.getCustomers());
    }
}
