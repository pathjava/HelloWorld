package ru.progwards.java1.lessons.queues;

import java.util.*;

public class CollectionsSort {

    public static void mySort(Collection<Integer> data){
        Object[] arr = data.toArray();
        for (int i = 0; i < arr.length -1; i++){
            for (int j = 0; j < arr.length -i -1; j++){
                if ((int)arr[j] > (int)arr[j+1]){
                    int temp = (int)arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
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

    public static void mySort2(Collection<Integer> data){
        List<Integer> list = new ArrayList<>(data);
        boolean sort = false;
        while (!sort) {
            sort = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) > list.get(i + 1)) {
                    int temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    sort = false;
                }
            }
        }
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

    public static void mySort3(Collection<Integer> data){
        List<Integer> list = new ArrayList<>(data);
        boolean sort = false;
        while (!sort) {
            sort = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) > list.get(i + 1)) {
                    Collections.swap(list, i, i+1);
                    sort = false;
                }
            }
        }
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
        List<Integer> list = new ArrayList<>(data);
        Collections.sort(list);

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

    static final int ELEMENT = 200;
    public static Collection<String> compareSort(){
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < ELEMENT; i++) {
            list.add(random.nextInt(5000));
        }

        List<String> result = new ArrayList<>();
        long mySort = 0;
        long minSort =0;
        long collSort = 0;
        for (int i = 0; i < 3; i++) {
            switch (i){
                case 0:
                    long start = System.currentTimeMillis();
                    mySort(list);
                    mySort = (System.currentTimeMillis() - start);
                    result.add(i, "mySort");
                    result.add(i+1, String.valueOf(mySort));
                    break;
                case 1:
                    start = System.currentTimeMillis();
                    minSort(list);
                    minSort = (System.currentTimeMillis() - start);
                    result.add(i+1, "minSort");
                    result.add(i+2, String.valueOf(minSort));
                    break;
                case 2:
                    start = System.currentTimeMillis();
                    collSort(list);
                    collSort = (System.currentTimeMillis() - start);
                    result.add(i+2, "collSort");
                    result.add(i+3, String.valueOf(collSort));
                    break;
            }
        }
        System.out.println(result);

        List<String> resultTest = new ArrayList<>();
        if (mySort < minSort && mySort < collSort){
            resultTest.add(0, "mySort");
        } else if (minSort < mySort && minSort < collSort){
            resultTest.add(0, "minSort");
        } else
            resultTest.add(0, "collSort");
        return resultTest;
    }


    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>(List.of(5,25,11,-1,7,1,-12,28,35,3));
//        mySort(array);
//        mySort2(array);
//        mySort3(array);
//        minSort(array);
//        collSort(array);
        System.out.println(compareSort());
    }
}
