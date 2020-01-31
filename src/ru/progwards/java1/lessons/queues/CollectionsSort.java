package ru.progwards.java1.lessons.queues;

import java.util.*;

public class CollectionsSort {
    public static void mySort(Collection<Integer> data){
        /* создаем ArrayList, который наполняем содержимым параметра data */
        List<Integer> list = new ArrayList<>(data);
        /* создаем и инициализируем переменную sort */
        boolean sort = false;
        /* заводим цикл с условием выполнения, пока sort не будет истина (true) */
        while (!sort) {
            /* присваиваем переменной sort значение истина и когда все значения ArrayList будут отсортированы и
            * цикл for не будет доходить до sort = false, тогда в условие цикла while вернется true и он завершится */
            sort = true;
            /* создаем цикл */
            for (int i = 0; i < list.size() -1; i++) {
                /* в условие проверяем, больше ли значение get(i) чем get(i+1) и если истина, переходим к методу swap */
                if (list.get(i) > list.get(i+1)) {
                    /* меняем местами значения под индексами i и i+1 */
                    Collections.swap(list, i, i+1);
                    /* так как в ArrayList не все отсортировано, то цикл доходит до переменной sort и ей присваивается ложь */
                    sort = false;
                }
            }
        }
        /* очищаем входящую коллекцию из параметра data */
        data.clear();
        /* копируем из list отсортированный массив обратно в data*/
        data.addAll(list);
//        String str;
//        int output = 0;
//        for (Object o : arr) {
//            if (output <= (arr.length-2)){
//                str = ", ";
//            } else str = "\n";
//            System.out.print(o + str);
//            output++;
//        }
    }

//    public static void mySort2(Collection<Integer> data){
//        List<Integer> list = new ArrayList<>(data);
//        boolean sort = false;
//        while (!sort) {
//            sort = true;
//            for (int i = 0; i < list.size() - 1; i++) {
//                if (list.get(i) > list.get(i + 1)) {
//                    int temp = list.get(i);
//                    list.set(i, list.get(i + 1));
//                    list.set(i + 1, temp);
//                    sort = false;
//                }
//            }
//        }
//        String str;
//        int output = 0;
//        for (Object o : list) {
//            if (output <= (list.size()-2)){
//                str = ", ";
//            } else str = "\n";
//            System.out.print(o + str);
//            output++;
//        }
//    }

//    public static void mySort4(Collection<Integer> data){
//        Object[] arr = data.toArray();
//        for (int i = 0; i < arr.length -1; i++){
//            for (int j = 0; j < arr.length -i -1; j++){
//                if ((int)arr[j] > (int)arr[j+1]){
//                        int temp = (int)arr[j];
//                        arr[j] = arr[j+1];
//                        arr[j+1] = temp;
//                }
//            }
//        }
//        String str;
//        int output = 0;
//        for (Object o : arr) {
//            if (output <= (arr.length-2)){
//                str = ", ";
//            } else str = "\n";
//            System.out.print(o + str);
//            output++;
//        }
//    }

    public static void minSort(Collection<Integer> data){
        Collection<Integer> tempList = new ArrayList<>();

        for (int i = data.size() -1; i >= 0; i--) {
            tempList.add(Collections.min(data));
            data.remove(Collections.min(data));
        }
        data.addAll(tempList);

//        String str;
//        int output = 0;
//        for (Object o : tempList) {
//            if (output <= (tempList.size()-2)){
//                str = ", ";
//            } else str = "\n";
//            System.out.print(o + str);
//            output++;
//        }
    }

    static void collSort(Collection<Integer> data){
        Collections.sort((List)data);

//        String str;
//        int output = 0;
//        for (Object o : list) {
//            if (output <= (list.size()-2)){
//                str = ", ";
//            } else str = "\n";
//            System.out.print(o + str);
//            output++;
//        }
    }

    static final int ELEMENT = 1000;
    public static Collection<String> compareSort(){
        List<Integer> listMySort = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < ELEMENT; i++) {
            listMySort.add(random.nextInt(5000));
        }
        List<Integer> listMinSort = new ArrayList<>(listMySort);
        List<Integer> listCollSort = new ArrayList<>(listMySort);

        long speed = 0;
        String nameMethod = "";

        List<String> res = new ArrayList<>();

        long start = System.currentTimeMillis();
        mySort(listMySort);
        speed = (System.currentTimeMillis() - start);
        nameMethod = "mySort";
        res.add(addResult(speed, nameMethod));

        start = System.currentTimeMillis();
        minSort(listMinSort);
        speed = (System.currentTimeMillis() - start);
        nameMethod = "minSort";
        res.add(addResult(speed, nameMethod));

        start = System.currentTimeMillis();
        collSort(listCollSort);
        speed = (System.currentTimeMillis() - start);
        nameMethod = "collSort";
        res.add(addResult(speed, nameMethod));

//        for (int i = 0; i < 3; i++) {
//            switch (i){
//                case 0:
//                    long start = System.currentTimeMillis();
//                    mySort(listMySort);
//                    speed = (System.currentTimeMillis() - start);
//                    nameMethod = "mySort";
//                    res.add(addResult(speed, nameMethod));
//                    break;
//                case 1:
//                    start = System.currentTimeMillis();
//                    minSort(listMinSort);
//                    speed = (System.currentTimeMillis() - start);
//                    nameMethod = "minSort";
//                    res.add(addResult(speed, nameMethod));
//                    break;
//                case 2:
//                    start = System.currentTimeMillis();
//                    collSort(listCollSort);
//                    speed = (System.currentTimeMillis() - start);
//                    nameMethod = "collSort";
//                    res.add(addResult(speed, nameMethod));
//                    break;
//            }
//        }
        Collections.sort(res);

        List<String> finalResult = new ArrayList<>();
        StringBuilder methodName;
        for (String re : res) {
            methodName = new StringBuilder();
            for (int j = 0; j < re.length(); j++) {
                char ch = re.charAt(j);
                if (Character.isAlphabetic(ch)) {
                    methodName.append(ch);
                }
            }
            finalResult.add(methodName.toString());
        }

        return finalResult;
    }

    static String addResult(long speed, String name){
        int n = String.valueOf(Long.MAX_VALUE).length();
        String str = "0".repeat(n) + speed;
        str = str.substring(str.length()-n) + "|" + name;
        return str;
    }


    public static void main(String[] args) {
        final int ELEMENT = 5;
        List<Integer> listMySort = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < ELEMENT; i++) {
            listMySort.add(random.nextInt(20));
        }
        List<Integer> listMinSort = new ArrayList<>(listMySort);
        List<Integer> listCollSort = new ArrayList<>(listMySort);

        System.out.println(listMySort);
        mySort(listMySort);
        System.out.println(listMySort);
        System.out.println();
        System.out.println(listMinSort);
        minSort(listMinSort);
        System.out.println(listMinSort);
        System.out.println();
        System.out.println(listCollSort);
        collSort(listCollSort);
        System.out.println(listCollSort);

        System.out.println(compareSort());
    }
}
