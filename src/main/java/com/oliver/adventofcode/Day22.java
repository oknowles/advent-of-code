package com.oliver.adventofcode;

import java.util.List;
import java.util.function.Supplier;

public class Day22 {

    private static final int GRID_SIZE = 10_000;
    private static final int CLEAN_STATUS = 0;
    private static final int WEAKENED_STATUS = 1;
    private static final int INFECTED_STATUS = 2;
    private static final int FLAGGED_STATUS = 3;

    private int[][] grid = new int[GRID_SIZE][GRID_SIZE];
    private int[] carrierPosition;
    private int[] carrierDirection;

    public Day22(List<String> input) {
        int midPoint = GRID_SIZE / 2;
        carrierPosition = new int[] {midPoint + input.size() / 2, midPoint + input.size() / 2};
        carrierDirection = new int[] {0, -1};

        int rowPos = midPoint;
        for (String row : input) {
            int colPos = midPoint;
            for (Character c : row.toCharArray()) {
                if (c == '#') {
                    grid[rowPos][colPos] = 2;
                }
                colPos++;
            }
            rowPos++;
        }
    }

    public int runBinaryBursts(int numBursts) {
        return runBursts(numBursts, 2, this::isClean);
    }

    public int runFourStateBursts(int numBursts) {
        return runBursts(numBursts, 1, this::isWeakened);
    }

    private int runBursts(int numBursts, int stateJump, Supplier<Boolean> currentToBeInfected) {
        int infectionCount = 0;
        for (int i = 0; i < numBursts; i++) {
            if (isClean()) {
                turnLeft();
            } else if (isInfected()) {
                turnRight();
            } else if (isFlagged()) {
                reverse();
            }
            infectionCount += currentToBeInfected.get() ? 1 : 0;
            changeStateOfCurrentElement(stateJump);
            moveCarrier();
        }
        return infectionCount;
    }

    private void changeStateOfCurrentElement(int stateJump) {
        int positionX = carrierPosition[0];
        int positionY = carrierPosition[1];
        grid[positionY][positionX] = (grid[positionY][positionX] + stateJump) % 4;
    }

    private void moveCarrier() {
        carrierPosition[0] += carrierDirection[0];
        carrierPosition[1] += carrierDirection[1];
    }

    private boolean isClean() {
        return assertStatus(carrierPosition[0], carrierPosition[1], CLEAN_STATUS);
    }

    private boolean isWeakened() {
        return assertStatus(carrierPosition[0], carrierPosition[1], WEAKENED_STATUS);
    }

    private boolean isInfected() {
        return assertStatus(carrierPosition[0], carrierPosition[1], INFECTED_STATUS);
    }

    private boolean isFlagged() {
        return assertStatus(carrierPosition[0], carrierPosition[1], FLAGGED_STATUS);
    }

    private boolean assertStatus(int x, int y, int status) {
        return grid[y][x] == status;
    }

    private void turnRight() {
        int temp = carrierDirection[0];
        carrierDirection[0] = -carrierDirection[1];
        carrierDirection[1] = temp;
    }

    private void turnLeft() {
        int temp = carrierDirection[1];
        carrierDirection[1] = -carrierDirection[0];
        carrierDirection[0] = temp;
    }

    private void reverse() {
        carrierDirection[0] = -carrierDirection[0];
        carrierDirection[1] = -carrierDirection[1];
    }
}
