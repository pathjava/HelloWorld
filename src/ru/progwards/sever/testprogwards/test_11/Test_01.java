package ru.progwards.sever.testprogwards.test_11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Collection;

public class Test_01 {
    public List<Integer> listAction(List<Integer> list) {
        list.remove(Collections.min(list));
        list.add(0, list.size());
        list.add(2, Collections.max(list));
        return list;
    }


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
//        for(int i=0; i<5; i++)
//            list.add(i);

        for (int i = 5; i > 0; i--)
            list.add(i);

        System.out.println(list);

        Test_01 test = new Test_01();
        test.listAction(list);

        System.out.println(list);
    }
}


/*
    Collection<Integer> numbers = new ArrayList<>();
        for(int i=0; i<5; i++)
        numbers.add(i);

        System.out.println(numbers);

//        numbers.add(3, 5);
//        numbers.remove(3);
//        numbers.add(Collections.min(numbers));
//        ((ArrayList)numbers).add(3, numbers.size());


        System.out.println(numbers);
*/
