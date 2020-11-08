package ru.progwards.java1.lessons.maps;

import java.io.FileReader;
import java.util.*;

public class SalesInfo {
    private TreeMap<Integer, ArrayList<String>> treeMap = new TreeMap<>();
    /* переменная, используемая в определение проверяемой строки в методе isOnlyLettersOrDigits */
    private static int count;

    public int loadOrders(String fileName) {
        /* переменная учета успешно добавленных строк с данными в treeMap */
        int indexMap = 1;
        try (FileReader fileReader = new FileReader(fileName); Scanner scanner = new Scanner(fileReader)) {
            ArrayList<String> list;
            /* "якорь" к которому происходит возврат из цикла при false.
            как это называется не знаю - нашел по запросу как выйти во внешний цикл */
            outerloop:
            while (scanner.hasNext()) {
                count = 0;
                list = new ArrayList<>();
                String str = scanner.nextLine();
                String[] arr = str.split("[\\\\;,]");
                /* проверяем, соответствует ли массив заданной длине */
                if ((!(arr.length == 4))) {
                    continue;
                }
                /* берем элементы массива и в функции isOnlyLettersOrDigits проверяем, соответствуют ли они заданным параметрам.
                 * если нет, выходим из цикла во внешний while. если соответствует, то добавляем в list */
                for (String s : arr) {
                    if (!(isOnlyLettersOrDigits(s))) {
                        continue outerloop;
                    } else {
                        count++;
                        list.add(s.trim());
                    }
                }
                /* добавляем в TreeMap в качестве ключа цифру, в качестве значения массив ArrayList */
                treeMap.put(indexMap, list);
                indexMap++;
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return indexMap - 1;
    }

    /* метод проверки соответствия входных данных */
    private boolean isOnlyLettersOrDigits(String array) {
        char ch = ' ';
        boolean safe;
        int failCount = 0;
        if (count == 0) {
            for (int j = 0; j < array.length(); j++) {
                ch = array.charAt(j);
                if (!(Character.isLetter(ch)) && !(array.charAt(j) == ' '))
                    failCount += 1;
            }
        } else if (count == 1) {
            for (int j = 0; j < array.length(); j++) {
                ch = array.charAt(j);
                if (!(Character.isLetterOrDigit(ch)) && !(array.charAt(j) == ' '))
                    failCount += 1;
            }
        } else if (count == 2) {
            for (int j = 0; j < array.length(); j++) {
                ch = array.charAt(j);
                if (!(Character.isDigit(ch)) && !(array.charAt(j) == ' '))
                    failCount += 1;
            }
        } else if (count == 3) {
            for (int j = 0; j < array.length(); j++) {
                ch = array.charAt(j);
                if (!(Character.isDigit(ch)) && !(array.charAt(j) == ' ') && !(array.charAt(j) == '.'))
                    failCount += 1;
            }
        }
        safe = failCount == 0;
        return safe;
    }


    public Map<String, Double> getGoods() {
        TreeMap<String, Double> goodsList = new TreeMap<>();

        for (Map.Entry<Integer, ArrayList<String>> entry : treeMap.entrySet()) {
            /* если значение get(1) из ArrayList уже есть в goodsList, тогда из goodsList получаем значение и суммируем с добавляемым */
            if (goodsList.containsKey(entry.getValue().get(1))) {
                Double sum = goodsList.get(entry.getValue().get(1));
                goodsList.put(entry.getValue().get(1), (Double.parseDouble(entry.getValue().get(3)) + sum));
            } else
                goodsList.put(entry.getValue().get(1), Double.parseDouble(entry.getValue().get(3)));
        }
        return goodsList;
    }

    public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers() {
        TreeMap<String, AbstractMap.SimpleEntry<Double, Integer>> goodsList = new TreeMap<>();

        for (Map.Entry<Integer, ArrayList<String>> entry : treeMap.entrySet()) {
            /* если значение get(1) из ArrayList уже есть в goodsList, тогда из goodsList получаем значения и суммируем с добавляемыми */
            if (goodsList.containsKey(entry.getValue().get(0))) {
                AbstractMap.SimpleEntry<Double, Integer> simpleEntry = goodsList.get(entry.getValue().get(0));
                Double sum = simpleEntry.getKey();
                Integer count = simpleEntry.getValue();
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
