package ru.progwards.filecomparator;

public class FileAnchors {
    public String anchorsLines = "";
    public String start = "#";
    public String finish = "#";

    @Override
    public String toString() {
        return String.format("%5s|%6s| %s", start, finish, anchorsLines);
    }
}
