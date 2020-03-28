package ru.progwards.sever.testprogwards.test_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringToBytes {
    public static void main(String[] args) {
//        String example = "  } Convert Java String \n text test";
//        byte[] bytes = example.getBytes();
//        byte[] bytes2 = example.getBytes();
//
//        System.out.println(Arrays.toString(bytes));
//
//        System.out.println("----------------------------------");
//        for (byte aByte : bytes) {
//            System.out.println(aByte);
//        }
//
//        System.out.println("----------------------------------");
//        StringBuilder stringBuilder = new StringBuilder();
//        for (byte aByte : bytes) {
//            stringBuilder.append(aByte);
//        }
//        System.out.println(stringBuilder.toString());
//
//        System.out.println("----------------------------------");
//        String result = new String(bytes);
//        System.out.println(result);
//
//        System.out.println("----------------------------------");
//        System.out.println(Arrays.equals(bytes, bytes2));

        System.out.println("----------------------------------");
        byte[] bt;
        List<String> list = new ArrayList<>(List.of("Convert Java String", "", "test", ""));
        for (String s : list) {
            bt = s.getBytes();
            System.out.println(Arrays.toString(bt));
            System.out.println(new String(bt));
        }

    }
}
