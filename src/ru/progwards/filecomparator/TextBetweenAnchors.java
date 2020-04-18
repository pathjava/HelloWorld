package ru.progwards.filecomparator;

public class TextBetweenAnchors {
    public String lineNumber;
    public String anchorsLines = "";
    public String start = "";
    public String finish = "";
    public String finishOneNumber = "";
    public String finishTwoNumber = "";
    public String startOneNumber = "";
    public String startTwoNumber = "";

    @Override
    public String toString() {
        return String.format("%3s|%3s|%5s|%3s|%3s|%6s| %s", startOneNumber, startTwoNumber, start,
                finishOneNumber, finishTwoNumber, finish, anchorsLines);
    }
}
