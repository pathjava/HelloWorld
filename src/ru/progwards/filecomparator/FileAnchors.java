package ru.progwards.filecomparator;

public class FileAnchors {
    public String anchorsLines = "";
    public String startFinish = "#";

    @Override
    public String toString() {
        return String.format("%6s| %s", startFinish, anchorsLines);
    }
}
