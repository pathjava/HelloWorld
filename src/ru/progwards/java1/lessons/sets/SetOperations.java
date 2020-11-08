package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {
    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
        /* создаем переменную result и через конструктор передаем в неё параметр set1 */
        HashSet<Integer> result = new HashSet<>(set1);
        /* через метод addAll объединяем содержимое result и set2 */
        result.addAll(set2);
        return result;
    }

    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
        /* создаем переменную result и через конструктор передаем в неё параметр set1 */
        HashSet<Integer> result = new HashSet<>(set1);
        /* через метод retainAll оставляем только пересекающиеся значения result и set2, остальное удаляем */
        result.retainAll(set2);
        return result;
    }

    public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2) {
        /* создаем переменную result и через конструктор передаем в неё параметр set1 */
        HashSet<Integer> result = new HashSet<>(set1);
        /* через метод removeAll удаляем все значения из result, которые есть в set2 */
        result.removeAll(set2);
        return result;
    }

    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2) {
        /* создаем переменную sumAll и через конструктор передаем в неё параметр set1 */
        HashSet<Integer> sumAll = new HashSet<>(set1);
        /* через метод addAll объединяем содержимое sumAll и set2 */
        sumAll.addAll(set2);
        /* создаем переменную intersection и через конструктор передаем в неё параметр set1 */
        HashSet<Integer> intersection = new HashSet<>(set1);
        /* через метод retainAll оставляем только пересекающиеся значения intersection и set2, остальное удаляем */
        intersection.retainAll(set2);
        /* через метод removeAll удаляем из sumAll все значения, содержащиеся в intersection */
        sumAll.removeAll(intersection);
        return sumAll;
    }


    public static void main(String[] args) {
//        Set<Integer> setOne = Set.of(8, 7, 15, 2, 11, 13, 14, 6, 10, 4, 12, 3, 1, 9, 5);
//        Set<Integer> setTwo = Set.of(9, 6, 7, 12, 10, 5, 19, 15, 14, 11, 8, 17, 16, 20, 13);

        Set<Integer> setOne = Set.of(1, 2, 3, 4, 5);
        Set<Integer> setTwo = Set.of(3, 4, 5, 6, 7);

        System.out.println(union(setOne, setTwo));
        System.out.println(intersection(setOne, setTwo));
        System.out.println(difference(setOne, setTwo));
        System.out.println(symDifference(setOne, setTwo));
    }
}
