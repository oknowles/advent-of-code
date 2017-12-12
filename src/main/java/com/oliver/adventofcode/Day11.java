package com.oliver.adventofcode;

import java.util.List;

public class Day11 {

    public Result getFewestSteps(List<String> path) {
        double xChange = 0;
        double yChange = 0;
        double maxXChange = Double.MIN_VALUE;
        double maxYChange = Double.MIN_VALUE;
        for (String movement : path) {
            xChange += getXMovement(movement);
            yChange += getYMovement(movement);
            maxXChange = (Math.abs(xChange) > maxXChange) ? Math.abs(xChange) : maxXChange;
            maxYChange = (Math.abs(yChange) > maxYChange) ? Math.abs(yChange) : maxYChange;
        }
        return new Result(getSteps(xChange, yChange), getSteps(maxXChange, maxYChange));
    }

    private int getXMovement(String direction) {
        if (direction.contains("e")) {
            return 1;
        } else if (direction.contains("w")) {
            return -1;
        }
        return 0;
    }

    private double getYMovement(String direction) {
        if (direction.length() == 1) {
            return direction.equals("n") ? 1 : -1;
        }
        return (direction.contains("n")) ? 0.5 : -0.5;
    }

    private int getSteps(double xChange, double yChange) {
        if (Math.abs(xChange) > Math.abs(yChange)) {
            return (int) Math.abs(xChange);
        } else {
            return (int) Math.ceil(Math.abs(yChange));
        }
    }

    static class Result {
        private int shortestSteps;
        private int maxSteps;

        public Result(int shortestSteps, int maxSteps) {
            this.shortestSteps = shortestSteps;
            this.maxSteps = maxSteps;
        }

        public int getShortestSteps() {
            return shortestSteps;
        }

        public int getMaxSteps() {
            return maxSteps;
        }
    }
}
