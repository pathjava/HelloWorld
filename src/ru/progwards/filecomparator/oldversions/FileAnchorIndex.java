package ru.progwards.filecomparator.oldversions;

public class FileAnchorIndex {
    public int oneListIndex;
    public int twoListIndex;
    public String start = "#";
    public String finish = "#";

    @Override
    public String toString() {
        return String.format("%3d|%3d|%5s|%6s", oneListIndex, twoListIndex, start, finish);
    }
}
