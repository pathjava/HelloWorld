package ru.progwards.java1.lessons.queues;

import com.google.inject.internal.asm.$ClassWriter;

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
        String str;
        int i = 0;
        for (Object o : arr) {
            if (i <= (arr.length-2)){
                str = ", ";
            } else str = "\n";
            System.out.print(o + str);
            i++;
        }
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
        String str;
        int i = 0;
        for (Object o : list) {
            if (i <= (list.size()-2)){
                str = ", ";
            } else str = "\n";
            System.out.print(o + str);
            i++;
        }
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
        String str;
        int i = 0;
        for (Object o : list) {
            if (i <= (list.size()-2)){
                str = ", ";
            } else str = "\n";
            System.out.print(o + str);
            i++;
        }
    }

    public static void minSort(Collection<Integer> data){
        List<Integer> list = new ArrayList<>(data);
        Collection<Integer> tempList = new ArrayList<>();

        for (int i = list.size() -1; i >= 0; i--) {
            tempList.add(Collections.min(list));
            list.remove(Collections.min(list));
        }
        data.clear();
        data.addAll(tempList);

        String str;
        int i = 0;
        for (Object o : tempList) {
            if (i <= (tempList.size()-2)){
                str = ", ";
            } else str = "\n";
            System.out.print(o + str);
            i++;
        }
    }


    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>(List.of(5,25,11,1,7,1,12,28,35,3));
        mySort(array);
        mySort2(array);
        mySort3(array);
        minSort(array);
    }
}
