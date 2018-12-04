package com.oliver.adventofcode.year2017;

public class Day10 {

    private static final int[] LENGTH_SUFFIX = new int[] {17, 31, 73, 47, 23};

    private int[] array;
    private int[] lengths;
    private int curPos = 0;
    private int skipSize = 0;

    public Day10(int maxSize, int[] lengths) {
        this.array = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            array[i] = i;
        }
        this.lengths = lengths;
    }

    public static Day10 fromString(String input) {
        byte[] inputBytes = input.getBytes();
        int[] lengths = new int[inputBytes.length + LENGTH_SUFFIX.length];
        for (int i = 0; i < input.length(); i++){
            lengths[i] = inputBytes[i];
        }
        System.arraycopy(LENGTH_SUFFIX, 0, lengths, input.length(), LENGTH_SUFFIX.length);

        return new Day10(256, lengths);
    }

    public int getFirstRoundMultiple() {
        performHashRound();
        return array[0] * array[1];
    }

    public String getKnotHash() {
        for (int i = 0; i < 64; i++) {
            performHashRound();
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int xorBlock = 0; xorBlock < 16; xorBlock++) {
            int xor = 0;
            for (int i = 0; i < 16; i++) {
                xor = xor ^ array[xorBlock * 16 + i];
            }
            stringBuilder.append(getPaddedHex(xor));
        }
        return stringBuilder.toString();
    }

    private void performHashRound() {
        for (Integer length : lengths) {
            reverseElements(curPos, curPos + length - 1);
            curPos = (curPos + length + skipSize) % array.length;
            skipSize++;
        }
    }

    private void reverseElements(int start, int end) {
        while (start <= end) {
            int startPos = start % array.length;
            int endPos = end % array.length;
            int temp = array[startPos];
            array[startPos] = array[endPos];
            array[endPos] = temp;

            start++;
            end--;
        }
    }

    private String getPaddedHex(int num) {
        String nonPaddedHex = Integer.toHexString(num);
        return (nonPaddedHex.length() > 1) ? nonPaddedHex : "0" + nonPaddedHex;
    }
}
