package com.oliver.adventofcode;

public class Day9 {

    public Result getScore(String input) {
        boolean ignore = false;
        boolean inGarbage = false;

        int score = 0;
        int curScore = 0;
        int garbageCount = 0;

        for (int i = 0; i < input.length(); i++) {
            char curChar = input.charAt(i);
            if (ignore) {
                ignore = false;
            } else if (curChar == '!') {
                ignore = true;
            } else if (curChar == '>') {
                inGarbage = false;
            } else if (inGarbage) {
                garbageCount++;
            } else {
                switch (curChar) {
                    case '<':
                        inGarbage = true;
                        continue;
                    case '{':
                        curScore++;
                        continue;
                    case '}':
                        score += curScore;
                        curScore--;
                }
            }
        }
        return new Result(score, garbageCount);
    }

    static class Result {
        private int score;
        private int charsInGarbage;

        public Result(int score, int charsInGarbage) {
            this.score = score;
            this.charsInGarbage = charsInGarbage;
        }

        public int getScore() {
            return score;
        }

        public int getCharsInGarbage() {
            return charsInGarbage;
        }
    }
}