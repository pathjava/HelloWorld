package ru.progwards.filecomparator;

public class FileAnchors {
    public String anchorsLines = "";
    public String start = "#";
    public String finish = "#";
    public String finishOneIndex = "";
    public String finishTwoIndex = "";
    public String startOneIndex = "";
    public String startTwoIndex = "";

    @Override
    public String toString() {
        return String.format("%3s|%3s|%3s|%3s|%5s|%6s| %s", startOneIndex, startTwoIndex, finishOneIndex, finishTwoIndex,  start,finish, anchorsLines);
    }
}