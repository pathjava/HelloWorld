package ru.progwards.filecomparator;

public class FileAnchors {
    public String anchorLine = "";
    public String start = "";
    public String stop = "";
    public String stopOneNumber = "";
    public String stopTwoNumber = "";
    public String startOneNumber = "";
    public String startTwoNumber = "";
    public String lineIsAnchor = "";

    @Override
    public String toString() {
        return String.format("%3s|%3s|%5s|%3s|%3s|%4s|%2s| %s", startOneNumber, startTwoNumber, start,
                stopOneNumber, stopTwoNumber, stop, lineIsAnchor, anchorLine);
    }
}