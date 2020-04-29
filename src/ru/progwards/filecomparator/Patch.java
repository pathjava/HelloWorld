// Oleg Kiselev
// 28.04.2020, 20:04

package ru.progwards.filecomparator;

public class Patch {

    PatchIdentifier identifier = new PatchIdentifier();
    PatchLine line = new PatchLine();

    @Override
    public String toString() {
        return "" + identifier + line;
    }

    static class PatchIdentifier {
        public int oneStart;
        public int oneCount;
        public int twoStart;
        public int twoCount;

        @Override
        public String toString() {
            if (oneStart != 0)
                return "@@ " + "-" + oneStart + "," + oneCount + " " + "+" + twoStart + "," + twoCount + " @@";
            else
                return "";
        }
    }

    static class PatchLine {
        public String noNewLine = "";
        public String plusMinusEmpty = "";
        public String fileLines = "";

        @Override
        public String toString() {
            return plusMinusEmpty + fileLines + noNewLine;
        }
    }
}
