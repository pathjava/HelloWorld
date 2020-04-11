package ru.progwards.filecomparator.oldversions;

public class FileCompareAnchors {
    public String lineFromFile;
    public int startLineBefore;
    public int finishLineBefore;

    @Override
    public String toString() {
        if (startLineBefore == 0 && finishLineBefore == 0)
            return "|   |   " + String.format("| %s", lineFromFile);
        else if (finishLineBefore == 0)
            return "|" + String.format("%3d|", startLineBefore) + "   " + String.format("| %s", lineFromFile);
        else if (startLineBefore == 0)
            return "|   |" + String.format("%3d| %s", finishLineBefore, lineFromFile);
        else
            return "|" + String.format("%3d|%3d| %s", startLineBefore, finishLineBefore, lineFromFile);
    }

//    @Override
//    public String toString() {
//        return "| " + lineFromFile;
//    }
}
