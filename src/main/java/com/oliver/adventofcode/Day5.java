package com.oliver.adventofcode;

import java.util.List;
import java.util.function.Function;

public class Day5 {

    private int[] input;

    public Day5(int[] intput) {
        this.input = intput;
    }

    public static Day5 fromInput() {
        List<String> stringInput = Utils.readFile(5);
        int[] input = new int[stringInput.size()];
        for (int i = 0; i < input.length; i++) {
            input[i] = Integer.parseInt(stringInput.get(i));
        }
        return new Day5(input);
    }

    public int getFirstHalfJumps() {
        return getNumberJumps(this::firstHalfIncrementor);
    }

    public int getSecondHalfJumps() {
        return getNumberJumps(this::secondHalfIncrementor);
    }

    private int firstHalfIncrementor(int prevJump) {
        return prevJump + 1;
    }

    private int secondHalfIncrementor(int prevJump) {
        int jumpIncrease = (prevJump > 2) ? -1 : 1;
        return prevJump + jumpIncrease;
    }

    private int getNumberJumps(Function<Integer, Integer> jumpIncrementor) {
        int[] inputClone = input.clone();
        int curPos = 0;
        int jumpCount = 0;
        while (curPos < inputClone.length) {
            int nexPos = curPos + inputClone[curPos];
            inputClone[curPos] = jumpIncrementor.apply(inputClone[curPos]);
            curPos = nexPos;
            jumpCount++;
        }
        return jumpCount;
    }
}
