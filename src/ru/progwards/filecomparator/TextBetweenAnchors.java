package ru.progwards.filecomparator;

public class TextBetweenAnchors {
    public int lineNumber;
    public int index;
    public String start = "";
    public String stop = "";
    public String finishOneNumber = "";
    public String finishTwoNumber = "";
    public String startOneNumber = "";
    public String startTwoNumber = "";
    public String anchorsLines;

    @Override
    public String toString() {
        return String.format("%3d|%3s|%3s|%5s|%3s|%3s|%4s|%3d| %s", lineNumber, startOneNumber, startTwoNumber, start,
                finishOneNumber, finishTwoNumber, stop, index, anchorsLines);
    }
}
