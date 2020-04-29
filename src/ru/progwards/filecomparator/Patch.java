// Oleg Kiselev
// 28.04.2020, 20:04

package ru.progwards.filecomparator;

public class Patch {

    PatchIdentifier identifier = new PatchIdentifier();
    PatchLine line = new PatchLine();

    static class PatchIdentifier {
        public String dogs = "@@";
        public int oneStart;
        public int oneCount;
        public int twoStart;
        public int twoCount;

        @Override
        public String toString() {
            return dogs + " " + oneStart + "," + oneCount + " " + twoStart + "," + twoCount + " " + dogs;
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
