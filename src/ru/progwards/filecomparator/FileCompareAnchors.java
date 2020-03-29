package ru.progwards.filecomparator;

public class FileCompareAnchors {
    public String lineFromFile;
    public int startLineBefore;
    public int finishLineBefore;

    @Override
    public String toString() {
        return "{" + lineFromFile +
                ", startLineBefore=" + startLineBefore +
                ", finishLineBefore=" + finishLineBefore + "}";
    }
}
