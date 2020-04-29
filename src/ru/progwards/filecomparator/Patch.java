// Oleg Kiselev
// 28.04.2020, 20:04

package ru.progwards.filecomparator;

public class Patch {

    public int oneStart;
    public int oneCount;
    public int twoStart;
    public int twoCount;
    public String noNewLine = "";
    public String plusMinusEmpty = " ";
    public String fileLines = "";

    @Override
    public String toString() {
        if (oneStart != 0)
            return "@@ " + "-" + oneStart + "," + oneCount + " " + "+" + twoStart + "," + twoCount + " @@";
        else
            return plusMinusEmpty + fileLines + noNewLine;
    }
}