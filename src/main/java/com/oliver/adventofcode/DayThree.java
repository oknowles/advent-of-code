package com.oliver.adventofcode;

import java.util.HashMap;
import java.util.Map;

public class DayThree {

    public int getManhattanDistance(int location) {
        int sideLength = 1;
        while ((sideLength * sideLength) < location) {
            sideLength += 2;
        }

        int minDistance = Integer.MAX_VALUE;
        for (int corner = 0; corner < 4; corner++) {
            int curLayerCorner = (sideLength * sideLength) - (sideLength - 1) * corner;
            int distanceToCorner = Math.abs(curLayerCorner - location);
            if (distanceToCorner < minDistance) {
                minDistance = distanceToCorner;
            }
        }
        return sideLength - 1 - minDistance;
    }

    public int getNextMemoryValue(int value) {
        Map<String, Integer> grid = new HashMap<>();

        putValue(grid, 0, 0, 1);
        int curX = 1;
        int curY = 0;
        int curVal = 1;

        int curSegmentLength = 1;
        int curSegmentProgress = 0;

        int dirX = 0;
        int dirY = 1;

        while (curVal <= value) {
            curVal = getAdjacentCellsSum(grid, curX, curY);
            putValue(grid, curX, curY, curVal);

            curX += dirX;
            curY += dirY;
            curSegmentProgress++;

            if (curSegmentProgress == curSegmentLength) {
                curSegmentProgress = 0;

                int temp = dirX;
                dirX = -dirY;
                dirY = temp;

                if (dirY == 0) {
                    curSegmentLength++;
                }
            }
        }
        return curVal;
    }

    private void putValue(Map<String, Integer> grid, int curX, int curY, int val) {
        grid.put(getKey(curX, curY), val);
    }

    private int getValue(Map<String, Integer> grid, int x, int y) {
        return grid.getOrDefault(getKey(x, y), 0);
    }

    private String getKey(int x, int y) {
        return x + "-" + y;
    }

    private int getAdjacentCellsSum(Map<String, Integer> grid, int curX, int curY) {
        int rM = getValue(grid, curX + 1, curY);
        int rU = getValue(grid, curX + 1, curY + 1);
        int rD = getValue(grid, curX + 1, curY - 1);
        int mU = getValue(grid, curX, curY + 1);
        int mD = getValue(grid, curX, curY - 1);
        int lM = getValue(grid, curX - 1, curY);
        int lU = getValue(grid, curX - 1, curY + 1);
        int lD = getValue(grid, curX - 1, curY - 1);

        return rM + rU + rD + mU + mD + lM + lU + lD;
    }
}
