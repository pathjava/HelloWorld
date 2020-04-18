package ru.progwards.filecomparator;

public class TextBetweenAnchors {
    public int lineNumber;
    public String start = "";
    public String finish = "";
    public String finishOneNumber = "";
    public String finishTwoNumber = "";
    public String startOneNumber = "";
    public String startTwoNumber = "";
    public String anchorsLines = "";

    @Override
    public String toString() {
        return String.format("%3s|%3s|%3s|%5s|%3s|%3s|%6s| %s", lineNumber, startOneNumber, startTwoNumber, start,
                finishOneNumber, finishTwoNumber, finish, anchorsLines);
    }
}
