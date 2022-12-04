package com.oliver.adventofcode.year2017;

import java.util.ArrayList;
import java.util.List;

public class Day17 {

    private int stepsPerInsert;
    private List<Integer> buffer;
    private int currentPosition;

    public Day17(int stepsPerInsert) {
        this.stepsPerInsert = stepsPerInsert;
        this.buffer = new ArrayList<>();
        buffer.add(0);
        this.currentPosition = 0;
    }

    public int getValueAfter(int value) {
        for (int i = 1; i <= value; i++) {
            insertNextValue(i);
        }
        return buffer.get(currentPosition + 1);
    }

    public int getValueAfterZero(int numAdditions) {
        int currentPosition = 0;
        int bufferSize = 1;
        int curZeroPos = 0;
        int afterZeroVal = 0;

        for (int i = 1; i < numAdditions; i++) {
            currentPosition = (currentPosition + stepsPerInsert ) % bufferSize + 1;
            int afterZeroPos = (curZeroPos + 1) % bufferSize;
            if (currentPosition <= curZeroPos) {
                curZeroPos++;
            } else if (currentPosition == afterZeroPos) {
                afterZeroVal = i;
            }
            bufferSize++;
        }
        return afterZeroVal;
    }

    private void insertNextValue(int value) {
        currentPosition  = (currentPosition + stepsPerInsert) % buffer.size() + 1;
        buffer.add(currentPosition, value);
    }
}
