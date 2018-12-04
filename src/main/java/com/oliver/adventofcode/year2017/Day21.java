package com.oliver.adventofcode.year2017;

import java.util.*;

public class Day21 {

    private static final String STARTING_GRID = ".#./..#/###";

    private Map<String, String> enhancementRules;
    private String grid;

    public Day21(List<String> enhancements) {
        grid = STARTING_GRID;
        enhancementRules = new HashMap<>();
        for (String enhancement : enhancements) {
            String[] parts = enhancement.split(" => ");
            Set<String> transformations = getTransformations(parts[0]);
            for (String transformation : transformations) {
                enhancementRules.put(transformation, parts[1]);
            }
        }
    }

    public int getNumberElementsOn() {
        int count = 0;
        for (char c : grid.toCharArray()) {
            if (c == '#') {
                count++;
            }
        }
        return count;
    }

    public void runIterations(int iterations) {
        for (int iteration = 0; iteration < iterations; iteration++) {
            getNextGrid();
        }
    }

    private void getNextGrid() {
        String[] rows = grid.split("/");

        int divisor = (rows.length % 2 == 0) ? 2 : 3;
        int gridSplitCount = rows.length / divisor;

        StringBuilder[] newRows = getEnhancedGridRows(rows, divisor, gridSplitCount);

        StringBuilder result = new StringBuilder();
        for (StringBuilder newRow : newRows) {
            result.append(newRow);
            result.append("/");
        }
        grid = removeLastElement(result);
    }

    private StringBuilder[] getEnhancedGridRows(String[] rows, int divisor, int gridSplitCount) {
        StringBuilder[] newRows = new StringBuilder[rows.length + gridSplitCount];
        for (int row = 0; row < rows.length + gridSplitCount; row++) {
            newRows[row] = new StringBuilder();
        }

        for (int subGridX = 0; subGridX < gridSplitCount; subGridX++) {
            for (int subGridY = 0; subGridY < gridSplitCount; subGridY++) {
                String subGrid = getSubGrid(rows, divisor, subGridX, subGridY);
                String[] subGridRows = enhancementRules.get(subGrid).split("/");
                for (int rowCount = 0; rowCount < subGridRows.length; rowCount++) {
                    int gridRow = rowCount + subGridX * (divisor + 1);
                    newRows[gridRow].append(subGridRows[rowCount]);
                }
            }
        }
        return newRows;
    }

    private String getSubGrid(String[] rows, int divisor, int subGridX, int subGridY) {
        StringBuilder subGrid = new StringBuilder();
        int rowStart = subGridX * divisor;
        int colStart = subGridY * divisor;
        for (int row = rowStart; row < rowStart + divisor; row++) {
            subGrid.append(rows[row].substring(colStart, colStart + divisor)).append("/");
        }
        return removeLastElement(subGrid);
    }

    private Set<String> getTransformations(String input) {
        Set<String> transformations = new HashSet<>();
        String flippedInput = flip(input);
        for (int i = 0; i < 4; i++) {
            transformations.add(input);
            transformations.add(flippedInput);
            input = rotateClockWise(input);
            flippedInput = rotateClockWise(flippedInput);
        }

        return transformations;
    }

    private String rotateClockWise(String input) {
        String[] rows = input.split("/");

        int inputSize = rows.length;
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < inputSize; row++) {
            for (int col = 0; col < inputSize; col++) {
                result.append(rows[col].charAt(inputSize - row - 1));
            }
            result.append("/");
        }
        return removeLastElement(result);
    }

    private String flip(String input) {
        String[] rows = input.split("/");

        int inputSize = rows.length;
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < inputSize; row++) {
            for (int col = 0; col < inputSize; col++) {
                result.append(rows[row].charAt(inputSize - col - 1));
            }
            result.append("/");
        }
        return removeLastElement(result);
    }

    private String removeLastElement(StringBuilder stringBuilder) {
        return stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length()).toString();
    }
}
