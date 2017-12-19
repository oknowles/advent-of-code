package com.oliver.adventofcode;

import java.util.List;

public class Day19 {

    private static final char WHITESPACE = ' ';
    private static final char VERTICAL_PATH = '|';
    private static final char HORIZONTAL_PATH = '-';
    private static final char DIRECTION_CHANGE = '+';

    private List<String> grid;

    public Day19(List<String> grid) {
        this.grid = grid;
    }

    public Result walkPath() {
        StringBuilder pathLetters = new StringBuilder();

        int pathLength = 0;
        int xDirection = 0;
        int yDirection = 1;
        int lineIndex = 0;
        char curChar = VERTICAL_PATH;
        int charPos = grid.get(lineIndex).indexOf(curChar);

        while (curChar != WHITESPACE) {
            pathLength++;

            if (directionChange(curChar)) {
                if (movingHorizontally(xDirection)) {
                    int yChange = pathGoesDown(lineIndex, charPos) ? -1 : 1;
                    yDirection += yChange;
                    lineIndex += yChange;
                    xDirection = 0;
                } else {
                    int xChange = pathGoesLeft(lineIndex, charPos) ? -1 : 1;
                    xDirection += xChange;
                    charPos += xChange;
                    yDirection = 0;
                }
            } else {
                if (movingHorizontally(xDirection)) {
                    charPos += xDirection;
                } else {
                    lineIndex += yDirection;
                }
            }

            curChar = getChar(lineIndex, charPos);

            if (Character.isAlphabetic(curChar)) {
                pathLetters.append(curChar);
            }
        }

        return new Result(pathLetters.toString(), pathLength);
    }

    private boolean movingHorizontally(int xDirection) {
        return xDirection != 0;
    }

    private boolean directionChange(char curChar) {
        return curChar == DIRECTION_CHANGE;
    }

    private boolean pathGoesDown(int lineIndex, int charPos) {
        char candidateAbove = getChar(lineIndex - 1, charPos);
        return isCharOrLetter(candidateAbove,VERTICAL_PATH);
    }

    private boolean pathGoesLeft(int lineIndex, int charPos) {
        char candidateLeft = getChar(lineIndex, charPos - 1);
        return isCharOrLetter(candidateLeft, HORIZONTAL_PATH);
    }

    private char getChar(int lineIndex, int charPos) {
        try {
            return grid.get(lineIndex).charAt(charPos);
        } catch (IndexOutOfBoundsException e) {
            return WHITESPACE;
        }
    }

    private boolean isCharOrLetter(char charToCheck, char candidate) {
        return charToCheck == candidate || Character.isAlphabetic(charToCheck);
    }

    class Result {

        private String lettersInPath;
        private int pathLength;

        public Result(String lettersInPath, int pathLength) {
            this.lettersInPath = lettersInPath;
            this.pathLength = pathLength;
        }

        public String getPathLetters() {
            return lettersInPath;
        }

        public int getPathLength() {
            return pathLength;
        }
    }
}
