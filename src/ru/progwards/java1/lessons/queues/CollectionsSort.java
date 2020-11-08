package ru.progwards.java1.lessons.queues;

import java.util.*;

public class CollectionsSort {
    public static void mySort(Collection<Integer> data) {
        List<Integer> list = new ArrayList<>(data);
        int i = 0;
        while (i < list.size()) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) > list.get(j)) {
                    Collections.swap(list, i, j);
                }
            }
            i++;
        }
        data.clear();
        data.addAll(list);
    }

    /* сортировка на основе пузырькового метода */
    public static void mySort2(Collection<Integer> data) {
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
            for (int i = 0; i < list.size() - 1; i++) {
                /* в условие проверяем, больше ли значение get(i) чем get(i+1) и если истина, переходим к методу swap */
                if (list.get(i) > list.get(i + 1)) {
                    /* меняем местами значения под индексами i и i+1 */
                    Collections.swap(list, i, i + 1);
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

    public static void minSort(Collection<Integer> data) {
        /* создаем временный ArrayList, который наполняем значениями, полученными методом Collections.min(data)
         * заполнение происходит от самого меньшего к большему */
        Collection<Integer> tempList = new ArrayList<>();
        /* в цикле методом Collections.min находим минимальные значения и передаем в tempList*/
        for (int i = data.size() - 1; i >= 0; i--) {
            tempList.add(Collections.min(data));
            /* после того как найденное значение переданов Collections.min, функцией remove удаляем его из data */
            data.remove(Collections.min(data));
        }
        /* после завершения цикла все отсортированные значения из tempList помещаем в data */
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

    static void collSort(Collection<Integer> data) {
        /* методом Collections.sort сортируем все значения в data */
        Collections.sort((List) data);

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

    /* заводим константу и присваиваем ей значение */
    static final int ELEMENT = 100_000;

    public static Collection<String> compareSort() {
        /* заводим массив ArrayList размером равным значению константы ELEMENT */
        List<Integer> listMySort = new ArrayList<>(ELEMENT);
        /* заводим генератор псевдослучайных чисел */
        Random random = new Random();
        /* в цикле заполняем listMySort случайными числами в количестве ELEMENT */
        for (int i = 0; i < ELEMENT; i++) {
            listMySort.add(random.nextInt(5000));
        }
        /* копируем содержимое массива listMySort */
        List<Integer> listMinSort = new ArrayList<>(listMySort);
        List<Integer> listCollSort = new ArrayList<>(listMySort);

        /* заводим и инициализируем переменные */
        long speed = 0;
        String nameMethod = "";

        /* заводим массив в который будем помещать результат работы метода addResult(speed, nameMethod) */
        List<String> res = new ArrayList<>();

        /* запоминаем время начала испольнения метода mySort(listMySort) */
        long start = System.currentTimeMillis();
        /* запускаем метод */
        mySort(listMySort);
        /* время исполнения метода - от времени завершения метода отнимаем время начала метода */
        speed = (System.currentTimeMillis() - start);
        /* присваиваем переменной имя метода */
        nameMethod = "mySort";
        /* передаем скорость и имя метода в метод addResult и полученный результат добавляем в массив res */
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

        /* тестирование скорости выполнения сортировки через switch, когда каждый метод запускается и выолняется единолично
         * и только после выполнения предыдущего метода запускается следующий */
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

        /* методом Collections.sort сортируем массив res */
        Collections.sort(res);

        /* заводим массив, в который помещаем только имена методов в отсортированном виде */
        List<String> finalResult = new ArrayList<>();
        /* заводим переменную класса StringBuilder */
        StringBuilder methodName;
        /* запускаем цикл for-each и поочереди берем значения из массива res */
        for (String re : res) {
            /* выделяем место в памяти для переменной methodName */
            methodName = new StringBuilder();
            /* в цикле проверяем каждый символ полученного значения из массива res и если это буква
             * конкатенируем её в переменную methodName */
            for (int j = 0; j < re.length(); j++) {
                char ch = re.charAt(j);
                if (Character.isAlphabetic(ch)) {
                    methodName.append(ch);
                }
            }
            /* сконкатенированный в цикле результат помещаем в массив finalResult */
            finalResult.add(methodName.toString());
        }
        /* возвращаем результат в метод */
        return finalResult;
    }

    /* метод, в котором объединяем результат скорости метода сортировки и имя метода*/
    static String addResult(long speed, String name) {
        /* переменной n присваиваем значение максимального количества символов числа long - 19 */
        int n = String.valueOf(Long.MAX_VALUE).length();
        /* строковую переменную str заполняем девятнадцатью нолями и добавляем результат скорости выполнения метода сортировки */
        String str = "0".repeat(n) + speed;
        /* функцией substring определяем индекс, с которого будет возвращена строка
         * в данном случае берем длину строки str.length() и отнимаем от неё -n (то есть 19) и получаем индекс
         * далее к полученному значению конкатенируем вертикальную черту и имя метода
         * (вертикальная черта сугубо для наглядности и никакого функционала не несёт ) */
        str = str.substring(str.length() - n) + "|" + name;
        return str;
    }


    public static void main(String[] args) {
        final int ELEMENT = 500;
        List<Integer> listMySort = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < ELEMENT; i++) {
            listMySort.add(random.nextInt(2000));
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
