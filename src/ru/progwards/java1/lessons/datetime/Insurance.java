package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.ZonedDateTime;

public class Insurance {
    public static enum FormatStyle {SHORT, LONG, FULL}

    private ZonedDateTime start;
    private Duration duration;

    public Insurance(ZonedDateTime start) {
        this.start = start;
    }

    public Insurance(String strStart, FormatStyle style){

    }


    public void setDuration(Duration duration){

    }
    public void setDuration(ZonedDateTime expiration){

    }
    public void setDuration(int months, int days, int hours){

    }
    public void setDuration(String strDuration, FormatStyle style){

    }
    public boolean checkValid(ZonedDateTime dateTime){
        return true;
    }

    @Override
    public String toString() {
        return "Insurance issued on " + start;
//        return "Insurance issued on " + start + validStr;
    }
}
