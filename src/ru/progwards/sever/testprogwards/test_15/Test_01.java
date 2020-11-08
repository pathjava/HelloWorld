package ru.progwards.sever.testprogwards.test_15;

import java.util.Calendar;
import java.util.Date;

public class Test_01 {
    public static Date createDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1986, 1, 28);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println(createDate());
    }
}
